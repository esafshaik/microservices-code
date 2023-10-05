package com.cafeteria.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cafeteria.order.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
