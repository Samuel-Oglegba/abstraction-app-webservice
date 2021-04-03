package com.example.abstractionapp.webserviceabstractionapp;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * To run cucumber test.
 */
//@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/test/resources")
//@CucumberOptions(features = "classpath:features", plugin = {"pretty", "json:target/cucumber-report.json"})
public class CucumberTest {

    public String testing(int number){
        if(number == 0) throw new IllegalArgumentException("Invalid number");
        if(number % 3 == 0) return "cool";
        if(number % 5 ==0) return "not so cool";

        return String.valueOf(number);
    }
}