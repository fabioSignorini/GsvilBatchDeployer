/**
 * 
 */
package com.sgsbpm.gsvildeployer.beans;

/**
 * @author gs01491
 *
 */
public class DeployConfigTableBean extends AbstractTableBean
{
	
	private boolean deployConfigOk				= false;
	
	private String lastDeployConfigTimestamp	= null;
	
	/**
	 * 
	 * @param batchName
	 * @param deployConfigOk
	 * @param lastDeployConfigTimestamp
	 */
	public DeployConfigTableBean(String batchName, boolean deployConfigOk, String lastDeployConfigTimestamp)
	{
		super(batchName);
		this.deployConfigOk 			= deployConfigOk;
		this.lastDeployConfigTimestamp 	= lastDeployConfigTimestamp;
	}
	
	@Override
	public boolean isInstallProcessOk() 
	{
		return this.deployConfigOk;
	}

	@Override
	public String getNotesMessage() 
	{
		return isInstallProcessOk() ? INSTALL_CONFIG_OK : INSTALL_CONFIG_KO;
	}

	public String getLastDeployConfigTimestamp()
	{
		return lastDeployConfigTimestamp;
	}

}
