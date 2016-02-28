package org.springframework.aop.service;

/**
 * fuquanemail@gamil.com
 * Date: 14-6-25 下午2:49
 */
public interface UserService {

    public boolean save();

    public void delete();

    public void update();

    public void sayBefore(String param ) ;

    void sayAfterReturning();

    void after(String str);



}
