package foro.hub.api.domain.issues;

public record IssueRegistryDTO(
        String title,
        String message,
        String author,
        String course
) {
}
