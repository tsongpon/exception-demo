package dev.songpon.exceptiondemo.v2.controller;

import dev.songpon.exceptiondemo.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingControllerV2 {

    @Autowired
    private PingService service;

    @GetMapping("/v2/ping")
    public String pint() {
        return service.doPing(true);
    }
}
