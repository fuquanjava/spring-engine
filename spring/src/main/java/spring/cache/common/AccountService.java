package spring.cache.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import spring.cache.domain.Account;

import java.util.Optional;


/**
 * fuquanemail@gmail.com 2016/9/26 9:21
 * description:
 *
 *
 *  @CachePut 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
 *
 *   @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存.
 *
 *   @CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空.
 */
@Service("accountService")
public class AccountService {

    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private CacheContext<Account> accountCacheContext;

    public Account getAccountByName1(String accountName) {
        Account result = accountCacheContext.get(accountName);
        if (result != null) {
            logger.info("get from cache... {}", accountName);
            return result;
        }

        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        Account account = accountOptional.get();
        accountCacheContext.addOrUpdateCache(accountName, account);
        return account;
    }

    /**
     * 使用了一个缓存名叫 accountCache,
     * 方法内部实现不考虑缓存逻辑，直接实现业务
     */
    @Cacheable(value = "accountCache")
    public Account getAccountByName2(String accountName) {

        Optional<Account> accountOptional = getFromDB(accountName);

        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        return accountOptional.get();
    }

    @CacheEvict(value = "accountCache", key = "#account.getName()")
    public void updateAccount(Account account) {
        updateDB(account);
    }

    @CacheEvict(value = "accountCache", allEntries = true)
    public void reload() {
        logger.info("reload cache");
    }





    private void updateDB(Account account) {
        logger.info("real update db...{}", account.getName());
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        return Optional.ofNullable(new Account(accountName));
    }
}
