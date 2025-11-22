package HR_project.services;

import HR_project.dtos.department.DepartmentDTO;
import HR_project.dtos.department.DepartmentResponseDTO;

public interface DepartmentService {

    DepartmentResponseDTO create(DepartmentDTO dto);
}
