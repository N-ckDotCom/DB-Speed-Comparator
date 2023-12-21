package at.spengergasse.at.petfinder.Mongo.presentation;

import at.spengergasse.at.petfinder.Mongo.Service.MongoOwnerService;
import at.spengergasse.at.petfinder.Mongo.domain.MongoOwner;
import at.spengergasse.at.petfinder.Mongo.domain.MongoPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mongoOwners")
public class MongoOwnerController {

    @Autowired
    private MongoOwnerService ownerService;

    @PostMapping
    public ResponseEntity<MongoOwner> createOwner(@RequestBody MongoOwner Owner) {
        MongoOwner createdOwner = ownerService.createOwner(Owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MongoOwner> getOwnerById(@PathVariable String id) {
        Optional<MongoOwner> Owner = ownerService.getOwnerById(id);
        return Owner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<MongoOwner>> getAllOwners() {
        List<MongoOwner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MongoOwner> updateOwner(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int mana) {
        MongoOwner updatedOwner = ownerService.updateOwner(id, name, mana);
        return updatedOwner != null
                ? new ResponseEntity<>(updatedOwner, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{OwnerId}/pet")
    public ResponseEntity<MongoPet> addPetToOwner(
            @PathVariable String OwnerId,
            @RequestBody MongoPet pet) {
        MongoPet createdPet = ownerService.addPetToOwner(OwnerId, pet);
        return createdPet != null
                ? new ResponseEntity<>(createdPet, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{OwnerId}/pet")
    public ResponseEntity<Void> removePetFromOwner(
            @PathVariable String OwnerId,
            @RequestBody MongoPet pet) {
        ownerService.removePetFromOwner(OwnerId, pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/test/{amount}")
    public ResponseEntity<MongoOwner> testWritings(@PathVariable int amount) {
        ownerService.testWritings(amount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
