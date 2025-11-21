package HR_project.services.impl;

import HR_project.dtos.leave.LeaveApplyDTO;
import HR_project.dtos.leave.LeaveResponseDTO;
import HR_project.entities.Leave;
import HR_project.enums.LeaveStatus;
import HR_project.mapper.LeaveMapper;
import HR_project.repositories.LeaveRequestRepository;
import HR_project.services.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRequestRepository repository;
    private final LeaveMapper mapper;

    @Override
    public LeaveResponseDTO applyLeave(LeaveApplyDTO dto) {
        Leave leave = mapper.toEntity(dto);
        leave.setStatus(LeaveStatus.PENDING);
        return mapper.toDTO(repository.save(leave));
    }

    @Override
    public List<LeaveResponseDTO> getAllLeaves() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
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
}
