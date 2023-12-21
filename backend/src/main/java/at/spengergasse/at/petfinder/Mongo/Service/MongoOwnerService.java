package at.spengergasse.at.petfinder.Mongo.Service;

import at.spengergasse.at.petfinder.Mongo.domain.MongoOwner;
import at.spengergasse.at.petfinder.Mongo.domain.MongoPet;
import at.spengergasse.at.petfinder.Mongo.persistence.MongoOwnerRepository;
import at.spengergasse.at.petfinder.PetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MongoOwnerService {

    @Autowired
    private MongoOwnerRepository ownerRepository;


    public MongoOwner createOwner(MongoOwner Owner) {
        return ownerRepository.save(Owner);
    }

    public Optional<MongoOwner> getOwnerById(String id) {
        return ownerRepository.findById(id);
    }

    public List<MongoOwner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public MongoOwner updateOwner(String id, String name, int mana) {
        Optional<MongoOwner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            MongoOwner Owner = optionalOwner.get();
            Owner.setName(name);
            Owner.setMana(mana);
            return ownerRepository.save(Owner);
        }
        return null; // Handle not found case
    }

    public MongoPet addPetToOwner(String OwnerId, MongoPet pet) {
        Optional<MongoOwner> optionalOwner = ownerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            MongoOwner Owner = optionalOwner.get();
            Owner.getPetList().add(pet);
            ownerRepository.save(Owner);
            return pet;
        }
        return null; // Handle not found case
    }


    public void removePetFromOwner(String OwnerId, MongoPet pet) {
        Optional<MongoOwner> optionalOwner = ownerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            MongoOwner Owner = optionalOwner.get();
            Owner.getPetList().remove(pet);
            ownerRepository.save(Owner);
        }
       
    }

    public void deleteOwner(String id) {
        ownerRepository.deleteById(id);
    }

    public void testWritings(int amount){
        for (int i = 0; i < amount; i++) {
            MongoOwner owner = new MongoOwner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));

            for (int j = 0; j < 3; j++) {
                MongoPet pet = new MongoPet();
                pet.setName("Pet" + j);
                pet.setType(getRandomPetType());
                pet.setAge(ThreadLocalRandom.current().nextInt(1, 10));
                pet.setWeight(ThreadLocalRandom.current().nextDouble(1, 20));
                pet.setHeight(ThreadLocalRandom.current().nextDouble(10, 100));
                owner.getPetList().add(pet);
            }
            ownerRepository.save(owner);
        }
    }

    public Optional<MongoOwner> getOwnerByName(String name) {
        return ownerRepository.findFirstByName(name);
    }
    private PetType getRandomPetType() {
        PetType[] petTypes = PetType.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(petTypes.length);
        return petTypes[randomIndex];
    }

}
