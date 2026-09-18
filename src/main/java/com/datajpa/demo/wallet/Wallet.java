package com.datajpa.demo.wallet;

import com.datajpa.demo.transaction.Transaction;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wallet {
    @Id
    @GeneratedValue()
    private Integer id;
    @Email(message = "Email must have user@gmail.com format")
    private String email;
    @NotNull(message = "Name can't be null")
//    @NotBlank
    @Size(min = 3, max = 30, message = "Name must be min 3 and max 30 chars.")
    private String name;
    @NotNull(message = "Password can't be null")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have min 1 digit, lower case, Upper case, special chars and min 8 chars.")
    private String password;
    @NotNull(message = "Balance can't be null")
    @Min(value = 500, message = "Minimum opening balance must be 500 Rs")
    private Double balance;
    private LocalDate createdOnDate;
    private Boolean isActive;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Transaction> transactions = new ArrayList<>();

    public Wallet() {
//        this.isActive = true;
    }

    public Wallet(Integer id, String email, String name, String password, Double balance, LocalDate createdOnDate, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.balance = balance;
        this.createdOnDate = createdOnDate;
//        this.isActive = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getCreatedOnDate() {
        return createdOnDate;
    }

    public void setCreatedOnDate(LocalDate createdOnDate) {
        this.createdOnDate = createdOnDate;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
