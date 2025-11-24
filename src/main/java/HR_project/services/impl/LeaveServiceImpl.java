package HR_project.services.impl;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.entities.Leave;
import HR_project.enums.LeaveStatus;
import HR_project.mapper.LeaveMapper;
import HR_project.repositories.LeaveRequestRepository;
import HR_project.services.EmployeeService;
import HR_project.services.LeaveService;
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

    @Override
    public LeaveResponseDTO applyLeave(LeaveApplyDTO dto) {
        Leave leave = mapper.toEntity(dto);
        leave.setEmployee(employeeService.getEmployee(dto.getEmployeeId()));
        return mapper.toDTO(repository.save(leave));
    }

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

    @Override
    public LeaveResponseDTO getLeaveById(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        return mapper.toDTO(leave);
    }

    @Override
    public LeaveResponseDTO approveLeave(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.APPROVED);
        return mapper.toDTO(repository.save(leave));
    }

    @Override
    public LeaveResponseDTO rejectLeave(String id) {
        Leave leave = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
        leave.setStatus(LeaveStatus.REJECTED);
        return mapper.toDTO(repository.save(leave));
    }

    @Override
    public boolean softDeleteLeave(String id) {
        return repository.softDeleteApplication(id) > 0;
    }
}
