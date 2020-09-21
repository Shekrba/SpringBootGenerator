package rs.ac.uns.ftn.mbrs.demo.test;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TestDTO {

    private Long id;

    private String testStr;

    private Set<Long> testModelmToSet = new HashSet<Long>();

}
