package rs.ac.uns.ftn.mbrs.demo.testApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.mbrs.demo.template.GController;

@RestController
@RequestMapping("/grad")
public class GradApi extends GController<Grad, GradDTO>{

	public GradApi(GradServiceImpl gService) {
		super(gService);
	}
}