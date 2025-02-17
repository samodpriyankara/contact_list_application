package com.cantact_list.com.cantact_list.repository;

import com.cantact_list.com.cantact_list.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Junior RT
 * @version 1.0
 * @license Get Arrays, LLC (<a href="https://www.getarrays.io">Get Arrays, LLC</a>)
 * @email getarrayz@gmail.com
 * @since 11/22/2023
 */

@Repository
@EnableJpaRepositories
public interface ContactRepo extends JpaRepository<Contact, String> {
    Optional<Contact> findById(String id);
}