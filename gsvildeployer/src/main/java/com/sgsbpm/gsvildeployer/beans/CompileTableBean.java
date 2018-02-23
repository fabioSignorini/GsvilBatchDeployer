/**
 * 
 */
package com.sgsbpm.gsvildeployer.beans;

/**
 * @author gs01491
 *
 */
public class CompileTableBean extends AbstractTableBean
{
	
	private String compileResult			= null;
	
	private String lastCompileTimestamp		= null;
	
	/**
	 * 
	 * @param batchName
	 * @param compileResult
	 * @param lastCompileTimestamp
	 */
	public CompileTableBean(String batchName, String compileResult, String lastCompileTimestamp)
	{
		super(batchName);
		this.compileResult 			= compileResult;
		this.lastCompileTimestamp 	= lastCompileTimestamp;
	}

	@Override
	public boolean isInstallProcessOk()
	{
		return true;
	}

	@Override
	public String getNotesMessage() 
	{
		return compileResult;
	}
	
	public String getLastCompileTimestamp() 
	{
		return lastCompileTimestamp;
	}

	public void setLastCompileTimestamp(String lastCompileTimestamp) 
	{
		this.lastCompileTimestamp = lastCompileTimestamp;
	}

}
