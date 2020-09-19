package rs.ac.uns.ftn.mbrs.demo.template;

import java.util.List;

public interface GService<T> {
    List<T> getAll();
    T save(T obj);
    T update(T obj);
    T get(long id);
    void delete(long id);
}