package re.btvn07.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import re.btvn07.dto.ApiResponse;
import re.btvn07.dto.CandidateCreateDTO;
import re.btvn07.dto.CandidateUpdateDTO;
import re.btvn07.entity.Candidate;
import re.btvn07.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService service;
    private final CandidateService candidateService;

    public CandidateController(CandidateService service, CandidateService candidateService) {
        this.service = service;
        this.candidateService = candidateService;
    }
    @PostMapping
    public ResponseEntity<?> createCandidate(@Valid @RequestBody CandidateCreateDTO dto) {
        Candidate createdCandidate = service.createCandidate(dto);
        return ResponseEntity.status(201).body(createdCandidate);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Candidate>> updateCandidate
            (@PathVariable int id, @Valid @ModelAttribute CandidateUpdateDTO dto) {
        Candidate candidate = candidateService.updateCandidate(id, dto);
        ApiResponse<Candidate> response = new ApiResponse<>(
          "200",
          "Cập nhập thành công",
          candidate
        );
        return ResponseEntity.ok(response);
    }
}
