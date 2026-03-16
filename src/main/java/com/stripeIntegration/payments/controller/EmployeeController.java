package com.stripeIntegration.payments.controller;

import com.stripeIntegration.payments.pojo.User;
import com.stripeIntegration.payments.pojo.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/employees")
public class EmployeeController {
//    @PostMapping
//    public String createEmployee(@RequestBody User user) {
//        log.info("Inside CreateEmployee method" + user);
//        return "Employee created successfully with name: " + user;
//    }

    @PostMapping
    public UserResponse crewateEmployeeResponse(@RequestBody User user) {
        log.info("Inside CreateEmployee method" + user);
        UserResponse response = new UserResponse();
        response.setId("12345");
        return response;
    }

    @GetMapping("/{id}")
    public UserResponse getEmployeeByID(@PathVariable String id) {
        log.info("Inside getEmployeeByID method" + id);
        UserResponse response = new UserResponse();
        response.setId(id);
        return response;
    }

    @GetMapping
    public List <UserResponse> getAllEmployees() {
        log.info("Inside getAllEmployees method");
        UserResponse response = new UserResponse();

        List<UserResponse> responseList = new ArrayList<UserResponse>();
        responseList.add(response);
        return responseList;
    }


}
