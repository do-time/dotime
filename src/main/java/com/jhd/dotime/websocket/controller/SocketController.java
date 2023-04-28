package com.jhd.dotime.websocket.controller;


import com.jhd.dotime.websocket.service.SocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class SocketController {
    private final SocketService socketService;
}
