package com.springboot.tubespbo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ChatMessage")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @OneToOne
    private User pengguna;

    @NotBlank
    private String isiPesan;

    @ManyToOne
    private TempChatRoom tempChatRoom; 

    
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
