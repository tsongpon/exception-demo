package dev.songpon.exceptiondemo.service;

import exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class PingService {
    public String doPing(boolean isError) {
        if (isError) {
            throw new IllegalStateException();
        }
        return "pong";
    }
}
