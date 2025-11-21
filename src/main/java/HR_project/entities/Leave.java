package HR_project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "leave")
@Setter
@Getter
public class Leave {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String  id;
}
