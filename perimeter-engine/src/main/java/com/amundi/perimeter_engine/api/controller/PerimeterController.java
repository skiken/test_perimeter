package com.amundi.perimeter_engine.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/perimeter")
public class PerimeterController {

    @GetMapping
    public void compute(@RequestParam String tenant_id){
        //todo
    }
}
