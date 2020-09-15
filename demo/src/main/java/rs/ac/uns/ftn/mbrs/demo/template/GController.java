package rs.ac.uns.ftn.mbrs.demo.template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class GController<T> {

    private final GServiceImpl<T> gService;

    public GController(JpaRepository<T,Long> repo) {
        this.gService = new GServiceImpl<T>(repo);
    }

    @PostMapping()
    public ResponseEntity save(@RequestBody T obj) {
        final T ret = gService.save(obj); // Cookie service is null
        return ResponseEntity.ok(ret);
    }

    @PutMapping()
    public ResponseEntity update(@RequestBody T obj) {
        final T ret = gService.update(obj); // Cookie service is null
        return ResponseEntity.ok(ret);
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") long id) {
        final T ret = gService.get(id); // Cookie service is null
        return ResponseEntity.ok(ret);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        gService.delete(id); // Cookie service is null
        return ResponseEntity.ok().build();
    }

}
