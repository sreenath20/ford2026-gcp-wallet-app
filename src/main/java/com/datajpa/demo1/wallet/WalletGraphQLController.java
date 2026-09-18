package com.datajpa.demo1.wallet;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WalletGraphQLController {

    private final WalletService walletService;

    public WalletGraphQLController(
            WalletService walletService) {

        this.walletService = walletService;
    }

    @QueryMapping
    public Wallet wallet(@Argument Integer id) {
        return walletService.getUserWalletById(id);
    }

    @QueryMapping
    public List<Wallet> wallets() {

        return walletService.getAllWallets();
    }

    @MutationMapping
    public Wallet createWallet(
            @Argument String ownerName) {

//        return walletService.createWallet(ownerName);
        return null;
    }

    @MutationMapping
    public Wallet deposit(
            @Argument Long walletId,
            @Argument Double amount) {

//        return walletService.addFundsToWalletById(
//                walletId,
//                BigDecimal.valueOf(amount)
//        );
        return null;
    }

    @MutationMapping
    public Wallet withdraw(
            @Argument Long walletId,
            @Argument Double amount) {
//
//        return walletService.withdraw(
//                walletId,
//                BigDecimal.valueOf(amount)
//        );
        return null;
    }

    @MutationMapping
    public String transfer(
            @Argument Long fromWalletId,
            @Argument Long toWalletId,
            @Argument Double amount) {
//
//        return walletService.transfer(
//                fromWalletId,
//                toWalletId,
//                BigDecimal.valueOf(amount)
//        );
        return null;
    }
}