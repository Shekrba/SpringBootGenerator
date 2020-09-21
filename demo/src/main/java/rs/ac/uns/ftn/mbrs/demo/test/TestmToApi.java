package rs.ac.uns.ftn.mbrs.demo.test;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.mbrs.demo.template.GController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/testmto")
public class TestmToApi extends GController<TestModelmTo,TestmToDTO> {

    public TestmToApi(TestmToServiceImpl gService) {
        super(gService);
    }
}