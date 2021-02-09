package com.mygit.configurationproperties.config;

import com.mygit.configurationproperties.po.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: 邢武彪
 * Date: 2021-02-09
 * Time: 16:28
 */
@Component
public class UserConfig {

    @Bean
    @ConfigurationProperties(prefix = "connection")
    public User user() {
        return new User();
    }
}
