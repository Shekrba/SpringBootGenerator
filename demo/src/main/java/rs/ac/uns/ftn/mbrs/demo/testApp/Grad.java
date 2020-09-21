package rs.ac.uns.ftn.mbrs.demo.testApp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
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
	
	@ManyToOne(mappedBy = "gradovi")
	private Drzava drzava;  

	
	@ManyToMany
    	@JoinTable(
            name = "grad_reka",
            joinColumns = @JoinColumn(name = "reka_id"),
            inverseJoinColumns = @JoinColumn(name = "grad_id")
    	)
    private Set<Reka> reke = new HashSet<>();	

	public Grad() {

	}

}