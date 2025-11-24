package HR_project.services;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;

public interface LeaveService {

    /**
     * Apply for a new leave request.
     *
     * @param dto the leave application
     * @return Created leave request {@link LeaveResponseDTO}
     */
    LeaveResponseDTO applyLeave(LeaveApplyDTO dto);

    /**
     * Retrieve all leave requests with pagination support.
     *
     * @param page Number of pages
     * @param size Number of records per page
     * @return {@link Page} of {@link LeaveResponseDTO}
     * */
    Page<LeaveResponseDTO> getAllLeaves(int page, int size);

    /**
     * Get a leave request by its unique ID.
     *
     * @param id the leave request ID
     * @return Leave request {@link LeaveResponseDTO}
     */
    LeaveResponseDTO getLeaveById(String id);

    /**
     * Approve a pending leave request.
     *
     * @param id the leave request ID to approve
     * @return Updated leave request {@link LeaveResponseDTO}
     */
    LeaveResponseDTO approveLeave(String id);

    /**
     * Reject a pending leave request.
     *
     * @param id the leave request ID to reject
     * @return Updated leave request {@link LeaveResponseDTO}
     */
    LeaveResponseDTO rejectLeave(String id);

    /**
     * Soft-delete a leave request
     *
     * @param id the unique identifier of the leave request to delete
     * @return {@code true} if the leave request was successfully soft-deleted,
     *         {@code false} otherwise
     */
    boolean softDeleteLeave(@NotBlank String id);
}
