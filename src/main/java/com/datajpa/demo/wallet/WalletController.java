package com.datajpa.demo.wallet;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/wallets")
@CrossOrigin// allows public origins
public class WalletController {

    @GetMapping
    public String info() {
        return "Wallet App Running.";
    }

    @Autowired
    private WalletService walletService;

    // Register new Wallet User // REST API
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Wallet resigterNewWallet(@RequestBody Wallet newWallet) {
        return this.walletService.registerNewWalletUser(newWallet);// ctrl+alt + click
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable("id") Integer walletId) {
        return this.walletService.getUserWalletById(walletId);
    }

    // PUT // Patch // POST
    @PatchMapping
    public Double addFundsToWalletById(@RequestBody WalletDto walletDto) {
        try {
            return this.walletService
                    .addFundsToWalletById(walletDto.getToId(), walletDto.getAmount());

        } catch (WalletException e) {

            throw e;
        }
    }

    // Withdraw funds
    @PatchMapping("/withdraw")
    public Double withdrawFundsFromWalletById(@RequestBody WalletDto walletDto) {
        return this.walletService
                .withdrawFundsFromWalletById(walletDto.getFromId(), walletDto.getAmount());
    }

    @PatchMapping("/transfer")
    public Boolean transferFunds(@RequestBody WalletDto walletDto) {
        return this.walletService.fundTransfer(walletDto.getFromId(), walletDto.getToId(), walletDto.getAmount());
    }
}
