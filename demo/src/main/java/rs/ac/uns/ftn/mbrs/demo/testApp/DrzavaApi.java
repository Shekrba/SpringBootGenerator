package rs.ac.uns.ftn.mbrs.demo.testApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.mbrs.demo.template.GController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/drzava")
public class DrzavaApi extends GController<Drzava, DrzavaDTO>{

	public DrzavaApi(DrzavaServiceImpl gService) {
		super(gService);
	}
}