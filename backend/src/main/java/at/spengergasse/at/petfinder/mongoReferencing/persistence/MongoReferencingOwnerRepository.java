package at.spengergasse.at.petfinder.mongoReferencing.persistence;

import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingOwner;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoReferencingOwnerRepository extends MongoRepository<MongoReferencingOwner, String> {

}