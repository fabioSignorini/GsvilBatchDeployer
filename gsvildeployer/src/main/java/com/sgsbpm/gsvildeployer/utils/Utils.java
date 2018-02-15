package com.sgsbpm.gsvildeployer.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author gs01491
 * 
 */
public class Utils
{
	private static final Logger	logger	= Logger.getLogger(Utils.class.getName());

	public static Properties getPropertiesFromFile(String fileName, Properties prop, boolean useClassLoader) throws FileNotFoundException
	{
		InputStream input = null;
		try
		{
			if (useClassLoader)
			{
				input = Utils.class.getClassLoader().getResourceAsStream(fileName);
			}
			else
			{
				File fileToRead = new File(fileName);
				input = new FileInputStream(fileToRead);
			}
			if (input == null)
			{
				throw new FileNotFoundException();
			}
			prop.load(input);
		}
		catch (IOException e)
		{
			logger.error("Problem occurred while loading the properties file " + fileName + ": " + e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			IOUtils.closeQuietly(input);
		}
		return prop;
	}
	
	public static void writePropertiesToFile(String filePath, Properties properties)
	{
		OutputStream out = null;
		try
		{
			File fileURL = new File(filePath);
			out = new FileOutputStream(fileURL);
			properties.store(out, null);
			out.flush();
		}
		catch (Exception e) 
		{
			logger.error("Problem occurred while writing the properties file " + filePath + ": " + e.getMessage());
			e.printStackTrace();
		}
		finally 
		{
			IOUtils.closeQuietly(out);
		}
	}
	
	public static void updatePropertiesInFile(String filePath, Map<String, String> propertiesMap) throws IllegalArgumentException
	{
		if (StringUtils.isBlank(filePath) || propertiesMap == null)
		{
			throw new IllegalArgumentException();
		}
		
		File propertiesFile = new File(filePath);
		PropertiesConfiguration config = null;
		try 
		{
			config = new PropertiesConfiguration(propertiesFile);
			for (Map.Entry<String, String> entry : propertiesMap.entrySet())
			{
				config.setProperty(entry.getKey(), entry.getValue());
			}
			config.save();
		}
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static String linearize(String input)
	{
		String newLine = System.getProperty("line.separator");
		return StringUtils.replace(input, newLine, " ");
	}
	
	public static String wrapNoteMessages(String message, boolean error)
	{
		if (error) 
		{
			return "******* WARNING *******\n" + message + "\n***********************";
		}
		else
		{
			return "******* SUCCESS *******\n" + message + "\n***********************";
		}
	}

}
