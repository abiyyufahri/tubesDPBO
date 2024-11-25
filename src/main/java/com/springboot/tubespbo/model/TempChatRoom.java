package com.springboot.tubespbo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tempChatRoom")
public class TempChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    private User userA;

    @NotNull
    @OneToOne
    private User userB;

    @OneToMany(mappedBy = "tempChatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chatMessages = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "tempChatRoom", fetch = FetchType.EAGER)
    private RiwayatPesanan pesanan;
    
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotNull
    private boolean status;

    public TempChatRoom(){

    }
    

    public TempChatRoom(@NotNull User userA, @NotNull User userB) {
        this.userA = userA;
        this.userB = userB;
        this.status = true;
    }



    public void addMessage(ChatMessage message) {
        this.chatMessages.add(message);
        message.setTempChatRoom(this);
    }

    public List<ChatMessage> getChatMessages() {
        return this.chatMessages;
    }


    public Long getId() {
        return id;
    }


    public User getUserA() {
        return userA;
    }


    public User getUserB() {
        return userB;
    }


    public RiwayatPesanan getPesanan() {
        return pesanan;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public boolean isStatus() {
        return status;
    }

    

}
