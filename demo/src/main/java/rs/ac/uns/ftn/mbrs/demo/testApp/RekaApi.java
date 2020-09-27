package rs.ac.uns.ftn.mbrs.demo.testApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.mbrs.demo.template.GController;

@RestController
@RequestMapping("/reka")
public class RekaApi extends GController<Reka, RekaDTO>{

	public RekaApi(RekaServiceImpl gService) {
		super(gService);
	}
}