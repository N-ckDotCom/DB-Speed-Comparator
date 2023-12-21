package at.spengergasse.at.petfinder.JPA.domain;

import at.spengergasse.at.petfinder.PetType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table
@NoArgsConstructor
@Entity
public class JPAPet {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jpaID;

    private String name;
    private PetType type;
    private int age;
    private double weight;
    private double height;


}
