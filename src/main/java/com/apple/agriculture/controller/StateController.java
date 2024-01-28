package com.apple.agriculture.controller;

import com.apple.agriculture.domain.State;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/states")
@CrossOrigin(origins = "http://localhost:3000")
public class StateController {

    @GetMapping
    public List<State> getAllStates() {
        return Arrays.stream(State.values()).sorted().toList();
    }
}
