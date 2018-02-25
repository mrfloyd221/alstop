package com.jsonfloyd.alstop.security.event;

import com.jsonfloyd.alstop.security.model.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


public class OnAccountEnabledEvent extends ApplicationEvent {
    @Getter
    private final Account account;
    public OnAccountEnabledEvent(Object source, Account account){
        super(source);
        this.account = account;
    }
}
