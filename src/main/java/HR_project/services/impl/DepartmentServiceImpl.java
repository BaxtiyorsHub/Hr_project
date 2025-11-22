package HR_project.services.impl;

import HR_project.dtos.department.DepartmentDTO;
import HR_project.dtos.department.DepartmentResponseDTO;
import HR_project.repositories.DepartmentRepository;
import HR_project.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public DepartmentResponseDTO create(DepartmentDTO dto) {
        return null;
    }
}
