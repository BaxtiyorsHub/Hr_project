package HR_project.services.impl;

import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.entities.Employee;
import HR_project.exceptions.BadSituationException;
import HR_project.mapper.EmployeeMapper;
import HR_project.repositories.EmployeeRepository;
import HR_project.services.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    /**
     * Method creates employees
     * @return created EmployeeResponseDTO
     * */
    @Override
    public EmployeeResponseDTO create(@Valid @NotNull EmployeeDTO createDto) {
        Employee employee = mapper.toEntity(createDto);
        return mapper.toDTO(repository.save(employee));
    }

    /**
     * Method updates employees
     * @return updated EmployeeResponseDTO
     * */
    @Override
    public EmployeeResponseDTO update(@NotBlank String id, @NotNull @Valid EmployeeDTO updateDto) {
        Employee byId = repository.findById(id)
                .orElseThrow(() -> new BadSituationException("Employee not found"));
        if (!updateDto.getFirstName().equalsIgnoreCase(byId.getFirstName()))
            byId.setFirstName(updateDto.getFirstName());
        if (!updateDto.getLastName().equalsIgnoreCase(byId.getLastName()))
            byId.setLastName(updateDto.getLastName());
        if (!updateDto.getPhoneNumber().equals(byId.getPhoneNumber()))
            byId.setPhoneNumber(updateDto.getPhoneNumber());

        return mapper.toDTO(repository.save(byId));
    }

    /**Method makes employee soft deletion
     * @return True/False
     * */
    @Override
    public boolean delete(@NotBlank String id) {
        return repository.softDeleteById(id);
    }

    /**Method returns employee by id
     * @return EmployeeResponseDTO by id
     * */
    @Override
    public EmployeeResponseDTO get(@NotBlank String id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new BadSituationException("Employee not found"));
        return mapper.toDTO(employee);
    }

    /**Method returns all employees
     * @return List of EmployeeResponseDTOs
     * */
    @Override
    public List<EmployeeResponseDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}
