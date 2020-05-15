package com.enosh.userlogin.dao;

import com.enosh.userlogin.exceptions.DoesntExistsException;
import com.enosh.userlogin.model.Address;
import com.enosh.userlogin.model.User;
import com.enosh.userlogin.reposirtory.AddressRepository;
import com.enosh.userlogin.reposirtory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.commons.codec.digest.DigestUtils.*;

@Component
public class UserDao {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public UserDao(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public User save(User user, Address address){
        Address afterSave = addressRepository.save(address);
        user.setAddress(afterSave);
        user.setPassword(md5Hex(user.getPassword()).toUpperCase());
        return userRepository.save(user);
    }

    public boolean login(String email, String password) {
        return userRepository.existsByEmailAndPassword(
                email, md5Hex(password).toUpperCase()
        );
    }

    public User findByEmailAndPassword(String email, String password) throws DoesntExistsException {
        return userRepository.findByEmailAndPassword(email, md5Hex(password).toUpperCase())
                .orElseThrow(() -> new DoesntExistsException(
                        "User by the email: " + email + "\n" +
                        "And password: " + password + "\n" +
                        "Does not exists"));
    }


}
