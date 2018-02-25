package com.jsonfloyd.alstop.security.event;

import com.jsonfloyd.alstop.security.model.VerificationToken;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

//TODO refresh token event
public class OnVerificationTokenRefreshEvent extends ApplicationEvent{
    @Getter private final VerificationToken token;
    public OnVerificationTokenRefreshEvent(Object source, VerificationToken token) {
        super(source);
        this.token = token;
    }
}
