package rs.ac.uns.ftn.mbrs.demo.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TestModelmTo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String strTestmTo;

    @ManyToOne(fetch = FetchType.EAGER)
    private TestModel testModel;

}