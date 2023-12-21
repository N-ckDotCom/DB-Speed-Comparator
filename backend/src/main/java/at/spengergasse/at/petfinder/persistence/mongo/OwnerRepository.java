package at.spengergasse.at.petfinder.persistence.mongo;

import at.spengergasse.at.petfinder.domain.Owner;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;


public interface OwnerRepository extends MongoRepository<Owner, String> {
    public Optional<Owner> findFirstByName(String name);

}