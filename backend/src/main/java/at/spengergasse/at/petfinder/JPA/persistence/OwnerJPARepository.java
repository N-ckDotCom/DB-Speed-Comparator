package at.spengergasse.at.petfinder.JPA.persistence;

import at.spengergasse.at.petfinder.JPA.domain.JPAOwner;

import org.springframework.data.jpa.repository.JpaRepository;
public interface OwnerJPARepository extends JpaRepository<JPAOwner, String> {
}
