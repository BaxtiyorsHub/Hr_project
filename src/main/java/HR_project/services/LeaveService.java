package HR_project.services;


import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;

public interface LeaveService {

    LeaveResponseDTO applyLeave(LeaveApplyDTO dto);

    Page<LeaveResponseDTO> getAllLeaves(int page, int size);

    LeaveResponseDTO getLeaveById(String id);

    LeaveResponseDTO approveLeave(String id);

    LeaveResponseDTO rejectLeave(String id);

    boolean softDeleteLeave(@NotBlank String id);
}
