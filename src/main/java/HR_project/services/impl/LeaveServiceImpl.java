package HR_project.services.impl;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.entities.Leave;
import HR_project.enums.LeaveStatus;
import HR_project.exceptions.BadSituationException;
import HR_project.mapper.LeaveMapper;
import HR_project.repositories.LeaveRequestRepository;
import HR_project.services.EmployeeService;
import HR_project.services.LeaveService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRequestRepository repository;
    private final LeaveMapper mapper;
    private final EmployeeService employeeService;

    /**
     * Apply for a new leave request.
     *
     * @param dto the leave application data transfer object containing
     *            employee ID, start and end dates, and reason
     * @return Created leave request as {@link LeaveResponseDTO}
     */
    @Override
    @Transactional
    public LeaveResponseDTO applyLeave(LeaveApplyDTO dto) {
        Leave leave = mapper.toEntity(dto);
        leave.setEmployee(employeeService.getEmployee(dto.getEmployeeId()));
        return mapper.toDTO(repository.save(leave));
    }

    /**
     * Retrieve all leave requests with pagination.
     *
     * @param page Number of pages
     * @param size Number of records per page
     * @return {@link Page} of {@link LeaveResponseDTO}
     */
    @Override
    public Page<LeaveResponseDTO> getAllLeaves(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());

        var leaves = repository.findAll(pageable);
        long totalElements = leaves.getTotalElements();

        List<LeaveResponseDTO> list = leaves
                .map(mapper::toDTO)
                .toList();

        return new PageImpl<>(list, pageable, totalElements);
    }

    /**
     * Retrieve a leave request by its unique ID.
     *
     * @param id the unique identifier of the leave request
     * @return Leave request as {@link LeaveResponseDTO}
     * @throws RuntimeException if leave not found
     */
    @Override
    public LeaveResponseDTO getLeaveById(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new BadSituationException("Leave with id " + id + " not found"));
        return mapper.toDTO(leave);
    }

    /**
     * Approve a pending leave request.
     *
     * @param id the leave request ID
     * @return Updated leave request as {@link LeaveResponseDTO}
     * @throws RuntimeException if leave not found
     */
    @Override
    public LeaveResponseDTO approveLeave(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new BadSituationException("Leave with id " + id + " not found"));
        leave.setStatus(LeaveStatus.APPROVED);
        return mapper.toDTO(repository.save(leave));
    }

    /**
     * Reject a pending leave request.
     *
     * @param id the leave request ID
     * @return the updated leave request as {@link LeaveResponseDTO}
     * @throws RuntimeException if leave not found
     */
    @Override
    public LeaveResponseDTO rejectLeave(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new BadSituationException("Leave with id " + id + " not found"));
        leave.setStatus(LeaveStatus.REJECTED);
        return mapper.toDTO(repository.save(leave));
    }

    /**
     * Soft-delete a leave request
     *
     * @param id the unique identifier of the leave request
     * @return {@code true} if the leave request was successfully soft-deleted,
     *         {@code false} otherwise
     */
    @Override
    public boolean softDeleteLeave(String id) {
        return repository.softDeleteApplication(id) > 0;
    }
}
