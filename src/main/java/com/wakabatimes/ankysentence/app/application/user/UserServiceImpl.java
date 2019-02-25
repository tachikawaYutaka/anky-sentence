package com.wakabatimes.ankysentence.app.application.user;
import com.wakabatimes.ankysentence.app.domain.model.user.*;
import com.wakabatimes.ankysentence.app.domain.service.user.UserService;
import com.wakabatimes.ankysentence.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    MailConfig mailConfig;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${settings.url}")
    private String url;

    @Value("${settings.activatePath}")
    private String activatePath;

    @Value("${settings.fromMail}")
    private String fromMail;

    @Value("${settings.systemName}")
    private String systemName;

    @Value("${settings.remindPath}")
    private String remindPath;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void activateMail(User user) throws NoSuchAlgorithmException {
        try {

            //ハッシュを生成したい元の文字列
            String source = user.getUserMailAddress().getValue();
            //ハッシュ生成前にバイト配列に置き換える際のCharset
            Charset charset = StandardCharsets.UTF_8;
            //ハッシュアルゴリズム
            String algorithm = "MD5";

            //ハッシュ生成処理
            byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
            String result = DatatypeConverter.printHexBinary(bytes);

            String message = "" +
                    user.getUserMailAddress().getValue() + "さん\n" +
                    "\n" +
                    "この度は、" + systemName + " にご登録いただきありがとうございます。\n" +
                    "以下のURLにアクセスするとメールアドレスが有効化されます。\n" +
                    "\n" +
                    url + activatePath + result;

            //メール送信
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(fromMail);
            msg.setTo(user.getUserMailAddress().getValue());
            msg.setSubject("Please verify your email");
            msg.setText(message);

            mailConfig.javaMailService().send(msg);

            //ユーザーに紐づくハッシュを削除
            userRepository.deleteHash(user);

            //生成したハッシュを格納
            userRepository.saveHash(user,result);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void activate(UserMailAddress userMailAddress, String hash) {
        User user = userRepository.getUserByMail(userMailAddress.getValue());
        UserId userId = user.getUserId();
        Long count = userRepository.countUserHashAndUserId(userId, hash);
        if(count > 0) {
            userRepository.activate(userId);
        }else {
            throw new RuntimeException("URLが不正です。");
        }
    }

    @Override
    public void passwordRemindMail(UserMailAddress userMailAddress) throws NoSuchAlgorithmException {
        try {

            User user = userRepository.getUserByMailAddress(userMailAddress);

            //ハッシュを生成したい元の文字列
            String source = user.getUserMailAddress().getValue();
            //ハッシュ生成前にバイト配列に置き換える際のCharset
            Charset charset = StandardCharsets.UTF_8;
            //ハッシュアルゴリズム
            String algorithm = "MD5";

            //ハッシュ生成処理
            byte[] bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
            String result = DatatypeConverter.printHexBinary(bytes);

            String message = "" +
                    user.getUserMailAddress().getValue() + "さん\n" +
                    "\n" +
                    "この度は、" + systemName + " にご利用いただきありがとうございます。\n" +
                    "以下のURLよりパスワードの再設定を行ってください。\n" +
                    "\n" +
                    url + remindPath + result;

            //メール送信
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom(fromMail);
            msg.setTo(user.getUserMailAddress().getValue());
            msg.setSubject("Resetting a password");
            msg.setText(message);

            mailConfig.javaMailService().send(msg);

            //ユーザーに紐づくハッシュを削除
            userRepository.deleteHash(user);

            //生成したハッシュを格納
            userRepository.saveHash(user,result);
        } catch (RuntimeException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void passwordReset(String hash, String password) {
        Long count = userRepository.countUserHash(hash);
        if(count > 0) {
            UserId userId = userRepository.getUserIdByHash(hash);
            UserPassword userPassword = new UserPassword(password,bCryptPasswordEncoder);
            userRepository.updatePassword(userId,userPassword);
        }else {
            throw new RuntimeException("URLが不正です。");
        }
    }

    @Override
    public void delete(UserId userId, UserMailAddress userMailAddress) {
        try {
            Long count = userRepository.countUserByIdAndMail(userId,userMailAddress);
            if(count > 0) {
                userRepository.delete(userId);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(User user) {
        userRepository.update(user);
    }
}
