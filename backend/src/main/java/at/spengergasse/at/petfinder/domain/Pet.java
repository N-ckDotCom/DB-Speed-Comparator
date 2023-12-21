package at.spengergasse.at.petfinder.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Table
@NoArgsConstructor
@Entity
public class Pet {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jpaID;

    @Id
    private String id;
    private String name;
    private PetType type;
    private int age;
    private double weight;
    private double height;


}
