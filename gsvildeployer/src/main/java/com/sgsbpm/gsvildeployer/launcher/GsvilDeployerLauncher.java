/**
 * 
 */
package com.sgsbpm.gsvildeployer.launcher;

import com.sgsbpm.gsvildeployer.controller.Controller;
import com.sgsbpm.gsvildeployer.model.Model;
import com.sgsbpm.gsvildeployer.view.View;

/**
 * @author gs01491
 *
 */
public class GsvilDeployerLauncher
{
	public static void main(String[] args) 
	{
		Model model = new Model();
		View view = new View("Deploy batch EE000 - SVIL", model);
		
		Controller controller = new Controller(model, view);
		controller.initController();
		
		
	}
	
}
