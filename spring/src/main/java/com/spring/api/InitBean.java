package com.spring.api;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * fuquanemail@gmail.com
 * 2015/10/8 14:57
 */
@Component("initBean")
public class InitBean implements InitializingBean {
    private String attr = null;
    public InitBean(){
        this.attr = "属性attr.";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(attr.toString()+",初始化完毕...执行业务操作");
    }
}
