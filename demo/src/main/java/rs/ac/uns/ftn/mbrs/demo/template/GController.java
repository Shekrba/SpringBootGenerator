package rs.ac.uns.ftn.mbrs.demo.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.mbrs.demo.test.TestServiceImpl;

import java.util.List;

public abstract class GController<S,T> {

    private final GService<S,T> gService;

    public GController(GService<S,T> gService) {
        this.gService = gService;
    }

    @GetMapping()
    public ResponseEntity getAll() {
        final List<S> ret = gService.getAll();
        return ResponseEntity.ok(ret);
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody T obj) {
        final S ret = gService.save(obj);
        return ResponseEntity.ok(ret);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody T obj) {
        final S ret = gService.update(obj);
        return ResponseEntity.ok(ret);
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") long id) {
        final S ret = gService.get(id);
        return ResponseEntity.ok(ret);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        gService.delete(id);
        return ResponseEntity.ok().build();
    }



}
