package rs.ac.uns.ftn.mbrs.demo.testApp;

import com.fasterxml.jackson.annotation.*;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@JsonIgnoreProperties(ignoreUnknown = true, value = {"hibernateLazyInitializer"})
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grad")
public class Grad {  
	
    private String naziv;   
	
    private Integer brojStanovnika;   
	
    private String zip;   
	
	@ManyToOne()
	private Drzava drzava;  

	
		@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
		@JsonIdentityReference(alwaysAsId=true)
	@ManyToMany
    	@JoinTable(
            name = "reka_grad",
            joinColumns = @JoinColumn(name = "grad_id"),
            inverseJoinColumns = @JoinColumn(name = "reka_id")
    	)
    private Set<Reka> reke = new HashSet<>();	



}