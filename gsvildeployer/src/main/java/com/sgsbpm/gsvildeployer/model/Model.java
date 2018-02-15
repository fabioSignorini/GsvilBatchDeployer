/**
 * 
 */
package com.sgsbpm.gsvildeployer.model;

import static com.sgsbpm.gsvildeployer.utils.CommonConstants.BATCH_COMPILE_DEPLOY_REGISTER;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.BATCH_LIST;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.CONFIG_FILENAME;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.MISSING_INFO;

import java.io.FileNotFoundException; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Properties;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import org.apache.commons.lang3.StringUtils;

import com.sgsbpm.gsvildeployer.beans.TableBean;
import com.sgsbpm.gsvildeployer.utils.Utils;

/**
 * @author gs01491
 *
 */
public class Model extends Observable implements TableModel 
{
	final String[] columnNames 								= {"BATCH", "LAST COMPILE TIMESTAMP", "LAST JAR DEPLOY TIMESTAMP", "LAST CONFIG DEPLOY TIMESTAMP"};
	
	private final static String JAVA_TEMP_DIR 				= System.getProperty("java.io.tmpdir");
	
	private List<TableBean> rows 							= null;
	
	private Properties compileDeployRegisterProperties 		= null;
	
	private Properties configProperties 					= null;
	
	public Model()
	{
		this.rows 											= new ArrayList<TableBean>();
		final String LAST_COMPILE_TIMESTAMP_SUFFIX 			= "_LAST_COMPILE_TIMESTAMP";
		final String LAST_COMPILE_RESULT_SUFFIX				= "_LAST_COMPILE_RESULT";
		final String LAST_JAR_DEPLOY_TIMESTAMP_SUFFIX 		= "_LAST_JAR_DEPLOY_TIMESTAMP";
		final String LAST_JAR_DEPLOY_RESULT_SUFFIX 			= "_LAST_JAR_DEPLOY_RESULT";
		final String LAST_CONFIG_DEPLOY_RESULT_SUFFIX 		= "_LAST_CONFIG_DEPLOY_RESULT";
		final String LAST_CONFIG_DEPLOY_TIMESTAMP_SUFFIX 	= "_LAST_CONFIG_DEPLOY_TIMESTAMP";
		compileDeployRegisterProperties 					= new Properties();
		configProperties									= new Properties();
		List<String> batchList								= new ArrayList<String>();

		try 
		{
			compileDeployRegisterProperties = Utils.getPropertiesFromFile(JAVA_TEMP_DIR + BATCH_COMPILE_DEPLOY_REGISTER, compileDeployRegisterProperties, false);
			configProperties 				= Utils.getPropertiesFromFile(CONFIG_FILENAME, configProperties, true);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

		batchList = Arrays.asList(StringUtils.split(configProperties.getProperty(BATCH_LIST), ","));

		for (String batch : batchList)
		{
			final String LAST_COMPILE_TIMESTAMP 		= compileDeployRegisterProperties.getProperty(batch.concat(LAST_COMPILE_TIMESTAMP_SUFFIX));
			final String LAST_COMPILE_RESULT 			= compileDeployRegisterProperties.getProperty(batch.concat(LAST_COMPILE_RESULT_SUFFIX));
			final String LAST_JAR_DEPLOY_TIMESTAMP 		= compileDeployRegisterProperties.getProperty(batch.concat(LAST_JAR_DEPLOY_TIMESTAMP_SUFFIX));
			final String LAST_JAR_DEPLOY_RESULT 		= compileDeployRegisterProperties.getProperty(batch.concat(LAST_JAR_DEPLOY_RESULT_SUFFIX));
			final String LAST_CONFIG_DEPLOY_RESULT 		= compileDeployRegisterProperties.getProperty(batch.concat(LAST_CONFIG_DEPLOY_RESULT_SUFFIX));
			final String LAST_CONFIG_DEPLOY_TIMESTAMP 	= compileDeployRegisterProperties.getProperty(batch.concat(LAST_CONFIG_DEPLOY_TIMESTAMP_SUFFIX));
			rows.add(new TableBean(batch, LAST_COMPILE_RESULT != null ? LAST_COMPILE_RESULT : MISSING_INFO, LAST_JAR_DEPLOY_RESULT != null ? LAST_JAR_DEPLOY_RESULT : MISSING_INFO, LAST_CONFIG_DEPLOY_RESULT != null ? LAST_CONFIG_DEPLOY_RESULT : MISSING_INFO, LAST_COMPILE_TIMESTAMP != null ? LAST_COMPILE_TIMESTAMP : MISSING_INFO, LAST_JAR_DEPLOY_TIMESTAMP != null ? LAST_JAR_DEPLOY_TIMESTAMP : MISSING_INFO, LAST_CONFIG_DEPLOY_TIMESTAMP != null ? LAST_CONFIG_DEPLOY_TIMESTAMP : MISSING_INFO));
		}
	}
	
	public int getColumnCount() 
	{
		return this.columnNames.length;
	}

	public int getRowCount() 
	{
		return this.rows != null ? this.rows.size() : 0;
	}

	public Object getValueAt(int row, int col) 
	{
		TableBean t = this.rows.get(row);
		switch (col) 
		{
		case 0:
			return t.getBatchName();
			
		case 1:
			return t.getLastCompileTimestamp();
			
		case 2:
			return t.getLastDeployJarTimestamp();
			
		case 3:
			return t.getLastDeployConfigTimestamp();

		default:
			return "";
		}
	}
	
	public String getColumnName(int col) 
	{
		return columnNames[col];
	}

	public Class<String> getColumnClass(int col) 
	{
//		return getValueAt(0, col).getClass();
		return String.class;
	}
	
	public boolean isCellEditable(int row, int col)
	 {
		return false;
	 }
	
//	public void setValueAt(Object value, int row, int col) 
//	{
//		fireTableDataChanged();
//	}

	public void setRows(List<TableBean> newRows, Boolean orderColumnsByDate) 
	{
		updateRowsAvoidingDuplicates(newRows);
		setChanged();
		notifyObservers(orderColumnsByDate);
	}
	
	public void setNotesMessage(String message)
	{
		setChanged();
		notifyObservers(message);
	}
	
	public List<TableBean> getRows() 
	{
		return rows;
	}
	
	private void updateRowsAvoidingDuplicates(List<TableBean> newRows)
	{
		Set<TableBean> oldRowsAsSet = new HashSet<TableBean>(this.rows);
		Set<TableBean> newRowsAsSet = new HashSet<TableBean>(newRows);
		
		oldRowsAsSet.removeAll(newRowsAsSet);
		oldRowsAsSet.addAll(newRowsAsSet);
		this.rows = new ArrayList<TableBean>(oldRowsAsSet);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		// TODO Auto-generated method stub
		;
	}

	public void addTableModelListener(TableModelListener l) 
	{
		// TODO Auto-generated method stub
		;
	}

	public void removeTableModelListener(TableModelListener l)
	{
		// TODO Auto-generated method stub
		;
	}

}
