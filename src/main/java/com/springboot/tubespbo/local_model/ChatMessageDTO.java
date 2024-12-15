package com.springboot.tubespbo.local_model;

import java.time.LocalDateTime;

import com.springboot.tubespbo.model.ChatMessage;

public class ChatMessageDTO {
    private Long id;
    private String isiPesan;
    private LocalDateTime createdAt;
    private PenggunaDTO pengguna;

    public ChatMessageDTO(ChatMessage chatMessage) {
        this.id = chatMessage.getId();
        this.isiPesan = chatMessage.getIsiPesan();
        this.createdAt = chatMessage.getCreatedAt();
        this.pengguna = new PenggunaDTO(chatMessage.getPengguna());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsiPesan() {
        return isiPesan;
    }

    public void setIsiPesan(String isiPesan) {
        this.isiPesan = isiPesan;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PenggunaDTO getPengguna() {
        return pengguna;
    }

    public void setPengguna(PenggunaDTO pengguna) {
        this.pengguna = pengguna;
    }

    

}


