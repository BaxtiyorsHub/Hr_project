package HR_project.repositories;

import HR_project.entities.Leave;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("NullableProblems")
@Repository
public interface LeaveRequestRepository extends JpaRepository<Leave, String> {

    @Modifying
    @Transactional
    @Query("update Leave set visible = false where id=?1 and visible=true ")
    int softDeleteApplication(String id);
}
