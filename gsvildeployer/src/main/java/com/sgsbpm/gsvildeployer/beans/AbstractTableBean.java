/**
 * 
 */
package com.sgsbpm.gsvildeployer.beans;

/**
 * @author gs01491
 */
public abstract class AbstractTableBean
{ 
	protected final String INSTALL_JAR_OK 		= "...jar's installation process completed successfully!";
	
	protected final String INSTALL_JAR_KO 		= "...jar's installation process finished with errors, some elements have not been completely transferred, check the logs for more details...";	
	
	protected final String INSTALL_CONFIG_OK 	= "...config installation process completed successfully!";
	
	protected final String INSTALL_CONFIG_KO 	= "...config installation process finished with errors, some elements have not been completely transferred, check the logs for more details...";
	
//	private boolean deployJarFiles;
	
	protected boolean toDisplayInNoteSection	= false;
	
	protected String batchName					= null;
	
	public AbstractTableBean(String batchName)
	{
		this.batchName = batchName;
	}
	
//	public TableBean(String batchName, String installErrorMessage, String installErrorTimestamp)
//	{
//		this.batchName 				= batchName;
//		this.installErrorMessage 	= installErrorMessage;
//		this.installErrorTimestamp 	= installErrorTimestamp;
//	}
	
	public abstract boolean isInstallProcessOk();
//	{
//		return deployJarFiles ? isDeployJarOk() : isDeployConfigOk();
//	}
	
	public abstract String getNotesMessage();
//	{
//		if (deployJarFiles && isDeployJarOk())
//		{
//			return INSTALL_JAR_OK;
//		}
//		else if (deployJarFiles && !isDeployJarOk())
//		{
//			return INSTALL_JAR_KO;
//		}
//		else if (!deployJarFiles && isDeployConfigOk())
//		{
//			return INSTALL_CONFIG_OK;
//		}
//		else if (!deployJarFiles && !isDeployConfigOk())
//		{
//			return INSTALL_CONFIG_KO;
//		}
//		else
//		{
//			return "";
//		}
//	}
	
	public String getBatchName() 
	{
		return batchName;
	}

	public boolean isToDisplayInNoteSection() 
	{
		return toDisplayInNoteSection;
	}
	
	public void setToDisplayInNoteSection(boolean toDisplayInNoteSection) 
	{
		this.toDisplayInNoteSection = toDisplayInNoteSection;
	}
	
//	@Override
//	public int hashCode() 
//	{
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((batchName == null) ? 0 : batchName.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) 
//	{
//		if (this == obj)
//		{
//			return true;
//		}
//		if (obj == null)
//		{
//			return false;
//		}
//		if (getClass() != obj.getClass())
//		{
//			return false;
//		}
//		TableBean other = (TableBean) obj;
//		if (batchName == null)
//		{
//			if (other.batchName != null)
//			{
//				return false;
//			}
//		} 
//		else if (!batchName.equals(other.batchName))
//		{
//			return false;
//		}
//		return true;
//	}

}
