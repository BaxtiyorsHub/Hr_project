package HR_project.repositories;

import HR_project.entities.Employee;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@SuppressWarnings("NullableProblems")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Modifying
    @Transactional
    @Query("update Employee e set e.visible=false where e.id=?1 ")
    boolean softDeleteById(@NotBlank String id);
}
