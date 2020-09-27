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
@Table(name = "drzava")
public class Drzava {  
	
	@Id
	@GeneratedValue(strategy = EnumerationLiteral?)
	private Long id;   
	
	
    private String naziv;   
	
	@Column(name = "broj_stanovnika", unique = false, nullable = false)
	private Integer brojStanovnika;   


	
		@JsonIdentityInfo(
		generator = ObjectIdGenerators.PropertyGenerator.class,
		property = "id")
		@JsonIdentityReference(alwaysAsId=true)
	@OneToMany(mappedBy = "drzava")
	private Set<Grad> gradovi = new HashSet<Grad>();

	
    private String glavniGrad;   



}