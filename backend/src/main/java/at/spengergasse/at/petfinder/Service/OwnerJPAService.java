package at.spengergasse.at.petfinder.Service;

import at.spengergasse.at.petfinder.domain.Owner;
import at.spengergasse.at.petfinder.domain.Pet;
import at.spengergasse.at.petfinder.persistence.jpa.OwnerJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public void deleteOwner(String id) {
        ownerRepository.deleteById(id);
    }
}
