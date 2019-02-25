package com.wakabatimes.ankysentence.app.domain.service.user;

import com.wakabatimes.ankysentence.app.domain.model.user.User;
import com.wakabatimes.ankysentence.app.domain.model.user.UserId;
import com.wakabatimes.ankysentence.app.domain.model.user.UserMailAddress;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    /**
     * ユーザー情報の保存
     * @param user
     */
    void save(User user);

    /**
     * アクティベーション用のメール送信
     * @param user
     */
    void activateMail(User user) throws NoSuchAlgorithmException;

    /**
     * ユーザーアクティベイト
     * @param hash
     */
    void activate(UserMailAddress userMailAddress, String hash);

    /**
     * パスワード再設定メール
     * @param userMailAddress
     */
    void passwordRemindMail(UserMailAddress userMailAddress) throws NoSuchAlgorithmException;

    /**
     * パスワード再設定
     * @param hash
     * @param password
     */
    void passwordReset(String hash, String password);

    /**
     * ユーザーの退会
     * @param userId
     */
    void delete(UserId userId, UserMailAddress userMailAddress);

    /**
     * ユーザー更新
     * @param user
     */
    void update(User user);

}
