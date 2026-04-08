package re.btvn07.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.btvn07.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
