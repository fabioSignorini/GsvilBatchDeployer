/**
 * 
 */
package com.sgsbpm.gsvildeployer.beans;

/**
 * @author gs01491
 */
public class TableBean
{ 
	private String batchName;
	
	private String compileResult;
	
	private String deployJarResult;
	
	private String deployConfigResult;
	
	private String lastCompileTimestamp;
	
	private String lastDeployJarTimestamp;
	
	private String lastDeployConfigTimestamp;
	
	private String installErrorMessage;
	
	private String installErrorTimestamp;
	
	private boolean toDisplayInNoteSection;
	
	public TableBean(String batchName, String compileResult, String deployJarResult, String deployConfigResult, String lastCompileTimestamp, String lastDeployJarTimestamp, String lastDeployConfigTimestamp) 
	{
		this.batchName 					= batchName;
		this.compileResult 				= compileResult;
		this.deployJarResult 			= deployJarResult;
		this.deployConfigResult 		= deployConfigResult;
		this.lastCompileTimestamp 		= lastCompileTimestamp;
		this.lastDeployJarTimestamp 	= lastDeployJarTimestamp;
		this.lastDeployConfigTimestamp 	= lastDeployConfigTimestamp;
		this.installErrorMessage 		= null;
	}
	
	public TableBean(String batchName, String installErrorMessage, String installErrorTimestamp)
	{
		this.batchName 				= batchName;
		this.installErrorMessage 	= installErrorMessage;
		this.installErrorTimestamp 	= installErrorTimestamp;
	}
	
	public boolean isToDisplayInNoteSection() 
	{
		return toDisplayInNoteSection;
	}

	public void setToDisplayInNoteSection(boolean toDisplayInNoteSection) 
	{
		this.toDisplayInNoteSection = toDisplayInNoteSection;
	}

	public String getBatchName() 
	{
		return batchName;
	}

	public String getCompileResult() 
	{
		return compileResult;
	}

	public String getDeployJarResult()
	{
		return deployJarResult;
	}

	public String getDeployConfigResult()
	{
		return deployConfigResult;
	}

	public String getLastCompileTimestamp()
	{
		return lastCompileTimestamp;
	}

	public String getLastDeployJarTimestamp() 
	{
		return lastDeployJarTimestamp;
	}
	
	public String getLastDeployConfigTimestamp() 
	{
		return lastDeployConfigTimestamp;
	}
	
	public boolean isInstallProcessOk()
	{
		return this.installErrorMessage == null; 
	}
	
	public String getInstallErrorMessage()
	{
		return installErrorMessage; 
	}
	
	public String getInstallErrorTimestamp()
	{
		return installErrorTimestamp;
	}
	
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchName == null) ? 0 : batchName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		TableBean other = (TableBean) obj;
		if (batchName == null)
		{
			if (other.batchName != null)
			{
				return false;
			}
		} 
		else if (!batchName.equals(other.batchName))
		{
			return false;
		}
		return true;
	}

}
