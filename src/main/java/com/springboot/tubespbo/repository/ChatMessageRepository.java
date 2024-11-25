package com.springboot.tubespbo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.tubespbo.model.Alamat;
import com.springboot.tubespbo.model.ChatMessage;
import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.TempChatRoom;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    @Query(value = "SELECT * FROM chat_message WHERE temp_chat_room_id = :room_id", nativeQuery = true)
    List<ChatMessage> findByRoomId(@Param("room_id") String room_id);
}
