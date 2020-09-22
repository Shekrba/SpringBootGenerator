package rs.ac.uns.ftn.mbrs.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.mbrs.demo.template.GService;
import rs.ac.uns.ftn.mbrs.demo.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class TestmToServiceImpl implements GService<TestModelmTo,TestmToDTO> {

    @Autowired
    TestmToRepo testmToRepo;

    @Autowired
    TestRepo testRepo;

    @Override
    public List<TestModelmTo> getAll() {
        return testmToRepo.findAll();
    }

    @Override
    public TestModelmTo save(TestmToDTO obj) {
        TestModel testModel = testRepo.getOne(obj.getTestModel());
        TestModelmTo testModelmTo = ObjectMapperUtils.map(obj,TestModelmTo.class);
        testModelmTo.setTestModel(testModel);
        testModel.getTestModelmToSet().add(testModelmTo);
        testModelmTo = testmToRepo.save(testModelmTo);
        testRepo.save(testModel);
        return testModelmTo;
    }

    @Override
    public TestModelmTo update(TestmToDTO obj) {
        TestModel testModel = testRepo.getOne(obj.getTestModel());
        TestModelmTo oldTestModelmTo = testmToRepo.getOne(obj.getId());
        oldTestModelmTo.getTestModel().getTestModelmToSet().remove(oldTestModelmTo);
        TestModelmTo testModelmTo = ObjectMapperUtils.map(obj,TestModelmTo.class);
        testModelmTo.setTestModel(testModel);
        testModel.getTestModelmToSet().add(testModelmTo);
        testRepo.save(testModel);
        return testModelmTo;
    }

    @Override
    public TestModelmTo get(Long id) {
        return testmToRepo.getOne(id);
    }

    @Override
    public void delete(Long id) {
        testmToRepo.delete(testmToRepo.getOne(id));
    }
}
