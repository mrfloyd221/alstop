package com.jsonfloyd.alstop.security.event;

import com.jsonfloyd.alstop.util.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class AuthentificationEventsListener {

    private final MessageService messageService;
    @Autowired
    public AuthentificationEventsListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @EventListener(OnRegistrationSuccessEvent.class)
    public void registrationSuccessHandler(OnRegistrationSuccessEvent event) throws MessagingException{
            messageService.sendActivationToken(event.getToken().getToken(), event.getToken().getAccount().getEmail());
    }
    @EventListener(OnAccountEnabledEvent.class)
    public void accountEnabledHandler(OnAccountEnabledEvent event)  throws MessagingException{
            messageService.sendRegistrationGratitude(event.getAccount());
    }
}
