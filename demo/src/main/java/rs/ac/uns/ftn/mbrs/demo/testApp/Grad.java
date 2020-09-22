package rs.ac.uns.ftn.mbrs.demo.testApp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String naziv;   
	
    private Integer brojStanovnika;   
	
    private String zip;   

	@ManyToOne()
	private Drzava drzava;

	@JsonIgnore
	@ManyToMany
    	@JoinTable(
            name = "grad_reka",
            joinColumns = @JoinColumn(name = "reka_id"),
            inverseJoinColumns = @JoinColumn(name = "grad_id")
    	)
    private Set<Reka> reke = new HashSet<>();	


}