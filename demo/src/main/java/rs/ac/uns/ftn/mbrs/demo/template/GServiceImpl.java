package rs.ac.uns.ftn.mbrs.demo.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GServiceImpl<T> implements GService<T> {

    private final JpaRepository<T,Long> gRepo;

    public GServiceImpl(JpaRepository<T,Long> gRepo) {
        this.gRepo=gRepo;
    }

    @Override
    public List<T> getAll() {
        return gRepo.findAll();
    }

    @Override
    public T save(T obj) {
        return gRepo.save(obj);
    }

    @Override
    public T update(T obj) {
        return gRepo.save(obj);
    }

    @Override
    public T get(long id) {
        return gRepo.getOne(id);
    }

    @Override
    public void delete(long id) {
        T delObj = gRepo.getOne(id);
        gRepo.delete(delObj);
    }
}