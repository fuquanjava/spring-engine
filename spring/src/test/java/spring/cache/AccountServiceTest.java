package spring.cache;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import spring.BaseTest;
import spring.cache.domain.Account;
import spring.cache.service.AccountService;


/**
 * fuquanemail@gmail.com 2016/9/26 9:25
 * description:
 */
public class AccountServiceTest extends BaseTest{

    @Autowired
    private AccountService accountService;


    @Test
    public void testGetAccountByName1() throws Exception {
        accountService.getAccountByName1("张三丰");
        accountService.getAccountByName1("张三丰");
        accountService.getAccountByName1("张三丰");
    }

    @Test
    public void testGetAccountByName2() throws Exception {
        accountService.getAccountByName2("张三丰");
        accountService.getAccountByName2("张三丰");
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account1 = accountService.getAccountByName2("accountName1");
        logger.info(account1.toString());
        Account account2 = accountService.getAccountByName2("accountName2");
        logger.info(account2.toString());

        account2.setId(123);
        accountService.updateAccount(account2);

        // account1会走缓存
        account1 = accountService.getAccountByName2("accountName1");
        logger.info(account1.toString());
        // account2会查询db
        account2 = accountService.getAccountByName2("accountName2");
        logger.info(account2.toString());

    }

}