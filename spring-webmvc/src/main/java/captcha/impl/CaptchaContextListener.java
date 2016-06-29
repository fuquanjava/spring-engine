package captcha.impl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class CaptchaContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// 需要关闭
		CaptchaServlet.shutdownPruducer();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 需要初始化图片生产
		CaptchaServlet.startPruducer();
	}

}
