package com.longder.gov.repository;

import com.longder.gov.entity.po.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
