package com.wakabatimes.ankysentence.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wakabatimes.ankysentence.app.interfaces.user.form.UserLoginForm;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.wakabatimes.ankysentence.config.ApiUrlConstants.API_URL;
import static com.wakabatimes.ankysentence.config.SecurityConstants.*;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

        // ログイン用のpathを変更する
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(API_URL + LOGIN_URL, "POST"));

        // ログイン用のID/PWのパラメータ名を変更する
        setUsernameParameter(LOGIN_ID);
        setPasswordParameter(PASSWORD);

    }

    // 認証の処理
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res)  {
        try {
            // requestパラメータからユーザ情報を読み取る
            UserLoginForm form = new ObjectMapper().readValue(req.getInputStream(), UserLoginForm.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            form.getUserMailAddress(),
                            form.getUserPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    // 認証に成功した場合の処理
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        // loginIdからtokenを設定してヘッダにセットする
        String token = Jwts.builder()
                .setSubject(auth.getName()) // usernameだけを設定する
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);

    }

}
