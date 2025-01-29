package com.cantact_list.com.cantact_list.repository;

import com.cantact_list.com.cantact_list.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ContactRepo extends JpaRepository<Contact,String> {

}
