package com.jsonfloyd.alstop.util.service;

import org.springframework.stereotype.Component;
import java.util.concurrent.ThreadLocalRandom;
@Component
public class Base64TokenGenerator implements TokenGenerator {
    private final String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0987654321-_";
    @Override
    public String generateToken(int length){
        StringBuilder uid = new StringBuilder();
        while(length-- > 0){
            int index = ThreadLocalRandom.current().nextInt(0, symbols.length());
            uid.append(symbols.charAt(index));
        }
        return uid.toString();
    }
}
