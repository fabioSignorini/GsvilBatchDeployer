package com.sgsbpm.gsvildeployer.controller;

import static com.sgsbpm.gsvildeployer.utils.CommonConstants.BATCH_COMPILE_DEPLOY_REGISTER;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.CONFIG_FILENAME;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPIF;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPIF_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPIF_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPIF_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVR;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVR_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVR_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVR_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVS;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVS_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVS_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.DSPVS_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.FDIGI;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.FDIGI_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.FDIGI_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.FDIGI_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.IMMLR;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.IMMLR_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.IMMLR_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.IMMLR_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.INDOC;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.INDOC_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.INDOC_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.INDOC_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.RXXML;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.RXXML_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.RXXML_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.RXXML_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.SVIMM;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.SVIMM_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.SVIMM_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.SVIMM_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.TXXML;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.TXXML_ANT_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.TXXML_DEPLOY_CONFIG_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.TXXML_DEPLOY_JAR_SCRIPT;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.WINSCP_COMMAND;
import static com.sgsbpm.gsvildeployer.utils.CommonConstants.MISSING_INFO;
import static com.sgsbpm.gsvildeployer.utils.Utils.getPropertiesFromFile;
import static com.sgsbpm.gsvildeployer.utils.Utils.updatePropertiesInFile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import com.sgsbpm.gsvildeployer.beans.AbstractTableBean;
import com.sgsbpm.gsvildeployer.beans.CompileTableBean;
import com.sgsbpm.gsvildeployer.beans.DeployConfigTableBean;
import com.sgsbpm.gsvildeployer.beans.DeployJarTableBean;
import com.sgsbpm.gsvildeployer.beans.DeployResult;
import com.sgsbpm.gsvildeployer.beans.TableBean;
import com.sgsbpm.gsvildeployer.model.Model;
import com.sgsbpm.gsvildeployer.view.View;

/**
 * @author gs01491
 *
 */
public class Controller implements ActionListener 
{
	private Model model 				= null;
	private View view   				= null;
	
	private final String JAVA_TEMP_DIR 	= System.getProperty("java.io.tmpdir");
	private Properties configProperties = new Properties();
	private String winscp_log 			= "";
	
