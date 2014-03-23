package com.jxust.infolab.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.jxust.infolab.utils.SessionUtil;

/**
 * Servlet Filter implementation class FileRouter<br>
 * 项目基础过滤器
 */
@WebFilter(description = "路径过滤器", urlPatterns = { "*" })
public class FileRouter implements Filter {
	private static Logger log = Logger.getLogger(FileRouter.class);
	/**
	 * 浏览器
	 */
	private final String BRS = "browser";
	/**
	 * 浏览器版本
	 */
	private final String BRVS = "browserVersion";
	/**
	 * 项目浏览器根路径
	 */
	private final String FAKE_ROOT = "root";

	/**
	 * Default constructor.
	 */
	public FileRouter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getServletPath();
		String userAgent = req.getHeader("user-agent");// 获取浏览器信息
		log.info("url=" + url);
		log.info("userAgent=" + userAgent);

		// 第一次进入
		if (SessionUtil.get(BRS) == null) {
			browserInit(req, userAgent);
		}
		if (url.endsWith(".svl")) {// 若是发生servlet请求
			// 获取servlet请求名
			String servlet = url.substring(url.lastIndexOf('/'));
			// 把servlet移动到根目录下
			req.getRequestDispatcher(servlet).forward(request, response);
			// 必须加返回，否则报错
			return;
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);

	}

	private void browserInit(HttpServletRequest req, String userAgent) {
		SessionUtil.getSession(req);
		userAgent = userAgent.toLowerCase();
		if (userAgent.contains("chrome")) {// Chrome浏览器
			SessionUtil.set(BRS, "chrome");
			SessionUtil.set(FAKE_ROOT, "chrome");
		} else if (userAgent.contains("firefox")) {// FF浏览器
			SessionUtil.set(BRS, "firefox");
			SessionUtil.set(FAKE_ROOT, "firefox");
		} else if (userAgent.contains("msie")) {// IE浏览器
			SessionUtil.set(BRS, "ie");
			String[] detailInfos = userAgent.split(";");
			for (String detail : detailInfos) {
				if (detail.contains("msie")) {
					String[] versions = detail.split(" ");
					for (String v : versions) {
						if (v.matches("\\d{1,2}+(.[\\d]+){0,1}")) {
							float version = Float.parseFloat(v);
							if (version < 7) {
								SessionUtil.set(FAKE_ROOT, "ie6");
							} else if (version < 9) {
								SessionUtil.set(FAKE_ROOT, "ie78");
							} else {
								SessionUtil.set(FAKE_ROOT, "ie9+");
							}
							SessionUtil.set(BRVS, version);
							log.info("ie " + version);
							break;
						}
					}
					break;
				}
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
