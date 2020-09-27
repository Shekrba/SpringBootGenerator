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
public class Long {  



}