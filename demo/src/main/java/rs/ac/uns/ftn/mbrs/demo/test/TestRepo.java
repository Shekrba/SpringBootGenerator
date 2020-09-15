package rs.ac.uns.ftn.mbrs.demo.test;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepo extends JpaRepository<TestModel,Long> {
}
