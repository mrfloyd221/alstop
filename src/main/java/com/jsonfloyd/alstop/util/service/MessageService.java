package com.jsonfloyd.alstop.util.service;

import com.jsonfloyd.alstop.security.model.Account;

public interface MessageService {
    void sendActivationToken(String token, String email);
    void sendRegistrationGratitude(String email, Account account);
}
