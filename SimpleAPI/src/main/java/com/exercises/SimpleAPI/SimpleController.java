package com.exercises.SimpleAPI;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @RequestMapping("/greeting")
    public String greeting(@RequestParam (defaultValue = "") String name) {
        return "Hello " + name + "!!!";
    }
}
