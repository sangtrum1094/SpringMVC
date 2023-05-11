package com.sang.minishops.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The type Role.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;


    @ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
    private Set<User> users = new LinkedHashSet<>();
    /**
     * Gets users.
     *
     * @return the users
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }
}