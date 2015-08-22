package session.filter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import session.impl.SessionManager;
import session.wrapper.SessionHttpServletRequestWrapper;
import util.CoreContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * spring-demo 2015/7/24 22:04
 * fuquanemail@gmail.com
 */
public class CacheSessionFilter implements Filter {
    static Logger LOG = LoggerFactory.getLogger(CacheSessionFilter.class);
    public static String[] IGNORE_SUFFIX = new String[0];
    private SessionManager sessionManager;
    private String sessionManagerName = "sessionManager";

    @Override
    public void init(FilterConfig fc) throws ServletException {
        String ignore_suffix = fc.getInitParameter("ignore_suffix");
        if (!"".equals(ignore_suffix)) {
            IGNORE_SUFFIX = fc.getInitParameter("ignore_suffix").split(",");
        }

        String smn = fc.getInitParameter("sessionManagerName");
        if(StringUtils.isNotBlank(smn)){
            sessionManagerName = smn;
        }

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain) throws IOException, ServletException {

        LOG.error(" doFilter ===================================== ");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (!this.shouldFilter(request)) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            this.sessionManager = (SessionManager) CoreContextHolder.getContext().getBean(sessionManagerName);

            SessionHttpServletRequestWrapper requestWrapper = new SessionHttpServletRequestWrapper(request, response, this.sessionManager);
            try {
                chain.doFilter(requestWrapper, servletResponse);
            } finally {
                LOG.info(" =================over==================== ");
            }

        }
    }

    @Override
    public void destroy() {

    }

    private boolean shouldFilter(HttpServletRequest request) {
        String uri = request.getRequestURI().toLowerCase();
        String[] arr$ = IGNORE_SUFFIX;
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String suffix = arr$[i$];
            if (uri.endsWith(suffix)) {
                return false;
            }
        }

        return true;
    }
}
