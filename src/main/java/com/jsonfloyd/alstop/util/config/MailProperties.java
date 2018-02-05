package com.jsonfloyd.alstop.util.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="mail")
public class MailProperties {
    @Getter @Setter private String protocol;
    @Getter @Setter private String host;
    @Getter @Setter private Integer port;
    @Getter @Setter private String username;
    @Getter @Setter private String password;
    @Getter @Setter private String debug;

}
