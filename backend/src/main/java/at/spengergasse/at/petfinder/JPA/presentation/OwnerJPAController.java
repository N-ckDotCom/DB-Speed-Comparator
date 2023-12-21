package at.spengergasse.at.petfinder.JPA.presentation;

import at.spengergasse.at.petfinder.JPA.Service.OwnerJPAService;
import at.spengergasse.at.petfinder.JPA.domain.JPAOwner;
import at.spengergasse.at.petfinder.JPA.domain.JPAPet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/jpaOwners")
public class OwnerJPAController {

    @Autowired
    private OwnerJPAService ownerService;

    @PostMapping
    public ResponseEntity<JPAOwner> createOwner(@RequestBody JPAOwner owner) {
        JPAOwner createdOwner = ownerService.createOwner(owner);
        return new ResponseEntity<>(createdOwner, HttpStatus.CREATED);
    }

    @PostMapping("/test/{amount}")
    public ResponseEntity<JPAOwner> testWritings(@PathVariable int amount) {
        ownerService.testWritings(amount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JPAOwner> getOwnerById(@PathVariable String id) {
        Optional<JPAOwner> Owner = ownerService.getOwnerById(id);
        return Owner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Owner> getOwnerByName(@PathVariable String name) {
        Optional<Owner> Owner = ownerService.getOwnerByName(name);
        return Owner.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<JPAOwner>> getAllOwners() {
        List<JPAOwner> owners = ownerService.getAllOwners();
        return new ResponseEntity<>(owners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JPAOwner> updateOwner(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) int mana) {
        JPAOwner updatedOwner = ownerService.updateOwner(id, name, mana);
        return updatedOwner != null
                ? new ResponseEntity<>(updatedOwner, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{OwnerId}/pet")
    public ResponseEntity<JPAPet> addPetToOwner(
            @PathVariable String OwnerId,
            @RequestBody JPAPet pet) {
        JPAPet createdPet = ownerService.addPetToOwner(OwnerId, pet);
        return createdPet != null
                ? new ResponseEntity<>(createdPet, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{OwnerId}/pet")
    public ResponseEntity<Void> removePetFromOwner(
            @PathVariable String OwnerId,
            @RequestBody JPAPet pet) {
        ownerService.removePetFromOwner(OwnerId, pet);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable String id) {
        ownerService.deleteOwner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
