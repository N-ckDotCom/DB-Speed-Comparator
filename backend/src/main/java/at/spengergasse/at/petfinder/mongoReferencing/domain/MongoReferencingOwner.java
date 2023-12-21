package at.spengergasse.at.petfinder.mongoReferencing.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document("MongowithReferencingOwner")
@RequiredArgsConstructor
@NoArgsConstructor
public class MongoReferencingOwner {

    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private int mana;


    @DBRef
    private List<MongoReferencingPet> petList = new ArrayList<>();

}



