package spring.orm.mybatis.dao.template.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.orm.domain.UserDO;
import spring.orm.mybatis.dao.template.UserDAOTemplate;

/**
 * fuquanemail@gmail.com 2016/3/29 11:17
 * description:
 * 1.0.0
 */
@Repository(value = "userDAOTemplate")
public class UserDAOTemplateImpl implements UserDAOTemplate {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @Override
    public UserDO findById(int id) {
        return sqlSessionTemplate.selectOne("userDAO.findById",id);
    }
}
