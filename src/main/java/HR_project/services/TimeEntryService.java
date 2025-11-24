package HR_project.services;


import HR_project.dtos.time.TimeDTO;
import HR_project.dtos.time.TimeEntryResponse;
import org.springframework.data.domain.Page;

public interface TimeEntryService {

    TimeEntryResponse entry(TimeDTO dto);

    TimeEntryResponse leave(TimeDTO dto);

    Page<TimeEntryResponse> reports(int page,int size);
}
