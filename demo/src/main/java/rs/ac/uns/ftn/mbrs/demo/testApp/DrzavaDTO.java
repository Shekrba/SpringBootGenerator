package rs.ac.uns.ftn.mbrs.demo.testApp;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class DrzavaDTO {  
	
    private String naziv;   
	
    private Integer brojStanovnika;   
	
    private String glavniGrad;   
	
	private Set<Long> gradovi = new HashSet<Long>();


	public DrzavaDTO() {

	}

}