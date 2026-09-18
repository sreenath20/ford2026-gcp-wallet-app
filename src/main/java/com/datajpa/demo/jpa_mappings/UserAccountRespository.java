package com.datajpa.demo.jpa_mappings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRespository
        extends JpaRepository<UserAccount,Integer> {
}
