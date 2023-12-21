package at.spengergasse.at.petfinder.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document
@Table
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Owner {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jpaID;
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private int mana;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Pet> petList = new ArrayList<>();

}



