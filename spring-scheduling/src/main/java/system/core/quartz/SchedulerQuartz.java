package system.core.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerContext;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.utils.ConnectionProvider;
import org.quartz.utils.DBConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;
import system.core.quartz.config.QuartzConfigServer;
import system.core.util.SpringContextHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * fuquanemail@gmail.com 2016/3/11 16:20
 * description:
 * 1.0.0
 */
@Component("schedulerQuartz")
public class SchedulerQuartz {

    private QuartzConfigServer quartzConfigServer;

    @Autowired
    private QuartzManager quartzManager;

    public void setQuartzConfigServer(QuartzConfigServer quartzConfigServer) {
        this.quartzConfigServer = quartzConfigServer;
    }

    public void afterPropertiesSet() throws Exception {
        if (null != quartzConfigServer) {
            Properties config = quartzConfigServer.getQuartzProperties();
            Properties quartzProperties = (Properties) config.clone();
            String applicationName = quartzConfigServer.getApplicationName();
            String instanceName = config.getProperty("org.quartz.scheduler.instanceName");
            instanceName = applicationName + instanceName;
            quartzProperties.setProperty("org.quartz.scheduler.instanceName", instanceName);

            Map<String, DataSource> dataSourceMap =
                    SpringContextHolder.applicationContext.getBeansOfType(DataSource.class);

            if (dataSourceMap == null || dataSourceMap.isEmpty()) {
                System.err.println("没有配置数据源");
                return;
            }
            Set<Map.Entry<String, DataSource>> set = dataSourceMap.entrySet();
            DataSource dataSource = null;
            String dataSourceKey = null;
            for (Map.Entry<String, DataSource> entry : set) {
                dataSource = entry.getValue();
                dataSourceKey = entry.getKey();
            }
            if (null == dataSource || null == dataSourceKey) {
                throw new RuntimeException("数据源配置有误");
            }
            // ---------------------------
            quartzProperties.setProperty("org.quartz.jobStore.dataSource", dataSourceKey);
            // 这里同下面的  DBConnectionManager.getInstance()会冲突，初始化2次

            final DataSource source = dataSource;
            DBConnectionManager.getInstance().addConnectionProvider(dataSourceKey, new ConnectionProvider() {
                @Override
                public Connection getConnection() throws SQLException {
                    System.err.println("数据源 getConnection");
                    return DataSourceUtils.getConnection(source);
                }

                @Override
                public void shutdown() throws SQLException {
                    System.err.println("数据源 shutdown");

                }

                @Override
                public void initialize() throws SQLException {
                    System.err.println("数据源 initialize");
                }
            });


            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(quartzProperties);
            quartzConfigServer.setStdSchedulerFactory(stdSchedulerFactory);
            if (quartzConfigServer.isAutoStartup()) {
                Scheduler scheduler = stdSchedulerFactory.getScheduler();
                scheduler.getContext().put("org.quartz.jobStore.dataSource", dataSourceKey);
                scheduler.start();

                SchedulerContext schedulerContext = scheduler.getContext();
                System.err.println(schedulerContext);

                System.err.println("Quartz [" + quartzConfigServer.getConfigServerKey() + "] 自动启动");
                quartzManager.setScheduler(scheduler);
            }

        }

    }
}
