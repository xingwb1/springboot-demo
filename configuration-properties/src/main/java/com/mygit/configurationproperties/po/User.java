package com.mygit.configurationproperties.po;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: 邢武彪
 * Date: 2021-02-09
 * Time: 15:50
 * @author 邢武彪
 */
@Data
//@Component
//@ConfigurationProperties(prefix = "connection")
public class User {
    private String userName;
    private String passWord;
}
