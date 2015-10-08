package quartz.manager.job;


import quartz.manager.config.QuartzConfigServer;
import quartz.manager.config.QuartzParameter;

public class InitialJob {

	private QuartzParameter quartzParameter = new QuartzParameter();

	private QuartzConfigServer quartzConfigServer;

	public QuartzParameter getQuartzParameter() {
		return quartzParameter;
	}

	public void setQuartzParameter(QuartzParameter quartzParameter) {
		this.quartzParameter = quartzParameter;
	}

	public QuartzConfigServer getQuartzConfigServer() {
		return quartzConfigServer;
	}

	public void setQuartzConfigServer(QuartzConfigServer quartzConfigServer) {
		this.quartzConfigServer = quartzConfigServer;
	}

	@Override
	public String toString() {
		return "InitialJob [quartzParameter=" + quartzParameter + ", quartzConfigServer=" + quartzConfigServer + "]";
	}

}
