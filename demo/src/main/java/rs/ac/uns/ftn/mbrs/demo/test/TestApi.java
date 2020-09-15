package rs.ac.uns.ftn.mbrs.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.uns.ftn.mbrs.demo.template.GController;
import rs.ac.uns.ftn.mbrs.demo.template.GService;

@RestController
@RequestMapping("/test")
public class TestApi extends GController<TestModel> {

    @Autowired
    public TestApi(TestRepo repo) {
        super(repo);
    }

}
