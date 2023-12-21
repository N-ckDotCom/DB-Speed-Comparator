package at.spengergasse.at.petfinder.mongoReferencing.persistence;

import at.spengergasse.at.petfinder.Mongo.domain.MongoOwner;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingOwner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface MongoReferencingOwnerRepository extends MongoRepository<MongoReferencingOwner, String> {
    Optional<MongoReferencingOwner> findFirstByName(String name);

}