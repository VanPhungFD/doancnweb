package com.shoptt.dto;

import com.shoptt.entity.Authority;
import com.shoptt.entity.User;
import com.shoptt.entity.Village;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private String phone;

    private Integer actived;

    private String avatar;

    private Set<Authority> authorities;

    private Timestamp created_date;

    private Village village;

    private String fullname;

    private String tenDuong;

    public UserDto() {
    }

    public UserDto(String email, String phone, String fullname) {
        this.email = email;
        this.phone = phone;
        this.fullname = fullname;
    }

    public UserDto(User user){
        this.actived = user.getActived();
        this.authorities = user.getAuthorities();
        this.avatar = user.getAvatar();
        this.phone = user.getPhone();
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.created_date = user.getCreated_date();
        this.village = user.getVillage();
        this.fullname = user.getFullname();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getActived() {
        return actived;
    }

    public void setActived(Integer actived) {
        this.actived = actived;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTenDuong() {
        return tenDuong;
    }

    public void setTenDuong(String tenDuong) {
        this.tenDuong = tenDuong;
    }
}

