package rs.ac.uns.ftn.mbrs.demo.testApp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.mbrs.demo.template.GService;
import rs.ac.uns.ftn.mbrs.demo.utils.ObjectMapperUtils;

import java.util.List;
import java.util.HashSet;


@Service
public class RekaServiceImpl implements GService<Reka, RekaDTO> {

    @Autowired
    RekaRepo rekaRepo;
    
	
	@Autowired
    GradRepo gradRepo;
    
    @Override
    public List<Reka> getAll() {
        return rekaRepo.findAll();
    }

    @Override
    public Reka save(RekaDTO obj) {
    	Reka reka = ObjectMapperUtils.map(obj, Reka.class);
    
		for(Long id : obj.getGradovi()){
			Grad grad = gradRepo.getOne(id);
			reka.getGradovi().add(grad);
			grad.getReke().add(reka);
			reka = rekaRepo.save(reka);
			gradRepo.save(grad);
		}
		return reka;
	}
	
	@Override
    public Reka update(RekaDTO obj) {
    	Reka reka = ObjectMapperUtils.map(obj, Reka.class);

		Reka oldReka = null;
			oldReka = rekaRepo.getOne(obj.getId());
		
		oldReka.setGradovi(new HashSet<>());
				
		for(Long id : obj.getGradovi()){
			Grad grad = gradRepo.getOne(id);			
			reka.getGradovi().add(grad);
			grad.getReke().add(reka);
			reka = rekaRepo.save(reka);
			gradRepo.save(grad);
		}
		return reka;
	}
	
	@Override
    public Reka get(Long id) {
    	return rekaRepo.getOne(id);
    }
	
	@Override
    public void delete(Long id) {
    	rekaRepo.delete(rekaRepo.getOne(id));
    }
}