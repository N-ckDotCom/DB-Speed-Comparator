package at.spengergasse.at.petfinder.Mongo.persistence;

import at.spengergasse.at.petfinder.Mongo.domain.MongoOwner;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MongoOwnerRepository extends MongoRepository<MongoOwner, String> {

}