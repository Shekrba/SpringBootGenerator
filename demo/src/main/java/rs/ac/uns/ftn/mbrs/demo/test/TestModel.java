package rs.ac.uns.ftn.mbrs.demo.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"hibernateLazyInitializer"})
@Getter
@Setter
@NoArgsConstructor
@Entity
public class TestModel {

    @Id
    private Long id;

    private String testStr;

}
