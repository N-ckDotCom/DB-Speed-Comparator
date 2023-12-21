package at.spengergasse.at.petfinder;


import at.spengergasse.at.petfinder.JPA.domain.JPAOwner;
import at.spengergasse.at.petfinder.JPA.domain.JPAPet;

import at.spengergasse.at.petfinder.Mongo.domain.MongoOwner;



import at.spengergasse.at.petfinder.JPA.persistence.OwnerJPARepository;

import at.spengergasse.at.petfinder.Mongo.domain.MongoPet;
import at.spengergasse.at.petfinder.Mongo.persistence.MongoOwnerRepository;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingOwner;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingPet;
import at.spengergasse.at.petfinder.mongoReferencing.persistence.MongoReferencingOwnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import at.spengergasse.at.petfinder.mongoReferencing.persistence.MongoReferencingPetRepository;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class SeedService implements CommandLineRunner {

    private final MongoOwnerRepository mongoOwnerRepository;
    private final OwnerJPARepository ownerJPARepository;

    private final MongoReferencingOwnerRepository mongoReferencingOwnerRepository;
    private final MongoReferencingPetRepository mongoReferencingPetRepository;

    public SeedService(MongoOwnerRepository mongoOwnerRepository, OwnerJPARepository ownerJPARepository,MongoReferencingOwnerRepository mongoReferencingOwnerRepository, MongoReferencingPetRepository mongoReferencingPetRepository) {
        this.mongoOwnerRepository = mongoOwnerRepository;
        this.ownerJPARepository = ownerJPARepository;
        this.mongoReferencingOwnerRepository = mongoReferencingOwnerRepository;
        this.mongoReferencingPetRepository = mongoReferencingPetRepository;
    }

    public void seedIntegrationPostgresDB(int amount)
    {
        for (int i = 0; i < amount; i++) {
            // Create a random owner
            JPAOwner owner = new JPAOwner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));

            // Create random pets and add them to the owner using the addPetToOwner method
            for (int j = 0; j < 3; j++) {
                JPAPet pet = new JPAPet();
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
            MongoOwner owner = new MongoOwner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));


            // Create random pets and add them to the owner using the addPetToOwner method
            for (int j = 0; j < 3; j++) {
                MongoPet pet = new MongoPet();
                pet.setName("Pet" + j);
                pet.setType(getRandomPetType());
                pet.setAge(ThreadLocalRandom.current().nextInt(1, 10));
                pet.setWeight(ThreadLocalRandom.current().nextDouble(1, 20));
                pet.setHeight(ThreadLocalRandom.current().nextDouble(10, 100));

                // Add the pet to the owner
                owner.getPetList().add(pet);
            }
            mongoOwnerRepository.save(owner);
        }
    }


    public void seedIntegrationMongoReferenceDB(int amount)
    {
        for (int i = 0; i < amount; i++) {
            // Create a random owner
            MongoReferencingOwner owner = new MongoReferencingOwner("Owner" + i, ThreadLocalRandom.current().nextInt(1, 100));


            // Create random pets and add them to the owner using the addPetToOwner method
            for (int j = 0; j < 3; j++) {
                MongoReferencingPet pet = new MongoReferencingPet();
                pet.setName("Pet" + j);
                pet.setType(getRandomPetType());
                pet.setAge(ThreadLocalRandom.current().nextInt(1, 10));
                pet.setWeight(ThreadLocalRandom.current().nextDouble(1, 20));
                pet.setHeight(ThreadLocalRandom.current().nextDouble(10, 100));

                // Add the pet to the owner
                mongoReferencingPetRepository.save(pet);

                owner.getPetList().add(pet);
            }
            mongoReferencingOwnerRepository.save(owner);
        }
    }


    @Override
    public void run(String... args) {
        seedIntegrationPostgresDB(10);
        seedIntegrationMongoDB(10);
        seedIntegrationMongoReferenceDB(10);
    }

    private PetType getRandomPetType() {
        PetType[] petTypes = PetType.values();
        int randomIndex = ThreadLocalRandom.current().nextInt(petTypes.length);
        return petTypes[randomIndex];
    }

}