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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String naziv;

	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope = Grad.class)
	@JsonIdentityReference(alwaysAsId=true)
	@ManyToMany
    	@JoinTable(
            name = "reka_grad",
            joinColumns = @JoinColumn(name = "grad_id"),
            inverseJoinColumns = @JoinColumn(name = "reka_id")
    	)
    private Set<Grad> gradovi = new HashSet<>();

}