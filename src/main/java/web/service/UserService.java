package web.service;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import web.model.User;


import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserService {
    private List<User> users = Arrays.asList(
            new User ( "name", "surname"));
}
