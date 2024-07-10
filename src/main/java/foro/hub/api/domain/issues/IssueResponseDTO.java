package foro.hub.api.domain.issues;

public record IssueResponseDTO(
        Long id,
        String title,
        String message,
        String created_at,
        String status,
        String author,
        String course
) {
}
