package rs.ac.uns.ftn.mbrs.demo.testApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.mbrs.demo.template.GController;

@RestController
@RequestMapping("/${class.className?lower_case}")
public class ${class.className}Api extends GController<${class.className}, ${class.className}DTO>{

	public ${class.className}Api(${class.className}ServiceImpl gService) {
		super(gService);
	}
}