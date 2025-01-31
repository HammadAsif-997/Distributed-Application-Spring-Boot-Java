package com.example.Distributed.Application.User;

import com.example.Distributed.Application.Order.Order;
import com.example.Distributed.Application.Order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service class to handle operations related to users,
 * including CRUD operations backed by a JSON file as the data source.
 */
@Service
public class UserService {


    private final OrderService orderService;

    private static final String FILE_PATH = "src\\main\\java\\com\\example\\Distributed\\Application\\data\\users.json"; // File path for user data
    private final ObjectMapper objectMapper; // Used for JSON serialization and deserialization

    /**
     * Constructor initializes the ObjectMapper for handling JSON operations.
     */
    public UserService(OrderService orderService) {
        this.orderService = orderService;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Reads the list of users from the JSON file.
     *
     * @return List of UserModel objects.
     * @throws IOException If there is an issue accessing or reading the file.
     */
    private List<UserModel> readUsersFromFile() throws IOException {
        File file = new File(FILE_PATH); // Load the file from the specified path

        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

        // Deserialize JSON file content into a list of UserModel objects
        return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, UserModel.class));
    }

    /**
     * Writes the list of users to the JSON file.
     *
     * @param users List of UserModel objects to write to the file.
     * @throws IOException If there is an issue writing to the file.
     */
    private void writeUsersToFile(List<UserModel> users) throws IOException {
        File file = new File(FILE_PATH); // Use the specified file path
        objectMapper.writeValue(file, users); // Serialize and save the user list to the file
    }

    /**
     * Retrieves all users from the file.
     *
     * @return List of all users, or an empty list if an error occurs.
     */
    public List<UserModel> getAllUsers() {
        try {
            return readUsersFromFile();
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
            return Collections.emptyList(); // Return an empty list if an error occurs
        }
    }

    /**
     * Fetches a user by their unique ID.
     *
     * @param id The unique ID of the user.
     * @return The UserModel object if found, otherwise null.
     */
    public UserModel getUserById(Long id) {
        List<UserModel> users = getAllUsers();
        Optional<UserModel> user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst(); // Find the user with the matching ID
        return user.orElse(null); // Return the user or null if not found
    }

    /**
     * Saves a new user to the file.
     *
     * @param user The user to save.
     * @return The saved UserModel object with a generated ID, or null if an error occurs.
     */
    public UserModel saveUser(UserModel user) {
        try {
            List<UserModel> users = getAllUsers();
            long newId = users.size() + 1L; // Generate a new ID based on the list size
            user.setId(newId);
            users.add(user); // Add the new user to the list
            writeUsersToFile(users); // Save the updated list to the file
            return user;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates an existing user's details by their ID.
     *
     * @param id           The ID of the user to update.
     * @param updatedUser  The updated user details.
     * @return The updated UserModel object, or null if the user does not exist or an error occurs.
     */
    public UserModel updateUser(Long id, UserModel updatedUser) {
        try {
            List<UserModel> users = getAllUsers();
            Optional<UserModel> existingUserOpt = users.stream()
                    .filter(user -> user.getId().equals(id)) // Find the user by ID
                    .findFirst();

            if (existingUserOpt.isPresent()) {
                UserModel existingUser = existingUserOpt.get();
                existingUser.setUsername(updatedUser.getUsername());
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setRole(updatedUser.getRole());
                existingUser.setActive(updatedUser.getActive()); // Update user fields
                writeUsersToFile(users); // Save the updated list to the file
                return existingUser;
            }
            return null; // Return null if the user does not exist
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return True if the user was deleted successfully, false otherwise.
     */
    public boolean deleteUser(Long id) {
        try {
            List<UserModel> users = getAllUsers();
            boolean removed = users.removeIf(user -> user.getId().equals(id)); // Remove user by ID

            if (removed) {
                writeUsersToFile(users); // Save the updated list to the file
            }
            return removed; // Return true if the user was removed, false otherwise
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public String getUserId() {
        return "1";
    }

    public Order getRecentOrderForUser() {
        String userId = getUserId();
        return orderService.getRecentOrderForUser(userId);
    }

    
}
