package foro.hub.api.controller;

import foro.hub.api.domain.issues.Issue;
import foro.hub.api.domain.issues.IssueRegistryDTO;
import foro.hub.api.domain.issues.IssueRepository;
import foro.hub.api.domain.issues.IssueResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @GetMapping
    public ResponseEntity<String> getAllIssues() {
        return ResponseEntity.ok("This is an issue");
    }

    @PostMapping
    public ResponseEntity<IssueResponseDTO> createIssue(@RequestBody @Valid IssueRegistryDTO issueRegistryDTO) {
        Issue issue = new Issue(issueRegistryDTO);
        Issue savedIssue = issueRepository.save(issue);
        IssueResponseDTO response = new IssueResponseDTO(
                savedIssue.getId(),
                savedIssue.getTitle(),
                savedIssue.getMessage(),
                savedIssue.getCreated_at(),
                savedIssue.getStatus(),
                savedIssue.getAuthor(),
                savedIssue.getCourse()

        );
        return ResponseEntity.ok(response);
    }

}
