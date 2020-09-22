package rs.ac.uns.ftn.mbrs.demo.template;

import java.util.List;

public interface GService<S,T> {

    List<S> getAll();
    S save(T obj);
    S update(T obj);
    S get(Long id);
    void delete(Long id);

}