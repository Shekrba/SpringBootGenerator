package rs.ac.uns.ftn.mbrs.demo.testApp;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
public class RekaDTO {  
	
    private String naziv;   
	
    private Set<Long> gradovi = new HashSet<Long>();



}