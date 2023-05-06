package com.jhd.dotime.chat.repository;

import com.jhd.dotime.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    Optional<ChatRoom> findChatRoomById(String id);
    List<ChatRoom> findAll();
}
