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

import org.apache.log4j.Logger;

import com.jxust.infolab.utils.SessionUtil;

/**
 * Servlet Filter implementation class FileRouter<br>
 * 访问文件路由类,每一次请求首先被这个类捕获到
 */
@WebFilter(description = "访问路径判断", urlPatterns = { "/*" })
public class FileRouter implements Filter {
	private static Logger log = Logger.getLogger(FileRouter.class);
	/**
	 * 浏览器名称
	 */
	private final String BRS = "browser";
	/**
	 * 浏览器版本
	 */
	private final String BRVS = "browserVersion";
	/**
	 * 某个浏览器某版本应该访问的位置
	 */
	private final String FAKE_ROOT="root";

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
		String userAgent = req.getHeader("user-agent");//获取浏览器相关信息
		log.info("url=" + url);
		log.info("userAgent=" + userAgent);
		// 获取浏览器相关内容
		if (SessionUtil.get(BRS) == null&&url.equals("/index.jsp")) {
			SessionUtil.getSession(req);
			userAgent = userAgent.toLowerCase();
			if (userAgent.contains("chrome")) {//Chrome浏览器
				SessionUtil.set(BRS, "chorme");
				SessionUtil.set(FAKE_ROOT, "chorme");
			} else if (userAgent.contains("firefox")) {//FF浏览器
				SessionUtil.set(BRS, "firefox");
				SessionUtil.set(FAKE_ROOT, "firefox");
			} else if (userAgent.contains("msie")) {//IE浏览器
				SessionUtil.set(BRS, "ie");
				String[] detailInfos = userAgent.split(";");
				for(String detail:detailInfos){
					if(detail.contains("msie")){//获取IE版本
						String [] versions = detail.split(" ");
						for(String v:versions){
							if(v.matches("\\d{1,2}+(.[\\d]+){0,1}")){
								float version = Float.parseFloat(v);
								if(version<7){
									SessionUtil.set(FAKE_ROOT, "ie6");									
								}else if(version<9){
									SessionUtil.set(FAKE_ROOT, "ie78");
								}else{
									SessionUtil.set(FAKE_ROOT, "ie9+");
								}
								SessionUtil.set(BRVS, version);
								log.info("ie "+version);
								break;
							}
						}
						break;
					}
				}
			}
		}else{//没有登录
			//TODO 第一次进入，但没有访问首页
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
