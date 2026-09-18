package com.datajpa.demo.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    //    SimpleJpaRepository
    // Custom JPQL query by method name
    // SELECT wallet FROM Wallet wallet WHERE wallet.email = ?1
    Optional<Wallet> findByEmail(String email);

    @Query("SELECT wallet FROM Wallet wallet WHERE wallet.email = ?1")
    Wallet searchForWalletByEmail(String email);

    @Query(value = "SELECT * FROM wallet WHERE email = ?1", nativeQuery = true)
    Wallet searchForWalletByEmailNative(String email);

    @Query("SELECT wallet FROM Wallet wallet ")
    List<Wallet> getAllWallets();
}
