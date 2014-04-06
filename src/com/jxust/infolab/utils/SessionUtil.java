package com.jxust.infolab.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @author lumence<br>
 *         session 辅助类
 */
public class SessionUtil {

	private static HttpSession session;
	private static Logger log = Logger.getLogger(SessionUtil.class);

	private SessionUtil() {

	}

	public static HttpSession getSession(HttpServletRequest req) {
		session = req.getSession();
		return session;
	}
	
	public static void invalid(){
		session.invalidate();
	}

	public static void set(String attr, Object value,HttpServletRequest req) {
		session = req.getSession();
		if (session != null) {
			session.setAttribute(attr, value);
		} else {
			log.error("session is null, getSession method should be invocked before");
		}
	}

	public static Object get(String attr,HttpServletRequest req) {
		Object value = null;
		session = req.getSession();
		if (session != null) {
			try {
				value = session.getAttribute(attr);
			} catch (Exception e) {
				value = null;
				log.info("session out of date");
				e.printStackTrace();
			}
		}
		return value;
	}
}
