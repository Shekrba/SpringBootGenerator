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
@Table(name = "reka")
public class Reka {  
	
    private String naziv;   
	
		@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
		@JsonIdentityReference(alwaysAsId=true)
	@ManyToMany
    	@JoinTable(
            name = "grad_reka",
            joinColumns = @JoinColumn(name = "reka_id"),
            inverseJoinColumns = @JoinColumn(name = "grad_id")
    	)
    private Set<Grad> gradovi = new HashSet<>();	



}