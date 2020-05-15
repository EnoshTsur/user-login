package com.enosh.userlogin.reposirtory;

import com.enosh.userlogin.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    boolean existsByEmailAndPassword(String email, String password);

    Optional<User> findByEmailAndPassword(String email, String password);
}
