package com.mygit.apiimpl;

import com.mygit.mavenconfigapi.service.InterfaceApi;

/**
 * Description:
 *
 * @author: 邢武彪
 * Date: 2021-02-22
 * Time: 0:01
 */
public class ApiImpl implements InterfaceApi {

    @Override
    public void proceed() {
        System.out.println("api 方法实现");
    }
}
