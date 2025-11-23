package HR_project.repositories;

import HR_project.entities.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("NullableProblems")
@Repository
public interface LeaveRequestRepository extends JpaRepository<Leave, String> {

}
