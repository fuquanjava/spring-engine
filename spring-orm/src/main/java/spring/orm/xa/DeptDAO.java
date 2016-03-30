package spring.orm.xa;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import spring.orm.domain.DeptDO;

import javax.annotation.Resource;

/**
 * fuquanemail@gmail.com 2016/3/30 11:59
 * description:
 * 1.0.0
 */
@Repository("deptDAO")
public class DeptDAO {

    @Resource(name = "sqlSessionTemplateDept")
    private SqlSessionTemplate sqlSessionTemplateDept;

    /**
     *
     * @param deptDO
     * @param throwExp 模拟抛出异常
     * @return
     */
    public int add(DeptDO deptDO , boolean throwExp) {
        int rows = sqlSessionTemplateDept.insert("DeptDAO.save", deptDO);
        if(throwExp){
            throw new RuntimeException("保存失败");
        }
        return rows;
    }

    public DeptDO findById(int id) {
        return sqlSessionTemplateDept.selectOne("DeptDAO.findById", id);
    }

}
