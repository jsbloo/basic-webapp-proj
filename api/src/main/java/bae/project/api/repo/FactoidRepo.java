package bae.project.api.repo;

import bae.project.api.domain.Factoid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoidRepo extends JpaRepository<Factoid, Long> {
}
