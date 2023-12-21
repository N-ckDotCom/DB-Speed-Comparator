package at.spengergasse.at.petfinder.persistence.jpa;

import at.spengergasse.at.petfinder.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerJPARepository extends JpaRepository<Owner, String> {
    public Optional<Owner> findFirstByName(String name);
}
