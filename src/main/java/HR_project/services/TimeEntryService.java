package HR_project.services;

import HR_project.dtos.time.TimeDTO;
import HR_project.dtos.time.TimeEntryResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface TimeEntryService {

    /**
     * Records the check-in time for an employee.
     *
     * @param dto {@link TimeDTO} containing employee ID and check-in information
     * @return Saved {@link TimeEntryResponse} with check-in details
     */
    TimeEntryResponse entry(@Valid TimeDTO dto);

    /**
     * Records the check-out time for an employee and calculates late status if applicable.
     *
     * @param dto the {@link TimeDTO} containing employee ID and check-out information
     * @return the updated {@link TimeEntryResponse} with check-out details
     */
    TimeEntryResponse leave(@Valid TimeDTO dto);

    /**
     * Retrieves paginated reports of time entries.
     *
     * @param page Page number
     * @param size Number of records per page
     * @return {@link Page} of {@link TimeEntryResponse}
     */
    Page<TimeEntryResponse> reports(int page, int size);
}
