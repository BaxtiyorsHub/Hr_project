package HR_project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "working_hours")
@Setter
@Getter
public class TimeEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
