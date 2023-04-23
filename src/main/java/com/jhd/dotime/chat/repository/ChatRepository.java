package com.jhd.dotime.chat.repository;

import com.jhd.dotime.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
