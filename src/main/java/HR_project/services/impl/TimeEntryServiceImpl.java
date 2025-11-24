package HR_project.services.impl;

import HR_project.dtos.time.TimeDTO;
import HR_project.dtos.time.TimeEntryResponse;
import HR_project.entities.Employee;
import HR_project.entities.TimeEntry;
import HR_project.exceptions.BadSituationException;
import HR_project.mapper.TimeEntryMapper;
import HR_project.repositories.TimeEntryRepository;
import HR_project.services.TimeEntryService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeEntryServiceImpl implements TimeEntryService {

    private final TimeEntryRepository repository;
    private final TimeEntryMapper mapper;

    /**
     * Registers a check-in time for an employee. If an entry for today already exists,
     * it updates the check-in time and late status.
     *
     * @param dto TimeDTO containing employeeId and lateStatus
     * @return TimeEntryResponse with saved data
     * @throws BadSituationException if employeeId is null
     */
    @Override
    @Transactional
    public TimeEntryResponse entry(@Valid TimeDTO dto) {
        TimeEntry entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    /**
     * Registers a check-out time for an employee
     *
     * @param employeeID ID of {@link Employee}
     * @return TimeEntryResponse with saved data
     * @throws BadSituationException if no check-in exists for today
     */
    @Override
    @Transactional
    public TimeEntryResponse leave(@NotBlank String employeeID) {
        TimeEntry timeEntry = repository.findFirstByEmployeeIdAndVisibleTrueOrderByCreatedDateDesc(employeeID)
                .orElseThrow(() -> new BadSituationException("Cannot check out before check-in"));

        timeEntry.setCheckOutTime(LocalDateTime.now());

        repository.save(timeEntry);

        return mapper.toDTO(timeEntry);
    }

    /**
     * Retrieves a paginated list of all time entries.
     *
     * @param page page number
     * @param size page size
     * @return {@link Page} of {@link TimeEntryResponse}
     */
    @Override
    public Page<TimeEntryResponse> weeklyReports(String employeeId, int page, int size) {

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        PageRequest pageRequest = PageRequest.of(page, size);

        List<TimeEntryResponse> list = repository
                .findWeeklyEntries(employeeId, startOfWeek, endOfWeek)
                .stream()
                .map(mapper::toDTO)
                .toList();


        return new PageImpl<>(list, pageRequest, list.size());
    }

}
