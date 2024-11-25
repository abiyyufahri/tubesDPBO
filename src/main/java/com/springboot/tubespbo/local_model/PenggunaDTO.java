package com.springboot.tubespbo.local_model;

import com.springboot.tubespbo.model.User;

public class PenggunaDTO {
    private Long id;
    private String username;

    public PenggunaDTO(User pengguna) {
        this.id = pengguna.getId();
        this.username = pengguna.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
