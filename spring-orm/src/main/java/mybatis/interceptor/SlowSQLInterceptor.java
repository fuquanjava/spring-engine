package mybatis.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.SqlParserUtil;

import java.util.Properties;

/**
 * spring-engine
 * 2015/9/4 14:49
 */

@Intercepts({
        @Signature(
            type = Executor.class,
            method = "update",
            args = {MappedStatement.class, Object.class}
        ),
        @Signature(
            type = Executor.class,
            method = "query",
            args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        )
})
public class SlowSQLInterceptor implements Interceptor {
    private  final Logger log = LoggerFactory.getLogger(this.getClass());
    private static long limit;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        long start =System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();

        long time = end - start;
        if(time >= limit){
            Object [] args = invocation.getArgs();
            log.debug(" invocation args = {}", args);
            MappedStatement mappedStatement = (MappedStatement) args[0];
            Object parameter = args[1];
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String interceptSql = boundSql.getSql();

            String sql = SqlParserUtil.handleSql(interceptSql, mappedStatement, boundSql);
            String sqlId = mappedStatement.getId();
            this.log.debug("Slow query sql: " + sql + ", use time " + time + "ms" + " from method " + sqlId);

        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return null;
    }

    @Override
    public void setProperties(Properties properties) {
        String limitTime  = properties.getProperty("limit");
        try {
            limit = Integer.parseInt(limitTime);
        }catch (Exception e){
            log.error("类型转换异常, mybatis Interceptor 配置有误.");
        }
    }
}
