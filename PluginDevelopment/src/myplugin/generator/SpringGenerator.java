package myplugin.generator;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import freemarker.template.TemplateException;
import myplugin.generator.fmmodel.FMClass;
import myplugin.generator.fmmodel.FMModel;
import myplugin.generator.options.GeneratorOptions;
import myplugin.generator.options.ProjectOptions;

/**
 * EJB generator that now generates incomplete ejb classes based on MagicDraw
 * class model
 * 
 * @ToDo: enhance resources/templates/ejbclass.ftl template and intermediate
 *        data structure (@see myplugin.generator.fmmodel) in order to generate
 *        complete ejb classes
 */

public class SpringGenerator extends BasicGenerator {

	public SpringGenerator(GeneratorOptions generatorOptions) {
		super(generatorOptions);
	}

	public void generate(String type) {
		
		List<FMClass> classes = null;
		if(type.equals("listTemplate") || type.equals("nav")) {
			classes = FMModel.getInstance().getCrudClasses();
			
			if(classes != null && classes.size() > 0) {
				try {
					super.generate();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			
			Writer out;
			Map<String, Object> context = new HashMap<String, Object>();
			try {
				//classes.get(0).getClassPackage()
				out = getWriter("listTemplate", "angular" );
				if (out != null) {
					context.clear();
					context.put("classes", classes);
					getTemplate().process(context, out);
					out.flush();
				}
			} catch (TemplateException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}else {
			if(type.equals("table"))
				classes = FMModel.getInstance().getClasses();
			else if(type.equals("repo") || type.equals("api") || type.equals("form")  || type.equals("service") || type.equals("dto"))
				classes = FMModel.getInstance().getCrudClasses();
			
			if(classes != null && classes.size() > 0) {
				try {
					super.generate();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
			
			for (int i = 0; i < classes.size(); i++) {
				FMClass cl = classes.get(i);
				Writer out;
				Map<String, Object> context = new HashMap<String, Object>();
				try {
					if(type.equals("form")) {
						cl.setClassPackage("angular");
					}
					out = getWriter(cl.getClassName(), cl.getClassPackage());
					if (out != null) {
						context.clear();
						context.put("class", cl);
						context.put("properties", cl.getProperties());
						context.put("importedPackages", cl.getImportedPackages());
						getTemplate().process(context, out);
						out.flush();
					}
				} catch (TemplateException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
	}
}
