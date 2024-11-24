package com.springboot.tubespbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.Alamat;
import com.springboot.tubespbo.model.ChatMessage;
import com.springboot.tubespbo.model.TempChatRoom;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
