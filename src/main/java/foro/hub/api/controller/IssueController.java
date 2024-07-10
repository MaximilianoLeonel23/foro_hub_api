package foro.hub.api.controller;

import foro.hub.api.domain.issues.Issue;
import foro.hub.api.domain.issues.IssueRegistryDTO;
import foro.hub.api.domain.issues.IssueRepository;
import foro.hub.api.domain.issues.IssueResponseDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @GetMapping
    public ResponseEntity<List<IssueResponseDTO>> getAllIssues() {
        List<IssueResponseDTO> issuesFound = issueRepository.findAll().stream()
                .map(issue -> new IssueResponseDTO(
                        issue.getId(),
                        issue.getTitle(),
                        issue.getMessage(),
                        issue.getCreated_at(),
                        issue.getStatus(),
                        issue.getAuthor(),
                        issue.getCourse()))
                .toList();

        if (!issuesFound.isEmpty()) {
            return ResponseEntity.ok(issuesFound);
        } else {
            return null;
        }
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

    @PutMapping
    @Transactional
    public ResponseEntity<IssueResponseDTO> updateIssue(@RequestBody @Valid )


}
