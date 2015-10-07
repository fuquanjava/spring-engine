package quartz.manager.source;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quartz.manager.SpringContextHolder;
import quartz.manager.config.QuartzConfigServer;
import quartz.manager.job.InitialJob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author zhihongp
 *
 */
public class DynamicCreateQuartzManager {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private List<QuartzConfigServer> quartzConfigServerList;

	private List<InitialJob> initialJobList;

	private DynamicQuartz dynamicQuartz;

	public void setQuartzConfigServerList(List<QuartzConfigServer> quartzConfigServerList) {
		this.quartzConfigServerList = quartzConfigServerList;
	}

	public void setInitialJobList(List<InitialJob> initialJobList) {
		this.initialJobList = initialJobList;
	}

	public void setDynamicQuartz(DynamicQuartz dynamicQuartz) {
		this.dynamicQuartz = dynamicQuartz;
	}

	public void initCreateQuartz() {
		registerQuartScheduler();
	}

	private void registerQuartScheduler() {
		Map<String, QuartzConfigServer> targetQuartzConfigServerMap = new HashMap<String, QuartzConfigServer>();
		List<QuartzConfigServer> quartzConfigServerList = new ArrayList<QuartzConfigServer>();
		List<InitialJob> initialJobList = new ArrayList<InitialJob>();
		QuartzConfigServer defaultQuartzConfigServer = null;

		if (this.initialJobList == null || this.initialJobList.isEmpty()) {
			Map<String, InitialJob> initialJobMap = SpringContextHolder.applicationContext.getBeansOfType(InitialJob.class);

			if (initialJobMap != null && !initialJobMap.isEmpty()) {
				for (Entry<String, InitialJob> en : initialJobMap.entrySet()) {
					initialJobList.add(en.getValue());
				}
			}
		} else {
			initialJobList = this.initialJobList;
		}
		
		if (this.quartzConfigServerList == null || this.quartzConfigServerList.isEmpty()) {
			Map<String, QuartzConfigServer> quartzConfigServerMap = SpringContextHolder.applicationContext.getBeansOfType(QuartzConfigServer.class);

			if (quartzConfigServerMap != null && !quartzConfigServerMap.isEmpty()) {
				for (Entry<String, QuartzConfigServer> en : quartzConfigServerMap.entrySet()) {
					quartzConfigServerList.add(en.getValue());
				}
			}
		} else {
			quartzConfigServerList = this.quartzConfigServerList;
		}
		
		for (QuartzConfigServer quartzConfigServer : quartzConfigServerList) {
			String configServerKey = quartzConfigServer.getConfigServerKey();
			targetQuartzConfigServerMap.put(configServerKey, quartzConfigServer);
			boolean isDefault = quartzConfigServer.getIsDefault();

			if (isDefault) {
				defaultQuartzConfigServer = quartzConfigServer;
			}
		}
		
		dynamicQuartz.setTargetQuartzConfigServerMap(targetQuartzConfigServerMap);
		dynamicQuartz.setDefaultTargetQuartzSource(defaultQuartzConfigServer);
		dynamicQuartz.setInitialJobList(initialJobList);
		
		try {
			dynamicQuartz.afterPropertiesSet();
		} catch (Exception e) {
			log.error("Initialize quartz schedule error", e);
		}
	}

}
