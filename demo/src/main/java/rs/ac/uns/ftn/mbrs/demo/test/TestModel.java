package rs.ac.uns.ftn.mbrs.demo.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String testStr;

    @JsonIgnore
    @OneToMany(mappedBy = "testModel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TestModelmTo> testModelmToSet = new HashSet<TestModelmTo>();

}
