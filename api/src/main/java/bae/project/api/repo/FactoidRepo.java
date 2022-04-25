package bae.project.api.repo;

import bae.project.api.domain.Factoid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoidRepo extends JpaRepository<Factoid, Long> {
    @Query(value = "SELECT * FROM factoid ORDER BY RAND() LIMIT 1",  nativeQuery = true)
    Factoid getRandom();
}
