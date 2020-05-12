package com.enosh.userlogin.bootstrap;

import com.enosh.userlogin.dao.UserDao;
import com.enosh.userlogin.model.Address;
import com.enosh.userlogin.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserDao userDao;

    public DataLoader(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address("Tel Aviv", "Bugrashov", 58);
        User user = new User(
                "Liat",
                "Efrati",
                "liat@gmail.com",
                "123456"
        );
        userDao.save(user, address);
    }
}
