package at.spengergasse.at.petfinder;

import at.spengergasse.at.petfinder.domain.Owner;
import at.spengergasse.at.petfinder.domain.Pet;
import at.spengergasse.at.petfinder.domain.PetType;

import at.spengergasse.at.petfinder.persistence.mongo.OwnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SeedService implements CommandLineRunner {

    private final OwnerRepository ownerRepository;

    public SeedService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void run(String... args) {
        for (int i = 0; i < 100; i++) {
            // Create a random owner
            Owner owner = new Owner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));


            // Create random pets and add them to the owner using the addPetToOwner method
            for (int j = 0; j < 3; j++) {
                Pet pet = new Pet();
                pet.setName("Pet" + j);
                pet.setType(getRandomPetType());
                pet.setAge(ThreadLocalRandom.current().nextInt(1, 10));
                pet.setWeight(ThreadLocalRandom.current().nextDouble(1, 20));
                pet.setHeight(ThreadLocalRandom.current().nextDouble(10, 100));

                // Add the pet to the owner
                owner.getPetList().add(pet);
            }
            ownerRepository.save(owner);
        }
    }

    private PetType getRandomPetType() {
        PetType[] petTypes = PetType.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(petTypes.length);
        return petTypes[randomIndex];
    }

}