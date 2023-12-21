package at.spengergasse.at.petfinder.mongoReferencing.persistence;

import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingOwner;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingPet;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoReferencingPetRepository extends MongoRepository<MongoReferencingPet, String> {

}