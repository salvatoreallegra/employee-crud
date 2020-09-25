package com.salallegra.employeecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.salallegra.employeecrud.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
