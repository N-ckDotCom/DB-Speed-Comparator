package at.spengergasse.at.petfinder.mongoReferencing.domain;

import at.spengergasse.at.petfinder.PetType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("MongowithReferencingPet")
@NoArgsConstructor
public class MongoReferencingPet {

    @Id
    private String id;
    private String name;
    private PetType type;
    private int age;
    private double weight;
    private double height;


}
