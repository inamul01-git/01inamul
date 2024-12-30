package com.crm.service;

import com.crm.entity.Employee;
import com.crm.exception.ResourceNotFound;
import com.crm.payload.EmployeeDto;
import com.crm.repository.EmployeeRepository;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {


    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;

    public EmployeeService(
            EmployeeRepository employeeRepository,
            ModelMapper modelMapper) {
            this.employeeRepository = employeeRepository;
            this.modelMapper = modelMapper;
    }

    public EmployeeDto addEmployee(
            EmployeeDto dto){
        Employee employee = mapToEntity(dto);
        Employee emp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(emp);
        employeeDto.setDate(new Date());
        return employeeDto;
    }

    public void deleteEmployee(
            Long id) {
            employeeRepository.deleteById(id);
    }

    public EmployeeDto updateEmployee(Long id, EmployeeDto dto) {
        Employee employee = mapToEntity(dto);
        employee.setId(id);
        Employee updatedEmp = employeeRepository.save(employee);
        EmployeeDto employeeDto = mapToDto(updatedEmp);
        employeeDto.setDate(new Date());
        return employeeDto;
    }
   // Optional<Employee> byId = employeeRepository.findById(id);

//        Employee employee = byId.get();
//
//        employee.setName(dto.getName());
//        employee.setEmailId(dto.getEmailId());
//        employee.setMobile(dto.getMobile());
      //  employeeRepository.save(employee);


    public List<EmployeeDto> getEmployee(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo, pageSize,sort);
        Page<Employee> all = employeeRepository.findAll(page);
        List<Employee> employees = all.getContent();
        List<EmployeeDto> dto = employees.stream().map(e->mapToDto(e)).collect(Collectors.toList());
        return dto;
    }
        EmployeeDto mapToDto(Employee employee){

            EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
            return dto;


//            EmployeeDto dto = new EmployeeDto();
//             dto.setId(employee.getId());
//             dto.setName(employee.getName());
//             dto.setEmailId(employee.getEmailId());
//             dto.setMobile(employee.getMobile());


        }
    Employee mapToEntity(EmployeeDto dto){
        Employee emp = modelMapper.map(dto, Employee.class);
        return emp;
//        Employee emp = new Employee();
//        emp.setId(dto.getId());
//        emp.setName(dto.getName());
//        emp.setEmailId(dto.getEmailId());
//        emp.setMobile(dto.getMobile());

    }

    public EmployeeDto getEmployeeById(long empId) {


        Employee empById = employeeRepository.findById(empId).orElseThrow(
                ()->new ResourceNotFound("Employee Id not found :"+empId)
        );
        EmployeeDto dto= mapToDto(empById);
        return dto;
//        Optional<Employee> epId2 = employeeRepository.findById(empId);
//        Employee employee = epId.get();
//        EmployeeDto employeeDto =  mapToDto(employee);
//        employeeDto.setDate(new Date());
//        return employeeDto;

    }
}

