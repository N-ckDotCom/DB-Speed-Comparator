package at.spengergasse.at.petfinder.mongoReferencing.Service;


import at.spengergasse.at.petfinder.Mongo.domain.MongoOwner;
import at.spengergasse.at.petfinder.PetType;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingOwner;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingPet;
import at.spengergasse.at.petfinder.mongoReferencing.persistence.MongoReferencingOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MongoReferencingOwnerService {

    @Autowired
    private MongoReferencingOwnerRepository mongoReferencingOwnerRepository;


    public MongoReferencingOwner createOwner(MongoReferencingOwner owner) {
        return mongoReferencingOwnerRepository.save(owner);
    }

    public Optional<MongoReferencingOwner> getOwnerById(String id) {
        return mongoReferencingOwnerRepository.findById(id);
    }

    public List<MongoReferencingOwner> getAllOwners() {
        return mongoReferencingOwnerRepository.findAll();
    }

    public MongoReferencingOwner updateOwner(String id, String name, int mana) {
        Optional<MongoReferencingOwner> optionalOwner = mongoReferencingOwnerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            MongoReferencingOwner owner = optionalOwner.get();
            owner.setName(name);
            owner.setMana(mana);
            return mongoReferencingOwnerRepository.save(owner);
        }
        return null; // Handle not found case
    }

    public MongoReferencingPet addPetToOwner(String OwnerId, MongoReferencingPet pet) {
        Optional<MongoReferencingOwner> optionalOwner = mongoReferencingOwnerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            MongoReferencingOwner Owner = optionalOwner.get();
            Owner.getPetList().add(pet);
            mongoReferencingOwnerRepository.save(Owner);
            return pet;
        }
        return null; // Handle not found case
    }


    public void removePetFromOwner(String OwnerId, MongoReferencingPet pet) {
        Optional<MongoReferencingOwner> optionalOwner = mongoReferencingOwnerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            MongoReferencingOwner Owner = optionalOwner.get();
            Owner.getPetList().remove(pet);
            mongoReferencingOwnerRepository.save(Owner);
        }
       
    }

    public void deleteOwner(String id) {
        mongoReferencingOwnerRepository.deleteById(id);
    }

    public void testWritings(int amount){
        for (int i = 0; i < amount; i++) {
            MongoReferencingOwner owner = new MongoReferencingOwner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));

            for (int j = 0; j < 3; j++) {
                MongoReferencingPet pet = new MongoReferencingPet();
                pet.setName("Pet" + j);
                pet.setType(getRandomPetType());
                pet.setAge(ThreadLocalRandom.current().nextInt(1, 10));
                pet.setWeight(ThreadLocalRandom.current().nextDouble(1, 20));
                pet.setHeight(ThreadLocalRandom.current().nextDouble(10, 100));
                owner.getPetList().add(pet);
            }
            mongoReferencingOwnerRepository.save(owner);
        }
    }



    public Optional<MongoReferencingOwner> getOwnerByName(String name) {
        return mongoReferencingOwnerRepository.findFirstByName(name);
    }
    private PetType getRandomPetType() {
        PetType[] petTypes = PetType.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(petTypes.length);
        return petTypes[randomIndex];
    }

}
