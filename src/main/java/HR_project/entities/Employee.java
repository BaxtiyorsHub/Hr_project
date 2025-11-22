package HR_project.entities;

import HR_project.enums.EmployeeStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "name")
    private String firstName;

    @Column(name = "surname")
    private String lastName;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status = EmployeeStatus.INACTIVE;

    @Column(name = "visible")
    private boolean visible = true;

    @Column(name = "created_date")
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdDate;

}
