package HR_project.entities;

import HR_project.enums.LeaveStatus;
import HR_project.enums.LeaveType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave")
@Setter
@Getter
public class Leave {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "employee_id", nullable = false)
    private String employeeId;

    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_type")
    private LeaveType leaveType;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "reason")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "leave_status")
    private LeaveStatus status = LeaveStatus.PENDING; // PENDING, APPROVED, REJECTED

    @Column(name = "visible")
    private boolean visible = true;

    @Column(name = "created_date")
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdDate;
}
