package com.jxust.infolab.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author lumence<br>
 *         web系统配置文件的读取
 * 
 */
public class Constants {
	/**
	 * 文件上传存放目录
	 */
	public static String uploadPath = "E:/labr/upload";
	private static Logger log = Logger.getLogger(Constants.class);
	{
		Properties prop = new Properties();

		try {
			prop.load(Constants.class.getClassLoader().getResourceAsStream(
					"config.properties"));

			String sUploadPath = prop.getProperty("uploadPath");
			if (sUploadPath != null) {
				uploadPath = sUploadPath;
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
