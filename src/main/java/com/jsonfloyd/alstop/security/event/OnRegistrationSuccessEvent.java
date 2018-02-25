package com.jsonfloyd.alstop.security.event;

import com.jsonfloyd.alstop.security.model.VerificationToken;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

//TODO registration success event
public class OnRegistrationSuccessEvent extends ApplicationEvent {
    @Getter
    private final VerificationToken token;
    public OnRegistrationSuccessEvent(Object source, VerificationToken token) {
        super(source);
        this.token = token;
    }
}
