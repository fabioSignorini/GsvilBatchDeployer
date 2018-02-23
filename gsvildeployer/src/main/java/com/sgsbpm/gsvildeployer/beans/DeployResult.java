/**
 * 
 */
package com.sgsbpm.gsvildeployer.beans;

/**
 * @author gs01491
 *
 */
public class DeployResult
{
	private boolean completed 	= false;
	
	private String message		= null;

	public DeployResult(boolean completed, String message) 
	{
		this.completed = completed;
		this.message = message;
	}

	public boolean isCompleted() 
	{
		return completed;
	}

	public void setCompleted(boolean completed) 
	{
		this.completed = completed;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

}
