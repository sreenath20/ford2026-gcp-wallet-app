package com.datajpa.demo1.jpa_mappings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRespository
        extends JpaRepository<UserAccount,Integer> {
}
