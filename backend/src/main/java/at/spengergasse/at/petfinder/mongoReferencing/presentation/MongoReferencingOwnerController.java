package at.spengergasse.at.petfinder.mongoReferencing.presentation;

import at.spengergasse.at.petfinder.mongoReferencing.Service.MongoReferencingOwnerService;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingOwner;
import at.spengergasse.at.petfinder.mongoReferencing.domain.MongoReferencingPet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongoReferencingOwners")
public class MongoReferencingOwnerController {

    @Autowired
    private MongoReferencingOwnerService mongoReferencingOwnerService;

    @PostMapping
    public ResponseEntity<MongoReferencingOwner> createOwner(@RequestBody MongoReferencingOwner Owner) {
        MongoReferencingOwner createdOwner = mongoReferencingOwnerService.createOwner(Owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MongoReferencingOwner> getOwnerById(@PathVariable String id) {
        Optional<MongoReferencingOwner> Owner = mongoReferencingOwnerService.getOwnerById(id);
        return Owner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<MongoReferencingOwner>> getAllOwners() {
        List<MongoReferencingOwner> owners = mongoReferencingOwnerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MongoReferencingOwner> updateOwner(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int mana) {
        MongoReferencingOwner updatedOwner = mongoReferencingOwnerService.updateOwner(id, name, mana);
        return updatedOwner != null
                ? new ResponseEntity<>(updatedOwner, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{OwnerId}/pet")
    public ResponseEntity<MongoReferencingPet> addPetToOwner(
            @PathVariable String OwnerId,
            @RequestBody MongoReferencingPet pet) {
        MongoReferencingPet createdPet = mongoReferencingOwnerService.addPetToOwner(OwnerId, pet);
        return createdPet != null
                ? new ResponseEntity<>(createdPet, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{OwnerId}/pet")
    public ResponseEntity<Void> removePetFromOwner(
            @PathVariable String OwnerId,
            @RequestBody MongoReferencingPet pet) {
        mongoReferencingOwnerService.removePetFromOwner(OwnerId, pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        mongoReferencingOwnerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
