package com.springCloud.eventBus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventBus")
public class EventController {
    @GetMapping("/")
    public String getDefault() {
        return "Spring cloud Custom Event Bus";
    }
}
