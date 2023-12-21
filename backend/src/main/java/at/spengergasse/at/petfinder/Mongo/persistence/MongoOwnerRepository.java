package at.spengergasse.at.petfinder.Mongo.persistence;

import at.spengergasse.at.petfinder.Mongo.domain.MongoOwner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface MongoOwnerRepository extends MongoRepository<MongoOwner, String> {

    Optional<MongoOwner> findFirstByName(String name);

}