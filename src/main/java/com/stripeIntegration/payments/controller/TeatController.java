package com.stripeIntegration.payments.controller;

import com.stripeIntegration.payments.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;



@Slf4j
@RestController
@RequestMapping("/test")
public class TeatController {

    @GetMapping("/add")
    public int add(@RequestParam int value1,
                    @RequestParam int value2){
        int sumResult = value1 + value2;
        log.info("value1:{}|value2:{}|sumResult:{}", value1, value2, sumResult);
        return sumResult;

    }


    @GetMapping("add/{value1}/{value2}")
    public int add2(@PathVariable int value1,

                    @PathVariable int value2){
        int sumResult = value1 + value2;
        log.info("value1:{}|value2:{}|sumResult:{}", value1, value2, sumResult);
        return sumResult;

    }
    @GetMapping("/header")
    public String header(@RequestHeader("my-header") String header){
        log.info("header:{}",header);
        return "Header value is " + header;
    }

    @GetMapping("/body")
    public String body(@RequestBody String info){
        log.info("body:{}",info);
        return "The data in the body ==" + info ;
    }

    @GetMapping
    public String test() {
        log.info("This is a testController log message.");
        return "Test successful!";
    }
    @PostMapping("/test2/{pathp1}/{pv1}/789")
    public String test2(
            @RequestParam String param1,
            @RequestParam String param2,
            @PathVariable String pathp1,
            @PathVariable("pv1") int pathp2,
            @RequestHeader("my-header") String header1,
            @RequestBody String body

    ) {

        log.info("Received parameters: param1={}, param2={}, pathp1={}, pathp2={}, header1={} ,body={}", param1, param2, pathp1, pathp2, header1, body);

        return "Test2 successful with parameters: " + param1 + ", " + param2 + ", " + pathp1 + ", " + pathp2 + ", " + header1 + ", " + body ;
    }
    //convert the json to java object using jackson library

    @PostMapping("/createUser")
    public String CreateUser(@RequestBody User user) {
        log.info("Received user: {}", user);
        return "User created successfully: " + user;
    }



}
