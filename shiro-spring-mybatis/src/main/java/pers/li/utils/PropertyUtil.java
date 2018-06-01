package pers.li.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zkl
 * @date 2014-7-17 下午3:48:06
 * @version 1.0
 */
public class PropertyUtil {

	static Logger logger = LoggerFactory.getLogger(PropertyUtil.class.getName());

	public static final String DEFAULT_PROPERTY_FILE = "ApplicationResources.properties";

	private static Properties props = new Properties();

	static {
		load(DEFAULT_PROPERTY_FILE);
	}

	private static void load(String name) {
		InputStream is = PropertyUtil.class.getResourceAsStream("/" + name);
		try {
			try {
				props.load(is);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				// ignore
			}
		}
	}

	public static String getProperty(String key) {
		String result = props.getProperty(key);
		if (result != null && result.startsWith("@")) {
			result = getProperty(result.substring(1));
		}
		return result;
	}

	public static String getProperty(String key, String defaultValue) {
		String result = props.getProperty(key, defaultValue);
		if (result != null && result.startsWith("@")) {
			result = getProperty(result.substring(1), defaultValue);
		}
		return result;
	}

}
