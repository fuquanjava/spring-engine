package spring.orm.mybatis.dao.scanner;

import org.apache.ibatis.annotations.Param;
import spring.orm.domain.UserDO;

/**
 * fuquanemail@gmail.com 2016/3/28 13:00
 * description:
 * 1.0.0
 */
public interface UserDAOScanner {
    /**
     *  注意：@Param 标签必须有，标注参数 id的key, 因为mybatis 通过OGNL 取值，没有key,会去 Integer 中查找，当然无法找到。
     * @param id
     * @return
     */
    UserDO findById(@Param("id") Integer id);

    UserDO findById2(@Param("id") Integer id);

    int updateById(UserDO user);

}
