package com.springboot.tubespbo.model;

import com.springboot.tubespbo.auditable.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity

@Table(name = "temp_chat_rooms")

public class TempChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private User pengguna1;

    @NotNull
    @OneToOne
    private User pengguna2;

    @OneToOne
    private ChatMessage chatMessage;

    public void addMessage(ChatMessage message){
        setChatMessage(message);
    }

    public ChatMessage displayChatMessage(){
        return this.chatMessage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPengguna1(User pengguna1) {
        this.pengguna1 = pengguna1;
    }

    public void setPengguna2(User pengguna2) {
        this.pengguna2 = pengguna2;
    }

    public void setChatMessage(ChatMessage chatMessage) {
        this.chatMessage = chatMessage;
    }    

}
