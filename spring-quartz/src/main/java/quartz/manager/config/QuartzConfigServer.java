package quartz.manager.config;

import org.quartz.impl.StdSchedulerFactory;

import java.util.Properties;

public class QuartzConfigServer {

	private String configServerKey;

	private Properties quartzProperties;

	private StdSchedulerFactory stdSchedulerFactory;

	private String dataSourceKey;

	private boolean autoStartup = true;

	private boolean isDefault;

	public String getConfigServerKey() {
		return configServerKey;
	}

	public void setConfigServerKey(String configServerKey) {
		this.configServerKey = configServerKey;
	}

	public Properties getQuartzProperties() {
		return quartzProperties;
	}

	public void setQuartzProperties(Properties quartzProperties) {
		this.quartzProperties = quartzProperties;
	}

	public boolean getAutoStartup() {
		return autoStartup;
	}

	public void setAutoStartup(boolean autoStartup) {
		this.autoStartup = autoStartup;
	}

	public StdSchedulerFactory getStdSchedulerFactory() {
		return stdSchedulerFactory;
	}

	public void setStdSchedulerFactory(StdSchedulerFactory stdSchedulerFactory) {
		this.stdSchedulerFactory = stdSchedulerFactory;
	}

	public String getDataSourceKey() {
		return dataSourceKey;
	}

	public void setDataSourceKey(String dataSourceKey) {
		this.dataSourceKey = dataSourceKey;
	}

	public boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "QuartzConfigServer [configServerKey=" + configServerKey + ", quartzProperties=" + quartzProperties + ", stdSchedulerFactory="
				+ stdSchedulerFactory + ", dataSourceKey=" + dataSourceKey + ", autoStartup=" + autoStartup + ", isDefault=" + isDefault + "]";
	}

}
