package com.springboot.tubespbo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.ChatMessage;
import com.springboot.tubespbo.model.TempChatRoom;
import com.springboot.tubespbo.repository.ChatMessageRepository;
import com.springboot.tubespbo.repository.TempChatRoomRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class ChatMessageController {
    
    @Autowired
    TempChatRoomRepository tempChatRoomRepository;

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @PostMapping("/dashboard/jasa/chat/kirim_pesan")
    public String sendMessage(
        @RequestParam("id_pesanan") String id_pesanan,
        @RequestParam("id_chat_room") String id_chat_room,
        @RequestParam("pesan") String pesan,
        HttpSession session
    ) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }

        Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");

        Optional<TempChatRoom> tOption = tempChatRoomRepository.findById(Long.parseLong(id_chat_room));
        if(tOption.isPresent()){
            TempChatRoom tempChatRoom = tOption.get();
            ChatMessage chatMessage = new ChatMessage(sessiondata.getUser(), pesan, tempChatRoom);
            chatMessageRepository.save(chatMessage);
        }

        return "redirect:/dashboard/jasa/chat/" + id_pesanan;
    }
}
