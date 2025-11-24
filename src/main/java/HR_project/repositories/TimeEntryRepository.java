package HR_project.repositories;

import HR_project.entities.TimeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("NullableProblems")
public interface TimeEntryRepository extends JpaRepository<TimeEntry, String> {

    Optional<TimeEntry> findFirstByEmployeeIdAndVisibleTrueOrderByCreatedDateDesc(String employeeId);

    @Query("from TimeEntry where employeeId = :employeeId and date between :start and :end and visible = true")
    List<TimeEntry> findWeeklyEntries(@Param("employeeId") String employeeId,
                                      @Param("start") LocalDate start,
                                      @Param("end") LocalDate end);

}
