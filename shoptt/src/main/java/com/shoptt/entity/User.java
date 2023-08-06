package com.shoptt.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;
@Entity
@Table(name = "user")
@Getter
@Setter
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String username;

    private String fullname;

    private String password;

    private String email;
    @NotNull(message = "Phone must not be null")
    private String phone;

    private Integer actived;

    private String activation_key;

    private String remember_key;

    private Timestamp created_date;

    private String avatar;

    private String tenDuong;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Village village;

    @JsonIgnoreProperties(value = {"user"})
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_name", referencedColumnName = "name") }
    )
    @BatchSize(size = 20)
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
