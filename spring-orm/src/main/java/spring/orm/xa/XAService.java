package spring.orm.xa;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.orm.domain.DeptDO;
import spring.orm.domain.UserDO;

/**
 * fuquanemail@gmail.com 2016/3/30 13:14
 * description:
 * 1.0.0
 */

@Service("XaService")
public class XAService {

    @Autowired
    private DeptDAO deptDAO;

    @Autowired
    private UserDAO userDAO;

    public DeptDO findDept(int id){
        return deptDAO.findById(id);
    }
    public UserDO findUser(int id){
        return userDAO.findById(id);
    }

    @Transactional(value = "deptTransactionManager")
    public void addDept(){
        DeptDO dept = findDept(35);
        DeptDO deptDO = new DeptDO();
        BeanUtils.copyProperties(dept, deptDO);
        deptDO.setId(null);
        int rows = deptDAO.add(deptDO,false);
        printResult(rows);
    }

    private void printResult(int rows) {
        if(rows > 0){
            System.err.println("操作成功");
        }else {
            System.err.println("操作失败");
        }
    }

    @Transactional(value = "userTransactionManager")
    public void updateUser(){
        UserDO user = findUser(1);
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        userDO.setRealname("admin");
        int rows = userDAO.updateById(userDO);
        printResult(rows);
    }

    /**
     * 抛出异常的时候,不能回滚 updateUser 的数据
     */
    @Transactional(value = "deptTransactionManager")
    public void saveOrUpdate(){
        this.updateUser();
        this.addDept();
    }


    /**
     * xapool 实现分布式事务
     */
    @Transactional(value = "transactionManager")
    public void saveOrUpdate2(){
        this.updateUser();
        this.addDept();
    }
}
