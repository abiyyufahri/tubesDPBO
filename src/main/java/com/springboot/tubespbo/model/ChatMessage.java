package com.springboot.tubespbo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "chatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @OneToOne
    private User pengguna;

    @NotBlank
    private String isiPesan;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private TempChatRoom tempChatRoom;
    
    public ChatMessage(@NotNull User pengguna, @NotBlank String isiPesan, TempChatRoom tempChatRoom) {
        this.pengguna = pengguna;
        this.isiPesan = isiPesan;
        this.tempChatRoom = tempChatRoom;
    }

    public ChatMessage() {
    }

    public Long getId() {
        return id;
    }

    public User getPengguna() {
        return pengguna;
    }

    public String getIsiPesan() {
        return isiPesan;
    }

    public TempChatRoom getTempChatRoom() {
        return tempChatRoom;
    }

    public void setTempChatRoom(TempChatRoom tempChatRoom) {
        this.tempChatRoom = tempChatRoom;
    }
}
