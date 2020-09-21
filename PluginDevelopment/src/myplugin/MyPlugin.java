package myplugin;

import java.io.File;

import javax.swing.JOptionPane;

import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;


import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;

/** MagicDraw plugin that performes code generation */
public class MyPlugin extends com.nomagic.magicdraw.plugins.Plugin {
	
	String pluginDir = null; 
	
	public void init() {
		JOptionPane.showMessageDialog( null, "My Plugin init");
		
		pluginDir = getDescriptor().getPluginDirectory().getPath();
		
		// Creating submenu in the MagicDraw main menu 	
		ActionsConfiguratorsManager manager = ActionsConfiguratorsManager.getInstance();		
		manager.addMainMenuConfigurator(new MainMenuConfigurator(getSubmenuActions()));
		
		/** @Todo: load project options (@see myplugin.generator.options.ProjectOptions) from 
		 * ProjectOptions.xml and take ejb generator options */
		
		//for test purpose only:
		GeneratorOptions tableOptions = new GeneratorOptions("c:/temp", "model", "templates", "{0}.java", true, "spring");
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("TableGenerator", tableOptions);
		tableOptions.setTemplateDir(pluginDir + File.separator + tableOptions.getTemplateDir()); //apsolutna putanja
		
		GeneratorOptions repoOptions = new GeneratorOptions("c:/temp", "repo", "templates", "{0}Repo.java", true, "spring"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("RepoGenerator", repoOptions);
		repoOptions.setTemplateDir(pluginDir + File.separator + repoOptions.getTemplateDir()); //apsolutna putanja
		
		GeneratorOptions apiOptions = new GeneratorOptions("c:/temp", "api", "templates", "{0}Api.java", true, "spring"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ApiGenerator", apiOptions);
		apiOptions.setTemplateDir(pluginDir + File.separator + apiOptions.getTemplateDir()); //apsolutna putanja
		
		//to do ftl-s
		
		GeneratorOptions listTemplateOptions = new GeneratorOptions("c:/temp", "listTemplate", "templates", "ListTemplate.ts", true, "angular"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ListTemplateGenerator", listTemplateOptions);
		listTemplateOptions.setTemplateDir(pluginDir + File.separator + listTemplateOptions.getTemplateDir()); //apsolutna putanja
		
		GeneratorOptions formOptions = new GeneratorOptions("c:/temp", "form", "templates", "{0}Form.json", true, "angular"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("FormGenerator", formOptions);
		formOptions.setTemplateDir(pluginDir + File.separator + formOptions.getTemplateDir()); //apsolutna putanja
		
		GeneratorOptions navOptions = new GeneratorOptions("c:/temp", "nav", "templates", "entity-list.component.html", true, "angular"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("NavGenerator", navOptions);
		navOptions.setTemplateDir(pluginDir + File.separator + navOptions.getTemplateDir()); //apsolutna putanja

		GeneratorOptions serviceOptions = new GeneratorOptions("c:/temp", "service", "templates", "{0}ServiceImpl.java", true, "spring"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("ServiceGenerator", serviceOptions);
		serviceOptions.setTemplateDir(pluginDir + File.separator + serviceOptions.getTemplateDir()); //apsolutna putanja

		GeneratorOptions dtoOptions = new GeneratorOptions("c:/temp", "dto", "templates", "{0}DTO.java", true, "spring"); 				
		ProjectOptions.getProjectOptions().getGeneratorOptions().put("DtoGenerator", dtoOptions);
		dtoOptions.setTemplateDir(pluginDir + File.separator + dtoOptions.getTemplateDir()); //apsolutna putanja
		
	}

	private NMAction[] getSubmenuActions()
	{
	   return new NMAction[]{
			new GenerateAction("Generate"),
	   };
	}
	
	public boolean close() {
		return true;
	}
	
	public boolean isSupported() {				
		return true;
	}
}


