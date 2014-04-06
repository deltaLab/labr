package com.jxust.infolab.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
	public static String uploadPath = "/files/upload";
	/**
	 * 图片缩放尺寸
	 */
	public static Integer[][] pictureSizes={};
	private static Logger log = Logger.getLogger(Constants.class);
	public static Constants instance = new Constants();

	public Constants() 
	{
		Properties prop = new Properties();

		try {
			URL configPath = this.getClass().getResource("/");
			log.info("配置文件应该放在这里："+configPath);
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream("config.properties");
			if (is != null) {
				prop.load(is);
			} else {
				prop.load(this.getClass().getClassLoader()
						.getResourceAsStream("/config.properties"));
			}
			String sUploadPath = prop.getProperty("uploadPath");
			if (sUploadPath != null) {
				uploadPath = sUploadPath;
			}

			String pictureSize = prop.getProperty("pictureSizes");
			if (pictureSize != null) {
				String[] picSizes = pictureSize.split(",");
				List<Integer[]> list = new ArrayList<Integer[]>();
				for (String size : picSizes) {
					if (size.contains("x")) {
						String xy[] = size.split("x");
						if (xy[0].matches("\\d+") && xy[1].matches("\\d+")) {
							list.add(new Integer[] { Integer.parseInt(xy[0]),
									Integer.parseInt(xy[0]) });
						}
					}
				}
				pictureSizes = new Integer[list.size()][2];
				list.toArray(pictureSizes);
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}
}
