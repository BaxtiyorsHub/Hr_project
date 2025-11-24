package HR_project.repositories;

import HR_project.entities.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@SuppressWarnings("NullableProblems")
public interface TimeEntryRepository extends JpaRepository<TimeEntry, Long> {

    Optional<TimeEntry> findByEmployeeIdAndDate(String employeeId, LocalDate date);

}
