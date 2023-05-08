package com.jhd.dotime.chat.controller;

import com.jhd.dotime.chat.dto.ChatRoomDto;
import com.jhd.dotime.chat.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat-test")
public class ChatRoomTestController {

    private final ChatRoomService chatRoomService;

    //채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms(){

        ModelAndView mv = new ModelAndView("rooms");

        mv.addObject("list", chatRoomService.findAllRoom());

        return mv;
    }

    //채팅방 개설
    @PostMapping(value = "/room")
    public String create(@RequestParam String name, RedirectAttributes rttr){
        ChatRoomDto.Request chatRoomDto = new ChatRoomDto.Request(name);
        rttr.addFlashAttribute("roomName", chatRoomService.createChatroom(chatRoomDto));
        return "redirect:/rooms";
    }

    //채팅방 조회
    @GetMapping("/room")
    public String getRoom(String roomId, Model model){

        model.addAttribute("room", chatRoomService.findChatRoomById(roomId));

        return "room";
    }
}
