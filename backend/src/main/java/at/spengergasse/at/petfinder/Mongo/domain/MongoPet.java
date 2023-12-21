package at.spengergasse.at.petfinder.Mongo.domain;

import at.spengergasse.at.petfinder.PetType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class MongoPet {

    @Id
    private String id;
    private String name;
    private PetType type;
    private int age;
    private double weight;
    private double height;


}
