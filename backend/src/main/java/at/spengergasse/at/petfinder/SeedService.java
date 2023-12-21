package at.spengergasse.at.petfinder;

import at.spengergasse.at.petfinder.domain.Owner;
import at.spengergasse.at.petfinder.domain.Pet;
import at.spengergasse.at.petfinder.domain.PetType;

import at.spengergasse.at.petfinder.persistence.jpa.OwnerJPARepository;
import at.spengergasse.at.petfinder.persistence.mongo.OwnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class SeedService implements CommandLineRunner {

    private final OwnerRepository ownerRepository;
    private final OwnerJPARepository ownerJPARepository;

    public SeedService(OwnerRepository ownerRepository, OwnerJPARepository ownerJPARepository) {
        this.ownerRepository = ownerRepository;
        this.ownerJPARepository = ownerJPARepository;
    }

    public void seedIntegrationPostgresDB(int amount)
    {
        for (int i = 0; i < amount; i++) {
            // Create a random owner
            Owner owner = new Owner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));
            owner.setId(UUID.randomUUID().toString());

            // Create random pets and add them to the owner using the addPetToOwner method
            for (int j = 0; j < 3; j++) {
                Pet pet = new Pet();
                pet.setId(UUID.randomUUID().toString());
                pet.setName("Pet" + j);
                pet.setType(getRandomPetType());
                pet.setAge(ThreadLocalRandom.current().nextInt(1, 10));
                pet.setWeight(ThreadLocalRandom.current().nextDouble(1, 20));
                pet.setHeight(ThreadLocalRandom.current().nextDouble(10, 100));

                // Add the pet to the owner
                owner.getPetList().add(pet);
            }
            ownerJPARepository.save(owner);
        }
    }
    public void seedIntegrationMongoDB(int amount)
    {
        for (int i = 0; i < amount; i++) {
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


    @Override
    public void run(String... args) {
        seedIntegrationPostgresDB(10);
    }

    private PetType getRandomPetType() {
        PetType[] petTypes = PetType.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(petTypes.length);
        return petTypes[randomIndex];
    }

}