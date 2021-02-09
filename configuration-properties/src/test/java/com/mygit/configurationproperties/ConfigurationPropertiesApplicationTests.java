package com.mygit.configurationproperties;

import com.mygit.configurationproperties.po.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class ConfigurationPropertiesApplicationTests {

    @Autowired
    private User user;

    @Test
    void configurationProperties() {
        log.info(user.getUserName());
        log.info(user.getPassWord());
    }

}
