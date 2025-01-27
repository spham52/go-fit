package com.gofit.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gofit.annotation.UniqueColumn;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int id;

    @NotNull
    @NotBlank(message = "You must enter a displayed username.")
    @Column(name="display_username")
    @Size(min=6, max=32, message="Enter a username longer than 6 characters but less than 32.")
    @UniqueColumn(field = "displayName", message = "Display name must be unique.")
    private String displayName;

    @NotNull
    @NotBlank(message = "You must enter a username.")
    @Size(min=6, max=32, message="Enter a username longer than 6 characters but less than 32.")
    @UniqueColumn(field = "username", message = "Username must be unique.")
    @Column(name="username")
    private String username;

    @Column(name="password")
    @NotBlank
    @NotNull
    private String password;

    @Transient
    @JsonProperty("plainPassword")
    @NotBlank(message = "You must enter a password.")
    @NotNull
    @Size(min = 8, max = 20, message = "Please enter a password greater than 8 characters but less than 20.")
    private String plainPassword;

    // list of sessions created by a user
    // each session represents multiple workouts or exercises done in a session
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Session> sessions;

    // list of activites created by a user
    // these are custom activities created by the user
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Activities> activities;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Roles> roles = new ArrayList<>();

    public User() {
        roles.add(new Roles("ROLE_USER"));
    };

    public User(String displayName, String username, String plainPassword) {
        this.displayName = displayName;
        this.username = username;
        this.plainPassword = plainPassword;
        roles.add(new Roles("ROLE_USER"));
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String display_name) {
        this.displayName = display_name;
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

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Activities> getActivities() {
        return activities;
    }

    public void setActivities(List<Activities> activities) {
        this.activities = activities;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", display_name='" + displayName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
