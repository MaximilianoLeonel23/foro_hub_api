package foro.hub.api.domain.issues;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    private Date created_at;
    private String status;
    private String author;
    private String course;
}
