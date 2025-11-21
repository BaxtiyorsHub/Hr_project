package HR_project.services.impl;

import HR_project.dtos.employee.EmployeeCreateDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.dtos.employee.EmployeeUpdateDTO;
import HR_project.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public EmployeeResponseDTO create(EmployeeCreateDTO createDto) {
        return null;
    }

    @Override
    public EmployeeResponseDTO update(EmployeeUpdateDTO updateDto) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public EmployeeResponseDTO get(String id) {
        return null;
    }

    @Override
    public List<EmployeeResponseDTO> getAll() {
        return List.of();
    }
}
