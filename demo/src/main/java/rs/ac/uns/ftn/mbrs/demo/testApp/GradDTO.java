package rs.ac.uns.ftn.mbrs.demo.testApp;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class GradDTO {  
	
    private String naziv;   
	
    private Integer brojStanovnika;   
	
    private String zip;   
	
	private Long drzava;  

	
    private Set<Long> reke = new HashSet<Long>();



}