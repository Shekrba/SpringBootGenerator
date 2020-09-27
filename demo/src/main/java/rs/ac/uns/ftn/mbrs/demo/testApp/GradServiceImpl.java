package rs.ac.uns.ftn.mbrs.demo.testApp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.mbrs.demo.template.GService;
import rs.ac.uns.ftn.mbrs.demo.utils.ObjectMapperUtils;

import java.util.List;
import java.util.HashSet;


@Service
public class GradServiceImpl implements GService<Grad, GradDTO> {

    @Autowired
    GradRepo gradRepo;
    
	
	@Autowired
    DrzavaRepo drzavaRepo;
	
	@Autowired
    RekaRepo rekaRepo;
    
    @Override
    public List<Grad> getAll() {
        return gradRepo.findAll();
    }

    @Override
    public Grad save(GradDTO obj) {
    	Grad grad = ObjectMapperUtils.map(obj, Grad.class);
    
		Drzava drzava = drzavaRepo.getOne(obj.getDrzava());
		grad.setDrzava(drzava);
		drzava.getGradovi().add(grad);
		grad = gradRepo.save(grad);
		drzavaRepo.save(drzava);
		for(Long id : obj.getReke()){
			Reka reka = rekaRepo.getOne(id);
			grad.getReke().add(reka);
			reka.getGradovi().add(grad);
			grad = gradRepo.save(grad);
			rekaRepo.save(reka);
		}
		return grad;
	}
	
	@Override
    public Grad update(GradDTO obj) {
    	Grad grad = ObjectMapperUtils.map(obj, Grad.class);

		Grad oldGrad = null;
		Drzava drzava = drzavaRepo.getOne(obj.getDrzava());
		grad.setDrzava(drzava);
		oldGrad = gradRepo.getOne(obj.getId());
		oldGrad.getDrzava().getGradovi().remove(oldGrad);
		drzava.getGradovi().add(grad);
		grad = gradRepo.save(grad);
		drzavaRepo.save(drzava);
			oldGrad = gradRepo.getOne(obj.getId());
		
		oldGrad.setReke(new HashSet<>());
				
		for(Long id : obj.getReke()){
			Reka reka = rekaRepo.getOne(id);			
			grad.getReke().add(reka);
			reka.getGradovi().add(grad);
			grad = gradRepo.save(grad);
			rekaRepo.save(reka);
		}
		return grad;
	}
	
	@Override
    public Grad get(Long id) {
    	return gradRepo.getOne(id);
    }
	
	@Override
    public void delete(Long id) {
    	gradRepo.delete(gradRepo.getOne(id));
    }
}