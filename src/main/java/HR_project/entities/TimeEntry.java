package HR_project.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "working_hours")
@Setter
@Getter
public class TimeEntry {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private String id;

    @Column(name = "employee_id" , nullable = false)
    private String employeeId; // String

    @JoinColumn(name = "employee_id" , insertable = false, updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @Column(name = "date")
    private LocalDate date; // Qaysi kun

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @Column(name = "check_out_time")
    private LocalDateTime checkOutTime;

    @Column(name = "late_status")
    private boolean lateStatus; // kechikkan yoki yo‘q

    @Column(name = "visible")
    private boolean visible = true;

    @CreationTimestamp
    @Column(name = "created_date")
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdDate;
}
