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
	
	private Long id;
	
	
    private String naziv;   
	
	private Integer brojStanovnika;   

	
	private Set<Long> gradovi = new HashSet<Long>();

	
    private String glavniGrad;   



}