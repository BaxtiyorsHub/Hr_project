package HR_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "department")
@Setter
@Getter
public class Department {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "employee_id")
    private String employeeId;

    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Column(name = "position")
    private String position;

    @Column(name = "visible")
    private boolean visible = true;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "created_date")
    private LocalDate createdDate;
}
