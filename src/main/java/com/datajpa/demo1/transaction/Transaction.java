package com.datajpa.demo1.transaction;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Double amount;
    private LocalDate date;
    private String UPIDetails;
//    @Enumerated(EnumType.STRING)
//    private TransactionType type;
}
