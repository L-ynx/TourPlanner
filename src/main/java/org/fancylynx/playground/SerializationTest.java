package org.fancylynx.playground;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerializationTest {
    public static void run() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UserList userList = objectMapper.readValue(new File("src/main/java/org/fancylynx/playground/ok123.json"), UserList.class);
            for (User user : userList.getUsers()) {
                System.out.println(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UserList {
    private List<User> users;

    public UserList() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

