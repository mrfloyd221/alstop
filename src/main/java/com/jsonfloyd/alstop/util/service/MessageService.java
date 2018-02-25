package com.jsonfloyd.alstop.util.service;

import com.jsonfloyd.alstop.security.model.Account;

import javax.mail.MessagingException;

public interface MessageService {
    void sendActivationToken(String token, String email)throws MessagingException;
    void sendRegistrationGratitude(Account account) throws MessagingException;
}
