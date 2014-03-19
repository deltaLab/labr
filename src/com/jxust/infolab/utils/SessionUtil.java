package com.jxust.infolab.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 
 * @author lumence<br>
 *         ������ǰsession����
 */
public class SessionUtil {

	private static HttpSession session;
	private static Logger log = Logger.getLogger(SessionUtil.class);
	private SessionUtil() {

	}

	public static HttpSession getSession(HttpServletRequest req) {
		if (session == null) {
			session =req.getSession();
		}
		return session;
	}
	
	public static void set(String attr,Object value) {
		if(session!=null){
			session.setAttribute(attr, value);
		}else{
			log.error("session is null, getSession method should be invocked before");
		}
	}
	
	public static Object get(String attr){
		Object value=null;
		if(session!=null){
			try {
				value = session.getAttribute(attr);
			} catch (Exception e) {
				log.info("session out of date");
				e.printStackTrace();
			}
		}
		return value;
	}
}