	public Controller(Model model, View view) 
	{
		this.model = model;
		this.view = view;
		
		try 
		{
			configProperties = getPropertiesFromFile(CONFIG_FILENAME, configProperties, true);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		initView();
	}

	private void initView()
	{
//		TableRowSorter<TableModel> sorter = new TableRowSorter<>(view.getTable().getModel());
//		view.getTable().setRowSorter(sorter);
//		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
//		 
//		int columnIndexToSort = 1;
//		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.DESCENDING));
//		 
//		sorter.setSortKeys(sortKeys);
//		sorter.sort();
//		
//		view.getTable().setAutoCreateRowSorter(true);
		;
	}
	
	public void initController()
	{
		/**
		 * Set this controller as a listener for the events generated in the view
		 */
		this.view.getCompileAndDeployButton().addActionListener(this);
		this.view.getDeployConfigFilesButton().addActionListener(this);
	}

	/**
	 * Il controller interagisce con il model per realizzare la funzionalità richiesta ed aggiornare/modificare lo stato o i dati
	 */
	public void actionPerformed(ActionEvent e) 
	{
		/**
		 * 0. LIST OF SELECTED BATCH
		 */
		List<String> selectedBatchList 	= composeSelectedBatchList();
		List<TableBean> tableBeansList 	= new ArrayList<TableBean>();
		boolean deployJarFiles			= true;		
		
		if (e.getSource() == this.view.getCompileAndDeployButton())
		{
			/**
			 * 1. COMPILE
			 */
			this.model.setRows(doCompile(selectedBatchList), false);
		}
		else
		{
			deployJarFiles = false;
		}
		
		/**
		 * 2. DEPLOY
		 */
		List<TableBean> tableBeansListInstalled = new ArrayList<TableBean>();
		tableBeansList = doDeploy(selectedBatchList, deployJarFiles);
		for (TableBean tBean : tableBeansList)
		{
			if (tBean.isInstallProcessOk())
			{
				tableBeansListInstalled.add(tBean);
			}
			else
			{
				this.model.setNotesMessage(tBean.getNotesMessage());
			}
		}
		if (!tableBeansListInstalled.isEmpty())
		{
			this.model.setRows(tableBeansListInstalled, true);
		}
	}
	
	private List<String> composeSelectedBatchList()
	{
		List<String> selectedBatchList = new ArrayList<String>();
		if (this.view.getDspifCheckBox().isSelected())
		{
			selectedBatchList.add(DSPIF);
		}
		if (this.view.getDspvrCheckBox().isSelected())
		{
			selectedBatchList.add(DSPVR);
		}
		if (this.view.getDspvsCheckBox().isSelected())
		{
			selectedBatchList.add(DSPVS);
		}
		if (this.view.getFdigiCheckBox().isSelected())
		{
			selectedBatchList.add(FDIGI);
		}
		if (this.view.getImmlrCheckBox().isSelected())
		{
			selectedBatchList.add(IMMLR);
		}
		if (this.view.getIndocCheckBox().isSelected())
		{
			selectedBatchList.add(INDOC);
		}
		if (this.view.getRxxmlCheckBox().isSelected())
		{
			selectedBatchList.add(RXXML);
		}
		if (this.view.getSvimmCheckBox().isSelected())
		{
			selectedBatchList.add(SVIMM);
		}
		if (this.view.getTxxmlCheckBox().isSelected())
		{
			selectedBatchList.add(TXXML);
		}
		return selectedBatchList;
	}
	
	private List<CompileTableBean> doCompile(List<String> selectedBatchList)
	{
		List<CompileTableBean> tableBeansList = new ArrayList<CompileTableBean>();
		if (!selectedBatchList.isEmpty())
		{
			File buildFile 				= null;
			String compileResult		= null;
			CompileTableBean newRow		= null;
			for (String batch : selectedBatchList)
			{
				switch (batch) 
				{
				case DSPIF:
					buildFile = new File(configProperties.getProperty(DSPIF_ANT_SCRIPT));
					break;
					
				case DSPVR:
					buildFile = new File(configProperties.getProperty(DSPVR_ANT_SCRIPT));
					break;
					
				case DSPVS:
					buildFile = new File(configProperties.getProperty(DSPVS_ANT_SCRIPT));
					break;
					
				case FDIGI:
					buildFile = new File(configProperties.getProperty(FDIGI_ANT_SCRIPT));
					break;
					
				case IMMLR:
					buildFile = new File(configProperties.getProperty(IMMLR_ANT_SCRIPT));
					break;
					
				case INDOC:
					buildFile = new File(configProperties.getProperty(INDOC_ANT_SCRIPT));
					break;
					
				case RXXML:
					buildFile = new File(configProperties.getProperty(RXXML_ANT_SCRIPT));
					break;
					
				case SVIMM:
					buildFile = new File(configProperties.getProperty(SVIMM_ANT_SCRIPT));
					break;
					
				case TXXML:
					buildFile = new File(configProperties.getProperty(TXXML_ANT_SCRIPT));
					break;

				default:
					break;
				}
				
				compileResult = executeAntScript(buildFile);
				newRow = new CompileTableBean(batch, compileResult, new Timestamp(System.currentTimeMillis()).toString());
				newRow.setToDisplayInNoteSection(true);
				tableBeansList.add(newRow);
				updateLastCompileTimestamp(batch, newRow);
			}
		}
		return tableBeansList;
	}
	
	/**
	 * 
	 * @param selectedBatchList
	 * @param deployJarFiles
	 * @return
	 */
	private List<AbstractTableBean> doDeploy(List<String> selectedBatchList, boolean deployJarFiles)
	{
		List<AbstractTableBean> tableBeansList = new ArrayList<AbstractTableBean>();
		if (!selectedBatchList.isEmpty())
		{
			AbstractTableBean newRow		= null;
			String commandAndArguments[] 	= new String[2];
			commandAndArguments[0] 			= configProperties.getProperty(WINSCP_COMMAND);
			for (String batch : selectedBatchList)
			{
				switch (batch) 
				{
				case DSPIF:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(DSPIF_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(DSPIF_DEPLOY_CONFIG_SCRIPT);
					break;
				
				case DSPVR:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(DSPVR_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(DSPVR_DEPLOY_CONFIG_SCRIPT);
					break;
					
				case DSPVS:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(DSPVS_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(DSPVS_DEPLOY_CONFIG_SCRIPT);
					break;
					
				case FDIGI:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(FDIGI_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(FDIGI_DEPLOY_CONFIG_SCRIPT);
					break;
				
				case IMMLR:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(IMMLR_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(IMMLR_DEPLOY_CONFIG_SCRIPT);
					break;
				
				case INDOC:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(INDOC_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(INDOC_DEPLOY_CONFIG_SCRIPT);
					break;
					
				case RXXML:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(RXXML_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(RXXML_DEPLOY_CONFIG_SCRIPT);
					break;
					
				case SVIMM:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(SVIMM_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(SVIMM_DEPLOY_CONFIG_SCRIPT);
					break;

				case TXXML:
					commandAndArguments[1] = deployJarFiles ? configProperties.getProperty(TXXML_DEPLOY_JAR_SCRIPT) : configProperties.getProperty(TXXML_DEPLOY_CONFIG_SCRIPT);
					break;	
					
				default:
					break;
				}
				
				newRow = executeWinScpScript(batch, commandAndArguments, deployJarFiles);
				newRow.setToDisplayInNoteSection(true);
				tableBeansList.add(newRow);
				updateLastDeployTimestamp(batch, newRow);
			}
		}
		return tableBeansList;
	}
		
	/**
	 * 
	 * @param batch
	 * @param commandAndArguments
	 * @return a new {@link TableBean} if the installation was successful, {@code null} otherwise
	 */
	private AbstractTableBean executeWinScpScript(String batch, String[] commandAndArguments, boolean deployJarFiles)
	{
		int winScpExitValue 			= 0;
		AbstractTableBean result 		= null;
//		final String INSTALL_JAR_OK 	= "...jar's installation process completed successfully!";
//		final String INSTALL_JAR_KO 	= "...jar's installation process finished with errors, some elements have not been completely transferred, check the logs for more details...";	
//		final String INSTALL_CONFIG_OK 	= "...config installation process completed successfully!";
//		final String INSTALL_CONFIG_KO 	= "...config installation process finished with errors, some elements have not been completely transferred, check the logs for more details...";
		try
		{            
			Process proc = Runtime.getRuntime().exec(commandAndArguments);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("<ERROR>");
			while ((line = br.readLine()) != null)
			{
				System.out.println(line);
			}
			System.out.println("</ERROR>");
			winScpExitValue = proc.waitFor();
			System.out.println("Process exitValue: " + proc.exitValue());
		}
		catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
		}

		if (winScpExitValue == 0)
		{
			DeployResult deployResult = checkDeploy();
			if (deployResult.isCompleted())
			{
				if (isDeployCorrect(batch, deployJarFiles))
				{
					if (deployJarFiles)
					{
						result = new DeployJarTableBean(batch, true, new Timestamp(System.currentTimeMillis()).toString());
					}
					else
					{
						result = new DeployConfigTableBean(batch, true, new Timestamp(System.currentTimeMillis()).toString());
					}
				}
				else
				{
					if (deployJarFiles)
					{
						result = new DeployJarTableBean(batch, false, MISSING_INFO);
					}
					else
					{
						result = new DeployConfigTableBean(batch, false, MISSING_INFO);
					}
//					this.model.setNotesMessage(wrapNoteMessages(INSTALL_JAR_KO, true));
				}
			}
			else
			{
				result = new TableBean(batch, MISSING_INFO, deployResult.getMessage(), deployResult.getMessage(), MISSING_INFO, MISSING_INFO, MISSING_INFO);
//				this.model.setNotesMessage(deployResult.getMessage());
			}
		}
		else
		{
			final String MESSAGE = wrapNoteMessages("Error executing WinSCP\nCommand in error is: " + commandAndArguments[0] + " " + commandAndArguments[1] + " return code is: " + winScpExitValue, true);
			result = new TableBean(batch, MISSING_INFO, MESSAGE, MESSAGE, MISSING_INFO, MISSING_INFO, MISSING_INFO);
//			this.model.setNotesMessage(wrapNoteMessages("Error executing WinSCP\nCommand in error is: " + commandAndArguments[0] + " " + commandAndArguments[1] + " return code is: " + winScpExitValue, true));
		}
		return result;
	}
		
	private String executeAntScript(File buildFile)
	{
		final String TARGET 			= "LOCAL";
		String antBuildResult 			= "";
		File tempOutputAntCompileFile 	= new File(JAVA_TEMP_DIR + "temp.txt");
		OutputStream o 					= null;
		Project p 						= new Project();

		p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		if (!tempOutputAntCompileFile.exists()) 
		{
			try 
			{
				tempOutputAntCompileFile.createNewFile();
			}
			catch (IOException e) 
			{
				// logger.error("Non è stato possibile creare il file " + tempOutputAntCompileFile.getAbsolutePath());
				e.printStackTrace();
			}
		}

		try 
		{
			o = new FileOutputStream(tempOutputAntCompileFile);
		} 
		catch (FileNotFoundException e) 
		{
			// logger.error("Il file " + tempOutputAntCompileFile.getAbsolutePath() + " non è stato trovato");
			e.printStackTrace();
		}

		PrintStream ps = new PrintStream(o);
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setOutputPrintStream(ps);
		consoleLogger.setErrorPrintStream(ps);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);

		try 
		{
			p.fireBuildStarted();
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			p.addReference("ant.projectHelper", helper);
			helper.parse(p, buildFile);
			p.executeTarget(TARGET);
			p.fireBuildFinished(null);
		} 
		catch (BuildException e) 
		{
			p.fireBuildFinished(e);
		}
		finally
		{
			IOUtils.closeQuietly(o);
		}

		try 
		{
			antBuildResult = FileUtils.readFileToString(tempOutputAntCompileFile, Charset.defaultCharset());
		} 
		catch (IOException e) 
		{
			// logger.error("Errore I/O nella lettura e conversione del file " + tempOutputAntCompileFile.getAbsolutePath());
			e.printStackTrace();
		}

		if (tempOutputAntCompileFile.exists())
		{
			tempOutputAntCompileFile.delete();
		}
		return antBuildResult;
	}
	
	private void updateLastCompileTimestamp(final String BATCH, CompileTableBean tableBean)
	{
		final String LAST_COMPILE_TIMESTAMP = BATCH.concat("_LAST_COMPILE_TIMESTAMP");
		final String LAST_COMPILE_RESULT 	= BATCH.concat("_LAST_COMPILE_RESULT");
		Map<String, String> propertiesMap 	= new HashMap<String, String>();
		
		propertiesMap.put(LAST_COMPILE_TIMESTAMP, tableBean.getLastCompileTimestamp());
		propertiesMap.put(LAST_COMPILE_RESULT, tableBean.getNotesMessage());
		updatePropertiesInFile(JAVA_TEMP_DIR + BATCH_COMPILE_DEPLOY_REGISTER, propertiesMap);
	}
	
	private void updateLastDeployTimestamp(final String BATCH, AbstractTableBean tableBean)
	{
		final String LAST_JAR_DEPLOY_TIMESTAMP 		= BATCH.concat("_LAST_JAR_DEPLOY_TIMESTAMP");
		final String LAST_CONFIG_DEPLOY_TIMESTAMP 	= BATCH.concat("_LAST_CONFIG_DEPLOY_TIMESTAMP");
		final String LAST_JAR_DEPLOY_RESULT 		= BATCH.concat("_LAST_JAR_DEPLOY_RESULT");
		final String LAST_CONFIG_DEPLOY_RESULT 		= BATCH.concat("_LAST_CONFIG_DEPLOY_RESULT");
		Map<String, String> propertiesMap 	= new HashMap<String, String>();
		
		if (tableBean instanceof DeployJarTableBean)
		{
			DeployJarTableBean djtb = (DeployJarTableBean) tableBean;
			propertiesMap.put(LAST_JAR_DEPLOY_RESULT, djtb.getNotesMessage());
			propertiesMap.put(LAST_JAR_DEPLOY_TIMESTAMP, djtb.getLastDeployJarTimestamp());
		}
		else if (tableBean instanceof DeployConfigTableBean)
		{
			DeployConfigTableBean dctb = (DeployConfigTableBean) tableBean;
			propertiesMap.put(LAST_CONFIG_DEPLOY_RESULT, dctb.getNotesMessage());
			propertiesMap.put(LAST_CONFIG_DEPLOY_TIMESTAMP, dctb.getLastDeployConfigTimestamp());
		}
		updatePropertiesInFile(JAVA_TEMP_DIR + BATCH_COMPILE_DEPLOY_REGISTER, propertiesMap);
	}
	
	private DeployResult checkDeploy()
	{
		final int NUM_RETRY 		= 5;
		final long MILLISEC_TO_WAIT = 3000L;
		int i 						= 0;
		while (i < NUM_RETRY)
		{
			// SLEEP
			try 
			{
				Thread.sleep(MILLISEC_TO_WAIT);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			// READ
			try 
			{
				winscp_log = FileUtils.readFileToString(new File(configProperties.getProperty("WINSCP_LOGS")), Charset.defaultCharset());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			// CHECK
			if (winscp_log.contains("Closing connection.") && winscp_log.contains("Sent EOF message"))
			{
				return new DeployResult(true, null);
			}
			else if (winscp_log.contains("Password authentication failed") && winscp_log.contains("Disconnected: Unable to authenticate"))
			{
				return new DeployResult(false, "Password authentication failed. \nDisconnected: Unable to authenticate");
			}
			else
			{
				System.out.println("Lettura: " + i++);
			}
		}
		return new DeployResult(false, "Connection error.\nUnable to connect to the ee000 svil server in " + NUM_RETRY + " retries each " + (MILLISEC_TO_WAIT / 1000) + " seconds");
	}
	
	private boolean isDeployCorrect(final String BATCH_NAME, boolean deployJarFiles)
	{
		final String BATCH_ITEM_DIR = deployJarFiles ? configProperties.getProperty(BATCH_NAME.toUpperCase().concat("_LIB_DIR")) : configProperties.getProperty(BATCH_NAME.toUpperCase().concat("_CONFIG_DIR"));
		if (BATCH_ITEM_DIR != null)
		{
			File dir = new File(BATCH_ITEM_DIR);
			if (dir.isDirectory())
			{
				for (File item : dir.listFiles())
				{
					if (!winscp_log.contains("Transfer done: " + StringUtils.wrap(item.getName(), "'"))) 
					{
						return false;
					}
				}
			}
		}
		return true;
	}
			
}