package rs.ac.uns.ftn.mbrs.demo.template;

public interface GService<T> {
    T save(T obj);
    T update(T obj);
    T get(long id);
    void delete(long id);
}