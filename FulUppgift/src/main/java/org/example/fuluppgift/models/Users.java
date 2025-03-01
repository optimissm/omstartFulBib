package org.example.fuluppgift.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AppUsers")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public Users(){
        created = new Date();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    // så att vi har bättre läsbarhet över våra användare
    @Override
    public String toString() {
        return "Users [" +
                "id=" + id +
                ", name=" + name +
                ", username=" + username +
                ", created=" + created +
                "]";
    }

}
