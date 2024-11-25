package com.springboot.tubespbo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.tubespbo.local_model.ChatMessageDTO;
import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.ChatMessage;
import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.TempChatRoom;
import com.springboot.tubespbo.repository.ChatMessageRepository;
import com.springboot.tubespbo.repository.RiwayatPesananRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TempChatRoomController {

    @Autowired
    RiwayatPesananRepository riwayatPesananRepository;

    @Autowired
    ChatMessageRepository chatMessageRepository;


    @GetMapping("/getMessages")
    @ResponseBody
    public List<ChatMessageDTO> getMessages(@RequestParam("chatRoomId") String chatRoomId) {
        List<ChatMessage> messages = chatMessageRepository.findByRoomId(chatRoomId);
        return messages.stream()
                .map(ChatMessageDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/dashboard/jasa/chat/{id}")
    public String chatRoom(
            @PathVariable("id") String id,
            HttpSession session,
            Model model) {
        Sessiondata sessiondata;
        sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if (sessiondata == null) {
            return "redirect:/login";
        }

        Optional<RiwayatPesanan> rOptional = riwayatPesananRepository.findById(Long.parseLong(id));
        RiwayatPesanan riwayatPesanan;

        if (rOptional.isPresent()) {
            riwayatPesanan = rOptional.get();
            if(!riwayatPesanan.getTempChatRoom().isStatus()){
                return "redirect:/dashboard";
            }

            if (riwayatPesanan.getCustomer().getId().equals(sessiondata.getUser().getId())
                    || riwayatPesanan.getPenyediaJasa().getId().equals(sessiondata.getUser().getId())) {
                model.addAttribute("riwayat", riwayatPesanan);
                return "chatDashboard";
            } else {
                return "redirect:/dashboard";
            }
        }

        if (sessiondata.getRole().equals("Customer")) {
            return "redirect:/dashboard/riwayat";
        } else {
            return "redirect:/dashboard";
        }
    }

    @PostMapping("/dashboard/jasa/mulai_chat")
    public String tambahChatRoom(
            @RequestParam("id_pesanan") String idPesanan,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {
        Sessiondata sessiondata;
        sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if (sessiondata == null) {
            return "redirect:/login";
        }
        Optional<RiwayatPesanan> rOptional = riwayatPesananRepository.findById(Long.parseLong(idPesanan));
        if (rOptional.isPresent()) {
            RiwayatPesanan riwayatPesanan = rOptional.get();

            if (riwayatPesanan.getTempChatRoom() == null) {
                TempChatRoom tempChatRoom = new TempChatRoom(riwayatPesanan.getCustomer(),
                        riwayatPesanan.getPenyediaJasa());
                riwayatPesanan.setTempChatRoom(tempChatRoom);
                riwayatPesananRepository.save(riwayatPesanan);
            }
            return "redirect:/dashboard/jasa/chat/" + riwayatPesanan.getId();
        }

        if (sessiondata.getRole().equals("Customer")) {
            return "redirect:/dashboard/riwayat";
        } else {
            return "redirect:/dashboard";
        }
    }

}
