package util;

import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeAliasRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * spring-engine
 * 2015/9/4 14:38
 */
public class MybatisInterceptorUtil {
    private static SqlSessionFactoryBean sqlSessionFactoryBean;

    private MybatisInterceptorUtil() {
    }

    public static void setSqlSessionFactoryBean(SqlSessionFactoryBean sqlSessionFactoryBean) {
        if(sqlSessionFactoryBean == null) {
            sqlSessionFactoryBean = sqlSessionFactoryBean;
        }
    }

    public static void dynamicAddInterceptor(String typeClass) {
        try {
            SqlSessionFactory e = sqlSessionFactoryBean.getObject();
            Configuration configuration = e.getConfiguration();
            TypeAliasRegistry typeAliasRegistry = configuration.getTypeAliasRegistry();
            Interceptor interceptor = (Interceptor)resolveClass(typeClass, typeAliasRegistry).newInstance();
            configuration.addInterceptor(interceptor);
        } catch (Exception e) {
            throw new RuntimeException("DynamicAddInterceptor error", e);
        }
    }

    private static Class<?> resolveClass(String alias, TypeAliasRegistry typeAliasRegistry) {
        if(alias == null) {
            return null;
        } else {
            try {
                return resolveAlias(alias, typeAliasRegistry);
            } catch (Exception e) {
                throw new BuilderException("Error resolving class . Cause: " + e, e);
            }
        }
    }

    private static Class<?> resolveAlias(String alias, TypeAliasRegistry typeAliasRegistry) {
        return typeAliasRegistry.resolveAlias(alias);
    }

    public static void main(String[] args) {



    }
}
