package rs.ac.uns.ftn.mbrs.demo.test;

import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.mbrs.demo.template.GService;
import rs.ac.uns.ftn.mbrs.demo.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class TestServiceImpl implements GService<TestModel,TestDTO> {

    @Autowired
    TestRepo testRepo;

    @Override
    public List<TestModel> getAll() {
        return testRepo.findAll();
    }

    @Override
    public TestModel save(TestDTO obj) {
        TestModel testModel = ObjectMapperUtils.map(obj,TestModel.class);
        return testRepo.save(testModel);
    }

    @Override
    public TestModel update(TestDTO obj) {
        return testRepo.save(ObjectMapperUtils.map(obj,TestModel.class));
    }

    @Override
    public TestModel get(Long id) {
        return testRepo.getOne(id);
    }

    @Override
    public void delete(Long id) {
        testRepo.delete(testRepo.getOne(id));
    }

}