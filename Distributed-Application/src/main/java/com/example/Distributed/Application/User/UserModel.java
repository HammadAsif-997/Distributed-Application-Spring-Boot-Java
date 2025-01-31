package com.example.Distributed.Application.User;

/**
 * Represents a user entity with properties such as ID, username, email, role, and active status.
 * Provides constructors, getters, setters, and a toString method for ease of use.
 */
public class UserModel {

    // Unique identifier for the user
    private Long id;

    // Username of the user
    private String username;

    // Email address of the user
    private String email;

    // Role assigned to the user (e.g., admin, user)
    private String role;

    // Indicates whether the user is active or not
    private Boolean active;

    /**
     * Default constructor for creating an empty user instance.
     */
    public UserModel() {}

    /**
     * Parameterized constructor to initialize a user with specified attributes.
     *
     * @param id       Unique identifier for the user.
     * @param username The username of the user.
     * @param email    The email address of the user.
     * @param role     The role assigned to the user.
     * @param active   The active status of the user.
     */
    public UserModel(Long id, String username, String email, String role, Boolean active) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    /**
     * Retrieves the user's ID.
     *
     * @return Long representing the user's ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id The unique identifier to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the user's username.
     *
     * @return String representing the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the user's email address.
     *
     * @return String representing the email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the user's role.
     *
     * @return String representing the role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retrieves the user's active status.
     *
     * @return Boolean indicating whether the user is active.
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Sets the user's active status.
     *
     * @param active The active status to set.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * Returns a string representation of the user, including all attributes.
     *
     * @return A string with the user's details.
     */
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
