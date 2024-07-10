package foro.hub.api.domain.issues;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



@Table(name = "issues")
@Entity(name = "Issue")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private String created_at;
    private String status; // Unanswered - Resolved
    private String author;
    private String course;

    public Issue(IssueRegistryDTO issue) {
        this.title = issue.title();
        this.message = issue.message();
        this.created_at = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd'T'HH:mm"));
        this.status = "Unanswered";
        this.author = issue.author();
        this.course = issue.course();
    }

}
