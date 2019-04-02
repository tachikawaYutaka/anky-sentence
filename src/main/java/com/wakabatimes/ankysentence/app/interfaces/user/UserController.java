package com.wakabatimes.ankysentence.app.interfaces.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wakabatimes.ankysentence.app.domain.aggregates.user_book.UserBooks;
import com.wakabatimes.ankysentence.app.domain.model.user.*;
import com.wakabatimes.ankysentence.app.domain.service.user.UserService;
import com.wakabatimes.ankysentence.app.domain.service.user.UserSubscribeService;
import com.wakabatimes.ankysentence.app.domain.service.user_book.UserBookService;
import com.wakabatimes.ankysentence.app.interfaces.question.dto.QuestionResponseDto;
import com.wakabatimes.ankysentence.app.interfaces.user.dto.*;
import com.wakabatimes.ankysentence.app.interfaces.user.form.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;

import static com.wakabatimes.ankysentence.config.ApiUrlConstants.API_URL;
import static com.wakabatimes.ankysentence.config.SecurityConstants.SIGNUP_URL;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserBookService userBookService;

    @Autowired
    private UserSubscribeService userSubscribeService;

    private HttpStatus status = HttpStatus.OK;

    /**
     * csrfトークンの発行
     * @param token
     * @return　ResponseEntity<String>
     */
    @GetMapping(value = API_URL + "/user/csrf-token", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> csrf(CsrfToken token) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(token);
        } catch (JsonProcessingException e) {
            log.error("error occurs: ", e);
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(json, status);
    }

    /**
     * signup
     * @param form
     * @return UserSubscribeResponseDto
     */
    @PostMapping(value = API_URL + SIGNUP_URL, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserSubscribeResponseDto signup(@Valid @RequestBody UserSubscribeForm form) {
        UserMailAddress userMailAddress = new UserMailAddress(form.getMail());
        UserPassword userPassword = new UserPassword(form.getPassword(),bCryptPasswordEncoder);
        User user = UserFactory.create(userMailAddress, userPassword);
        UserSubscribeResponseDto userSubscribeResponseDto = new UserSubscribeResponseDto(user);

        try {
            userSubscribeService.save(user);
            userSubscribeResponseDto.setStatus(status.getReasonPhrase());
            userSubscribeResponseDto.setMessage("Success");
        } catch (RuntimeException e) {
            log.error("error occurs:", e);
            // 問題があればクライアントエラーとして返す。
            status = HttpStatus.BAD_REQUEST;
            userSubscribeResponseDto.setStatus(status.getReasonPhrase());
            userSubscribeResponseDto.setMessage(e.getMessage());
        } catch (Exception e) {
            log.error("error occurs:", e);
            // それ以外の問題はサーバ内エラーとして返す。
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            userSubscribeResponseDto.setStatus(status.getReasonPhrase());
            userSubscribeResponseDto.setMessage(e.getMessage());
        }
        return userSubscribeResponseDto;
    }


    /**
     * remind
     * @param form
     * @return UserPasswordRemindResponseDto
     */
    @PostMapping(value = API_URL + "/user/remind", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserPasswordRemindResponseDto remind(@Valid @RequestBody UserPasswordRemindForm form) {
        UserPasswordRemindResponseDto userPasswordRemindResponseDto = new UserPasswordRemindResponseDto();

        try {
            UserMailAddress userMailAddress = new UserMailAddress(form.getMail());
            userService.passwordRemindMail(userMailAddress);
            userPasswordRemindResponseDto.setStatus(status.getReasonPhrase());
            userPasswordRemindResponseDto.setMessage("パスワードの再設定メールを送信しました。");
        } catch(RuntimeException e) {
            log.error("error occurs:", e);
            status = HttpStatus.BAD_REQUEST;
            userPasswordRemindResponseDto.setStatus(status.getReasonPhrase());
            userPasswordRemindResponseDto.setMessage(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            log.error("error occurs:", e);
            status = HttpStatus.BAD_REQUEST;
            userPasswordRemindResponseDto.setStatus(status.getReasonPhrase());
            userPasswordRemindResponseDto.setMessage(e.getMessage());
        }

        return userPasswordRemindResponseDto;
    }

    /**
     * password reset
     * @param form
     * @return UserPasswordResetResponseDto
     */
    @PostMapping(value = API_URL + "/user/password-reset", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserPasswordResetResponseDto resetPassword(@Valid @RequestBody UserPasswordResetForm form) {
        UserPasswordResetResponseDto userPasswordResetResponseDto = new UserPasswordResetResponseDto();

        try {
            UserHash userHash = new UserHash(form.getHash());
            UserPassword userPassword = new UserPassword(form.getPassword(),bCryptPasswordEncoder);
            userService.passwordReset(userHash,userPassword);
            userPasswordResetResponseDto.setStatus(status.getReasonPhrase());
            userPasswordResetResponseDto.setMessage("パスワードの再設定をしました。");
        } catch(RuntimeException e) {
            log.error("error occurs:", e);
            status = HttpStatus.BAD_REQUEST;
            userPasswordResetResponseDto.setStatus(status.getReasonPhrase());
            userPasswordResetResponseDto.setMessage(e.getMessage());
        }
        return userPasswordResetResponseDto;
    }


    /**
     * Unsubscribe
     * @return UserUnsubscribeResponseDto
     */
    @PostMapping(value = API_URL + "/users/{userId}/unsubscribe", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserUnsubscribeResponseDto resetPassword(@PathVariable String userId,Principal principal) {
        UserUnsubscribeResponseDto userUnsubscribeResponseDto = new UserUnsubscribeResponseDto();

        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        if(!user.getUserId().getValue().equals(userId)){
            log.error("不正なアクセスです。");
            status = HttpStatus.BAD_REQUEST;
            userUnsubscribeResponseDto.setStatus(status.getReasonPhrase());
            userUnsubscribeResponseDto.setMessage("不正なアクセスです。");
            return userUnsubscribeResponseDto;
        }

        try {
            UserId userId1 = new UserId(userId);
            userService.delete(userId1,user.getUserMailAddress());
            userUnsubscribeResponseDto.setMessage("ユーザーの退会処理を行いました。");
            userUnsubscribeResponseDto.setStatus(status.getReasonPhrase());
        } catch(RuntimeException e) {
            log.error("error occurs:", e);
            status = HttpStatus.BAD_REQUEST;
            userUnsubscribeResponseDto.setStatus(status.getReasonPhrase());
            userUnsubscribeResponseDto.setMessage(e.getMessage());
        }
        return userUnsubscribeResponseDto;
    }


    /**
     * activate
     * @param hash
     * @return UserActivateResponseDto
     */
    @Transactional
    @PostMapping(value = API_URL + "/users/activate/{hash}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserActivateResponseDto activate(@PathVariable String hash) {
        UserActivateResponseDto userActivateResponseDto = new UserActivateResponseDto();

        try {
            UserHash userHash = new UserHash(hash);
            User user = userService.getUserByHash(userHash);
            UserMailAddress userMailAddress = user.getUserMailAddress();
            userService.activate(userMailAddress, userHash);
            userActivateResponseDto.setStatus(status.getReasonPhrase());
            userActivateResponseDto.setMessage("ユーザーの有効化を行いました。");
        } catch(RuntimeException e) {
            log.error("error occurs:", e);
            userActivateResponseDto.setMessage(e.getMessage());
            status = HttpStatus.BAD_REQUEST;
            userActivateResponseDto.setStatus(status.getReasonPhrase());
        }
        return userActivateResponseDto;
    }


    /**
     * user update
     * @param principal
     * @param form
     * @return
     */
    @PostMapping(value = API_URL + "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserUpdateResponseDto update(Principal principal, @Valid @RequestBody UserUpdateForm form) {
        UserUpdateResponseDto userUpdateResponseDto = new UserUpdateResponseDto();
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();
        try {
            UserMailAddress userMailAddress = new UserMailAddress(form.getMail());
            UserPassword userPassword = new UserPassword(form.getPassword(),bCryptPasswordEncoder);
            User user1 = new User(user.getUserId(),userMailAddress,userPassword,user.getUserStatus(),user.getUserRole());
            userService.update(user1);
            userUpdateResponseDto.setMessage("ユーザー情報を更新しました。");
            userUpdateResponseDto.setStatus(status.getReasonPhrase());
        } catch(RuntimeException e) {
            log.error("error occurs:", e);
            status = HttpStatus.BAD_REQUEST;
            userUpdateResponseDto.setStatus(status.getReasonPhrase());
            userUpdateResponseDto.setMessage(e.getMessage());
        }
        return userUpdateResponseDto;
    }

    /**
     * userBooks
     * @param principal
     * @return
     */
    @GetMapping(value = API_URL + "/users/user_books", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public UserBooksResponseDto userBooks(Principal principal) {
        Authentication auth = (Authentication) principal;
        User user = (User) auth.getPrincipal();

        UserBooksResponseDto userBooksResponseDto = new UserBooksResponseDto(user);
        try {
            UserBooks userBooks = userBookService.getByUserId(user.getUserId());
            List<UserBookResponseDto> userBooksResponseList = userBooks.responseList();
            userBooksResponseDto.setBooks(userBooksResponseList);
            userBooksResponseDto.setStatus(status.getReasonPhrase());
        } catch(RuntimeException e) {
            log.error("error occurs:", e);
            status = HttpStatus.BAD_REQUEST;
            userBooksResponseDto.setStatus(status.getReasonPhrase());
            userBooksResponseDto.setMessage(e.getMessage());
        }
        return userBooksResponseDto;
    }
}
