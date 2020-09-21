package rs.ac.uns.ftn.mbrs.demo.testApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.mbrs.demo.template.GService;
import rs.ac.uns.ftn.mbrs.demo.utils.ObjectMapperUtils;

import java.util.List;

@Service
public class DrzavaServiceImpl implements GService<Drzava, DrzavaDTO> {

    @Autowired
    DrzavaRepo drzavaRepo;
    
    
    @Override
    public List<Drzava> getAll() {
        return drzavaRepo.findAll();
    }

    @Override
    public Drzava save(DrzavaDTO obj) {
    	Drzava drzava = ObjectMapperUtils.map(obj, Drzava.class);
    
		drzava = drzavaRepo.save(drzava);
		return drzava;
	}
	
	@Override
    public Drzava update(DrzavaDTO obj) {
    	Drzava drzava = ObjectMapperUtils.map(obj, Drzava.class);
    
		drzava = drzavaRepo.save(drzava);
		return drzava;
	}
	
	@Override
    public Drzava get(Long id) {
    	return drzavaRepo.getOne(id);
    }
	
	@Override
    public void delete(Long id) {
    	drzavaRepo.delete(drzavaRepo.getOne(id));
    }
}