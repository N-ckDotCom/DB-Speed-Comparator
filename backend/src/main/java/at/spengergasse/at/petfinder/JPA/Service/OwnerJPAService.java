package at.spengergasse.at.petfinder.JPA.Service;

import at.spengergasse.at.petfinder.JPA.domain.JPAOwner;
import at.spengergasse.at.petfinder.JPA.domain.JPAPet;
import at.spengergasse.at.petfinder.PetType;

import at.spengergasse.at.petfinder.JPA.persistence.OwnerJPARepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OwnerJPAService {

    @Autowired
    private OwnerJPARepository ownerRepository;

    public JPAOwner createOwner(JPAOwner Owner) {

        return ownerRepository.saveAndFlush(Owner);
    }

    public Optional<JPAOwner> getOwnerById(String id) {
        return ownerRepository.findById(id);
    }

    public List<JPAOwner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public JPAOwner updateOwner(String id, String name, int mana) {
        Optional<JPAOwner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isPresent()) {
            JPAOwner Owner = optionalOwner.get();
            Owner.setName(name);
            Owner.setMana(mana);
            return ownerRepository.saveAndFlush(Owner);
        }
        return null; // Handle not found case
    }

    public JPAPet addPetToOwner(String OwnerId, JPAPet pet) {
        Optional<JPAOwner> optionalOwner = ownerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            JPAOwner Owner = optionalOwner.get();
            Owner.getPetList().add(pet);
            ownerRepository.saveAndFlush(Owner);
            return pet;
        }
        return null; // Handle not found case
    }


    public void removePetFromOwner(String OwnerId, JPAPet pet) {
        Optional<JPAOwner> optionalOwner = ownerRepository.findById(OwnerId);
        if (optionalOwner.isPresent()) {
            JPAOwner Owner = optionalOwner.get();
            Owner.getPetList().remove(pet);
            ownerRepository.saveAndFlush(Owner);
        }
    }

    public void testWritings(int amount){
        for (int i = 0; i < amount; i++) {
            JPAOwner owner = new JPAOwner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));


            for (int j = 0; j < 3; j++) {
                JPAPet pet = new JPAPet();
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
