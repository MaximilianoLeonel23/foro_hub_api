package foro.hub.api.controller;

import foro.hub.api.domain.issues.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<IssueResponseDTO> createIssue(@RequestBody @Valid IssueRegistryDTO issueRegistryDTO, UriComponentsBuilder uriComponentsBuilder) {
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
        URI uri = uriComponentsBuilder.path("/issues/{id}").buildAndExpand(savedIssue.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<IssueResponseDTO> updateIssue(@RequestBody @Valid IssueUpdateDTO issueUpdateDTO, @PathVariable Long id) {
        Optional<Issue> issueFound = issueRepository.findById(id);
        if (issueFound.isPresent()) {
            Issue issue = issueFound.get();
            issue.update(issueUpdateDTO);
            issueRepository.save(issue);
            return ResponseEntity.ok(new IssueResponseDTO(
                    issue.getId(),
                    issue.getTitle(),
                    issue.getMessage(),
                    issue.getCreated_at(),
                    issue.getStatus(),
                    issue.getAuthor(),
                    issue.getCourse()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<IssueResponseDTO> deleteIssue(@PathVariable Long id) {
        Optional<Issue> issueFound = issueRepository.findById(id);
        if (issueFound.isPresent()) {
            Issue issue = issueFound.get();
            issueRepository.delete(issue);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDTO> getDetailedIssue(@PathVariable Long id) {
        Optional<Issue> issueFound = issueRepository.findById(id);
        if (issueFound.isPresent()) {
            Issue issue = issueFound.get();
            return ResponseEntity.ok(new IssueResponseDTO(
                    issue.getId(),
                    issue.getTitle(),
                    issue.getMessage(),
                    issue.getCreated_at(),
                    issue.getStatus(),
                    issue.getAuthor(),
                    issue.getCourse()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
