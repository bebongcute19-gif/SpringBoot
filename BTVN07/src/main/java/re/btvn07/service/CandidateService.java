package re.btvn07.service;

import re.btvn07.dto.CandidateCreateDTO;
import re.btvn07.dto.CandidateUpdateDTO;
import re.btvn07.entity.Candidate;

public interface CandidateService {
    Candidate createCandidate(CandidateCreateDTO dto);
    Candidate updateCandidate(Integer id, CandidateUpdateDTO dto);
}
