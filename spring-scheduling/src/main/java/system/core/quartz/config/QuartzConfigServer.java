package system.core.quartz.config;

import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

/**
 * fuquanemail@gmail.com 2016/3/11 15:50
 * description:
 * 1.0.0
 */
public class QuartzConfigServer {

    /**
     * 目前全局数据库管理，暂不支持动态化
     */
    private String configServerKey = "quartz";

    /**
     * 应用名(quart任务前缀, 需全局唯一)
     */
    private String applicationName;

    private Properties quartzProperties;

    private StdSchedulerFactory stdSchedulerFactory;

    private boolean autoStartup = true;

    public String getConfigServerKey() {
        return configServerKey;
    }

    public void setConfigServerKey(String configServerKey) {
        this.configServerKey = configServerKey;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Properties getQuartzProperties() {
        return quartzProperties;
    }

    public void setQuartzProperties(Properties quartzProperties) {
        this.quartzProperties = quartzProperties;
    }

    public StdSchedulerFactory getStdSchedulerFactory() {
        return stdSchedulerFactory;
    }

    public void setStdSchedulerFactory(StdSchedulerFactory stdSchedulerFactory) {
        this.stdSchedulerFactory = stdSchedulerFactory;
    }

    public boolean isAutoStartup() {
        return autoStartup;
    }

    public void setAutoStartup(boolean autoStartup) {
        this.autoStartup = autoStartup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("QuartzConfigServer{");
        sb.append("configServerKey='").append(configServerKey).append('\'');
        sb.append(", applicationName='").append(applicationName).append('\'');
        sb.append(", quartzProperties=").append(quartzProperties);
        sb.append(", stdSchedulerFactory=").append(stdSchedulerFactory);
        sb.append(", autoStartup=").append(autoStartup);
        sb.append('}');
        return sb.toString();
    }
}
