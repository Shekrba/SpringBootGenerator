package rs.ac.uns.ftn.mbrs.demo.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class GController<T> {

    private final GServiceImpl<T> gService;

    public GController(JpaRepository<T,Long> repo) {
        this.gService = new GServiceImpl<T>(repo);
    }

    @GetMapping()
    public ResponseEntity getAll() {
        final List<T> ret = gService.getAll();
        return ResponseEntity.ok(ret);
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody T obj) {
        final T ret = gService.save(obj);
        return ResponseEntity.ok(ret);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody T obj) {
        final T ret = gService.update(obj);
        return ResponseEntity.ok(ret);
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") long id) {
        final T ret = gService.get(id);
        return ResponseEntity.ok(ret);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        gService.delete(id);
        return ResponseEntity.ok().build();
    }



}
