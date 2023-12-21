package at.spengergasse.at.petfinder.Service;

import at.spengergasse.at.petfinder.domain.Owner;
import at.spengergasse.at.petfinder.domain.Pet;
import at.spengergasse.at.petfinder.domain.PetType;
import at.spengergasse.at.petfinder.persistence.jpa.OwnerJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OwnerJPAService {

    @Autowired
    private OwnerJPARepository ownerRepository;

    public Owner createOwner(Owner Owner) {
        Owner.setId(UUID.randomUUID().toString());
        return ownerRepository.saveAndFlush(Owner);
    }

    public Optional<Owner> getOwnerById(String id) {
        return ownerRepository.findById(id);
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner updateOwner(String id, String name, int mana) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            Owner Owner = optionalOwner.get();
            Owner.setName(name);
            Owner.setMana(mana);
            return ownerRepository.saveAndFlush(Owner);
        }
        return null; // Handle not found case
    }

    public Pet addPetToOwner(String OwnerId, Pet pet) {
        Optional<Owner> optionalOwner = ownerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            Owner Owner = optionalOwner.get();
            Owner.getPetList().add(pet);
            ownerRepository.saveAndFlush(Owner);
            return pet;
        }
        return null; // Handle not found case
    }


    public void removePetFromOwner(String OwnerId, Pet pet) {
        Optional<Owner> optionalOwner = ownerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            Owner Owner = optionalOwner.get();
            Owner.getPetList().remove(pet);
            ownerRepository.saveAndFlush(Owner);
        }
    }

    public void testWritings(int amount){
        for (int i = 0; i < amount; i++) {
            Owner owner = new Owner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));


            for (int j = 0; j < 3; j++) {
                Pet pet = new Pet();
                pet.setName("Pet" + j);
                pet.setType(getRandomPetType());
                pet.setAge(ThreadLocalRandom.current().nextInt(1, 10));
                pet.setWeight(ThreadLocalRandom.current().nextDouble(1, 20));
                pet.setHeight(ThreadLocalRandom.current().nextDouble(10, 100));
                owner.getPetList().add(pet);
            }
            ownerRepository.save(owner);
        }
        ownerRepository.flush();
    }

    public void deleteOwner(String id) {
        ownerRepository.deleteById(id);
    }

    private PetType getRandomPetType() {
        PetType[] petTypes = PetType.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(petTypes.length);
        return petTypes[randomIndex];
    }

}
