package at.spengergasse.at.petfinder.JPA.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Table
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class JPAOwner {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jpaID;

    @NonNull
    private String name;
    @NonNull
    private int mana;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<JPAPet> petList = new ArrayList<>();

}



