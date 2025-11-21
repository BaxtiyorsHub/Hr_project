package HR_project.services;


import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;

import java.util.List;

public interface LeaveService {

    LeaveResponseDTO applyLeave(LeaveApplyDTO dto);

    List<LeaveResponseDTO> getAllLeaves();

    LeaveResponseDTO getLeaveById(String id);

    LeaveResponseDTO approveLeave(String id);

    LeaveResponseDTO rejectLeave(String id);
}
