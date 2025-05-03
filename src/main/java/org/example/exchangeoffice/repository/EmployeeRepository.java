package org.example.exchangeoffice.repository;

import org.example.exchangeoffice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


}
