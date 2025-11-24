package HR_project.services.impl;

import HR_project.dtos.FilterDTO;
import HR_project.dtos.employee.EmployeeDTO;
import HR_project.dtos.employee.EmployeeResponseDTO;
import HR_project.entities.Employee;
import HR_project.exceptions.BadSituationException;
import HR_project.mapper.EmployeeMapper;
import HR_project.repositories.EmployeeRepository;
import HR_project.services.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("NullableProblems")
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EntityManager entityManager;
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    /**
     * Method creates employees
     * @param createDto DTO which comes from API for creation
     * @return Created EmployeeResponseDTO
     *
     */
    @Override
    public EmployeeResponseDTO create(@Valid EmployeeDTO createDto) {
        Employee employee = mapper.toEntity(createDto);
        return mapper.toDTO(repository.save(employee));
    }

    /**
     * Method updates employees
     * @param id Employee's id
     * @param updateDto updates of fields
     * @return Updated EmployeeResponseDTO
     *
     */
    @Override
    public EmployeeResponseDTO update(@NotBlank String id, @Valid EmployeeDTO updateDto) {
        Employee byId = repository.findById(id)
                .orElseThrow(() -> new BadSituationException("Employee not found"));

        /*
        if (!updateDto.getFirstName().equalsIgnoreCase(byId.getFirstName()))
            byId.setFirstName(updateDto.getFirstName());
        if (!updateDto.getLastName().equalsIgnoreCase(byId.getLastName()))
            byId.setLastName(updateDto.getLastName());
        if (!updateDto.getPhoneNumber().equals(byId.getPhoneNumber()))
            byId.setPhoneNumber(updateDto.getPhoneNumber());
        if (!updateDto.getPosition().equalsIgnoreCase(byId.getPosition()))
            byId.setPosition(updateDto.getPosition());
        if (!updateDto.getDepartmentName().equalsIgnoreCase(byId.getDepartmentName()))
            byId.setDepartmentName(updateDto.getDepartmentName());
        */ // old code

        mapper.updateEntity(updateDto, byId); // optimized code
        return mapper.toDTO(repository.save(byId));
    }

    /**
     * Method makes employee soft deletion
     * @param id Employee's id to make soft delete
     * @return True/False
     */
    @Override
    public boolean delete(@NotBlank String id) {
        return repository.softDeleteById(id);
    }

    /**
     * Method returns employee by id
     * @param id Employee's id
     * @return EmployeeResponseDTO which found by id
     */
    @Override
    public EmployeeResponseDTO get(@NotBlank String id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new BadSituationException("Employee not found"));
        return mapper.toDTO(employee);
    }

    /**
     * Method returns all employees
     * @param page Number of pages
     * @param size Number to set page size
     * @return List of EmployeeResponseDTOs
     *
     */
    @Override
    public Page<EmployeeResponseDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        var dtos = repository.findAll(pageable);
        long totalElement = dtos.getTotalElements();
        List<EmployeeResponseDTO> list = dtos
                .map(mapper::toDTO)
                .toList();

        return new PageImpl<>(list, pageable, totalElement);
    }

    /**
     * Method makes searches
     * @param page Number of pages
     * @param size Number to set page size
     * @return Page of EmployeeResponseDTO
     */
    @Override
    public Page<EmployeeResponseDTO> search(int page, int size, FilterDTO filterQuery) {
        var result = filterEmployees(filterQuery, page, size);

        long totalCount = result.getTotalElements();
        List<Employee> entityList = result.getContent();

        List<EmployeeResponseDTO> dtoList = entityList.stream().map(mapper::toDTO).toList();

        PageRequest pageRequest = PageRequest.of(page, size);
        return new PageImpl<>(dtoList, pageRequest, totalCount);
    }

    /**Method for get employee entity by id
     * @param employeeId Employee's id
     * @return Employee which found
     * */
    @Override
    public Employee getEmployee(@NotBlank @Valid String employeeId) {
        return repository.findById(employeeId)
                .orElseThrow(() -> new BadSituationException("Employee not found"));
    }

   /**Method makes native query for filter/search
    * @param page Number of pages
    * @param size Number to set page size
    * @param filterDTO Query dto
    * @return Page
    * */
    private Page<Employee> filterEmployees(FilterDTO filterDTO, int page, int size) {

        StringBuilder selectQuery = new StringBuilder("SELECT e FROM Employee e ");
        StringBuilder countQuery = new StringBuilder("SELECT count(e) FROM Employee e ");

        StringBuilder whereClause = new StringBuilder(" WHERE e.visible = true ");

        Map<String, Object> params = new HashMap<>();

        if (filterDTO.getQuery() != null && !filterDTO.getQuery().isBlank()) {
            whereClause.append(" AND (");
            whereClause.append(" lower(e.firstName) LIKE lower(:query) ");
            whereClause.append(" OR lower(e.lastName) LIKE lower(:query) ");
            whereClause.append(" OR e.phoneNumber LIKE :query ");
            whereClause.append(" OR lower(e.position) LIKE lower(:query) ");
            whereClause.append(" OR lower(e.status) LIKE lower(:query) ");
            whereClause.append(" OR lower(e.departmentName) LIKE lower(:query) ");
            whereClause.append(" ) ");
            params.put("query", "%" + filterDTO.getQuery() + "%");
        }

        if (filterDTO.getStartingDate() != null && filterDTO.getEndingDate() != null) {
            whereClause.append(" AND e.createdDate BETWEEN :start AND :end ");
            params.put("start", filterDTO.getStartingDate());
            params.put("end", filterDTO.getEndingDate());
        } else if (filterDTO.getStartingDate() != null) {
            whereClause.append(" AND e.createdDate >= :start ");
            params.put("start", filterDTO.getStartingDate());
        } else if (filterDTO.getEndingDate() != null) {
            whereClause.append(" AND e.createdDate <= :end ");
            params.put("end", filterDTO.getEndingDate());
        }

        selectQuery.append(whereClause);
        countQuery.append(whereClause);

        Query select = entityManager.createQuery(selectQuery.toString());
        select.setFirstResult(page * size);
        select.setMaxResults(size);
        params.forEach(select::setParameter);
        var employees = select.getResultList();

        // Execute count query
        Query count = entityManager.createQuery(countQuery.toString());
        params.forEach(count::setParameter);
        Long total = (Long) count.getSingleResult();

        return new PageImpl<>(employees, PageRequest.of(page, size), total);
    }

}
