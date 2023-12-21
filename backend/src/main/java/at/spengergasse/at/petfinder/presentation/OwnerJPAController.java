package at.spengergasse.at.petfinder.presentation;

import at.spengergasse.at.petfinder.Service.OwnerJPAService;
import at.spengergasse.at.petfinder.Service.OwnerService;
import at.spengergasse.at.petfinder.domain.Owner;
import at.spengergasse.at.petfinder.domain.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ownersJPA")
public class OwnerJPAController {

    @Autowired
    private OwnerJPAService ownerService;

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner Owner) {
        Owner createdOwner = ownerService.createOwner(Owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @PostMapping("/test/{amount}")
    public ResponseEntity<Owner> testWritings(@PathVariable int amount) {
        ownerService.testWritings(amount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable String id) {
        Optional<Owner> Owner = ownerService.getOwnerById(id);
        return Owner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int mana) {
        Owner updatedOwner = ownerService.updateOwner(id, name, mana);
        return updatedOwner != null
                ? new ResponseEntity<>(updatedOwner, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{OwnerId}/pet")
    public ResponseEntity<Pet> addPetToOwner(
            @PathVariable String OwnerId,
            @RequestBody Pet pet) {
        Pet createdPet = ownerService.addPetToOwner(OwnerId, pet);
        return createdPet != null
                ? new ResponseEntity<>(createdPet, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{OwnerId}/pet")
    public ResponseEntity<Void> removePetFromOwner(
            @PathVariable String OwnerId,
            @RequestBody Pet pet) {
        ownerService.removePetFromOwner(OwnerId, pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
