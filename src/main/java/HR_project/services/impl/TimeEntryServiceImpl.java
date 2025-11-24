package HR_project.services.impl;

import HR_project.dtos.time.TimeDTO;
import HR_project.dtos.time.TimeEntryResponse;
import HR_project.entities.TimeEntry;
import HR_project.mapper.TimeEntryMapper;
import HR_project.repositories.TimeEntryRepository;
import HR_project.services.TimeEntryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeEntryServiceImpl implements TimeEntryService {

    private final TimeEntryRepository repository;
    private final TimeEntryMapper mapper;

    @Override
    public TimeEntryResponse entry(@Valid TimeDTO dto) {
        TimeEntry entity = mapper.toEntity(dto);

        Optional<TimeEntry> existing = repository.findByEmployeeIdAndDate(
                dto.getEmployeeId(),
                LocalDate.now()
        );

        if (existing.isPresent()) {
            TimeEntry timeEntry = existing.get();
            timeEntry.setCheckInTime(dto.getCheckInTime());
            timeEntry.setLateStatus(dto.isLateStatus());
            return mapper.toDTO(repository.save(timeEntry));
        }

        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public TimeEntryResponse leave(@Valid TimeDTO dto) {
        Optional<TimeEntry> existing = repository.findByEmployeeIdAndDate(
                dto.getEmployeeId(),
                LocalDate.now()
        );

        if (existing.isPresent()) {
            TimeEntry timeEntry = existing.get();
            timeEntry.setCheckOutTime(dto.getCheckOutTime());
            return mapper.toDTO(repository.save(timeEntry));
        } else {
            TimeEntry entity = mapper.toEntity(dto);
            return mapper.toDTO(repository.save(entity));
        }
    }

    @Override
    public Page<TimeEntryResponse> reports(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        var saved = repository.findAll(pageRequest);
        long totalElements = saved.getTotalElements();

        List<TimeEntryResponse> list = saved.map(mapper::toDTO).toList();

        return new PageImpl<>(list, pageRequest, totalElements);
    }
}
