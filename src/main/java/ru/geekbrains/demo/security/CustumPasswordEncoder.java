package ru.geekbrains.demo.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Component
public class CustumPasswordEncoder implements PasswordEncoder {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Override
    public String encode(CharSequence rawPassword) {
        // шифруем пароль
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}