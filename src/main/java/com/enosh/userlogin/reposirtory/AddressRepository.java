package com.enosh.userlogin.reposirtory;

import com.enosh.userlogin.model.Address;
import com.enosh.userlogin.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
}
