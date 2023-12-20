package at.spengergasse.at.petfinder.persistence.jpa;

import at.spengergasse.at.petfinder.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OwnerJPARepository extends JpaRepository<Owner, String> {
}
