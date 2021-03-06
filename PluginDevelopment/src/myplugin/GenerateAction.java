package myplugin;

import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.nomagic.magicdraw.actions.MDAction;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.uml2.ext.magicdraw.classes.mdkernel.Package;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import myplugin.analyzer.AnalyzeException;
import myplugin.analyzer.ModelAnalyzer;
import myplugin.generator.SpringGenerator;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/** Action that activate code generation */
@SuppressWarnings("serial")
class GenerateAction extends MDAction{
	
	
	public GenerateAction(String name) {			
		super("", name, null, null);		
	}

	public void actionPerformed(ActionEvent evt) {
		
		if (Application.getInstance().getProject() == null) return;
		Package root = Application.getInstance().getProject().getModel();
		
		if (root == null) return;
	
		ModelAnalyzer analyzer = new ModelAnalyzer(root, "spring");	
		
		try {
			analyzer.prepareModel();	
			GeneratorOptions goTable = ProjectOptions.getProjectOptions().getGeneratorOptions().get("TableGenerator");			
			SpringGenerator generatorTable = new SpringGenerator(goTable);
			generatorTable.generate("table");
			/**  @ToDo: Also call other generators */ 

			analyzer.prepareModel();
			GeneratorOptions goRepo = ProjectOptions.getProjectOptions().getGeneratorOptions().get("RepoGenerator");			
			SpringGenerator generatorRepo = new SpringGenerator(goRepo);
			generatorRepo.generate("repo");
			
			analyzer.prepareModel();
			GeneratorOptions goApi = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ApiGenerator");			
			SpringGenerator generatorApi = new SpringGenerator(goApi);
			generatorApi.generate("api");
			
			/*analyzer.prepareModel();
			GeneratorOptions goListTemplate = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ListTemplateGenerator");			
			SpringGenerator generatorListTemplate = new SpringGenerator(goListTemplate);
			generatorListTemplate.generate("listTemplate");
			*/
			analyzer.prepareModel();
			GeneratorOptions goForm = ProjectOptions.getProjectOptions().getGeneratorOptions().get("FormGenerator");			
			SpringGenerator generatorForm = new SpringGenerator(goForm);
			generatorForm.generate("form");
			
			analyzer.prepareModel();
			GeneratorOptions goNav = ProjectOptions.getProjectOptions().getGeneratorOptions().get("NavGenerator");			
			SpringGenerator generatorNav = new SpringGenerator(goNav);
			generatorNav.generate("nav");
			
			analyzer.prepareModel();
			GeneratorOptions goService = ProjectOptions.getProjectOptions().getGeneratorOptions().get("ServiceGenerator");			
			SpringGenerator generatorService = new SpringGenerator(goService);
			generatorService.generate("service");
			
			analyzer.prepareModel();
			GeneratorOptions goDto = ProjectOptions.getProjectOptions().getGeneratorOptions().get("DtoGenerator");			
			SpringGenerator generatorDto = new SpringGenerator(goDto);
			generatorDto.generate("dto");
			
			
			JOptionPane.showMessageDialog(null, "Code is successfully generated! Generated code is in folder: " + goTable.getOutputPath() +
					                         ", package: " + goTable.getFilePackage());
			exportToXml();
		} catch (AnalyzeException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 			
	}
	
	private void exportToXml() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to save FM Model?") == 
			JOptionPane.OK_OPTION)
		{	
			JFileChooser jfc = new JFileChooser();
			if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				String fileName = jfc.getSelectedFile().getAbsolutePath();
			
				XStream xstream = new XStream(new DomDriver());
				BufferedWriter out;		
				try {
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(fileName), "UTF8"));					
					xstream.toXML(FMModel.getInstance().getClasses(), out);
					//xstream.toXML(FMModel.getInstance().getEnumerations(), out);
					
				} catch (UnsupportedEncodingException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());				
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());				
				}		             
			}
		}	
	}	  

}