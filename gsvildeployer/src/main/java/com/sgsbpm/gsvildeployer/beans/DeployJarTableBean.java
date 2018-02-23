/**
 * 
 */
package com.sgsbpm.gsvildeployer.beans;

/**
 * @author gs01491
 *
 */
public class DeployJarTableBean extends AbstractTableBean
{
	
	private boolean deployJarOk				= false;
	
	private String lastDeployJarTimestamp	= null;
	
	/**
	 * 
	 * @param batchName
	 * @param deployJarOk
	 * @param lastDeployJarTimestamp
	 */
	public DeployJarTableBean(String batchName, boolean deployJarOk, String lastDeployJarTimestamp) 
	{
		super(batchName);
		this.deployJarOk 				= deployJarOk;
		this.lastDeployJarTimestamp 	= lastDeployJarTimestamp;
	}
	
	@Override
	public boolean isInstallProcessOk() 
	{
		return this.deployJarOk;
	}

	@Override
	public String getNotesMessage() 
	{
		return isInstallProcessOk() ? INSTALL_JAR_OK : INSTALL_JAR_KO;
	}

	public String getLastDeployJarTimestamp() 
	{
		return lastDeployJarTimestamp;
	}

}
