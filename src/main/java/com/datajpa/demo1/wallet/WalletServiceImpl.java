package com.datajpa.demo1.wallet;

import com.datajpa.demo1.transaction.Transaction;
import com.datajpa.demo1.transaction.TransactionRepository;
import com.datajpa.demo1.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional // class level
public class WalletServiceImpl implements WalletService { // IS-A
    private WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public WalletServiceImpl(TransactionRepository transactionRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet registerNewWalletUser(Wallet newWallet) {
        // check if email already exists
        if (this.walletRepository.findByEmail(newWallet.getEmail()).isPresent()) {
            throw new WalletException("Account with given Email already exists.");
        }
        newWallet.setCreatedOnDate(LocalDate.now());
        newWallet.setActive(true);
        return this.walletRepository.save(newWallet);
    }

    @Override
    public Wallet getUserWalletById(Integer walletId) {

        Optional<Wallet> foundWalletOpt = this.walletRepository.findById(walletId);

        if (foundWalletOpt.isPresent())
            return foundWalletOpt.get();
        // throw exception
        return null;
    }

    @Override
    public Wallet updateUserWallet(Wallet updateWallet) {
        return null;
    }

    @Override
    public Double addFundsToWalletById(Integer id, Double newBalance) {
        Wallet foundWallet = this.walletRepository.findById(id)
                .orElseThrow(() -> new WalletException("Wallet id does not exists."));// need to handle exception
        Double oldBalance = foundWallet.getBalance();
        foundWallet.setBalance(oldBalance + newBalance);
        this.walletRepository.save(foundWallet);// option 1 to modify DB
        return foundWallet.getBalance();
    }

    @Override
    @Transactional // option 2
    public Double withdrawFundsFromWalletById(Integer id, Double amount) throws WalletException {
        Wallet foundWallet = this.walletRepository.findById(id).orElseThrow(() -> new WalletException("Wallet not found for id :" + id));
        // if  balance < amount throw exception
        if (foundWallet.getBalance() < amount) {
            throw new WalletException("Insufficient balance, available balance :" + foundWallet.getBalance());
        }
        Double currentBalance = foundWallet.getBalance();
        foundWallet.setBalance(currentBalance - amount);
        return foundWallet.getBalance();
    }

    @Override
    @Transactional
    public Boolean fundTransfer(Integer fromId, Integer toId, Double amount) {
        Wallet fromWallet = this.walletRepository.findById(fromId).orElseThrow(() -> new WalletException("From account not found."));
        Wallet toWallet = this.walletRepository.findById(toId).orElseThrow(() -> new WalletException("To account not found."));
        if (fromWallet.getBalance() < amount) throw new WalletException("Insufficient balance in your account.");
        Double fromBalance = fromWallet.getBalance();
        fromWallet.setBalance(fromBalance - amount);
// Using setter
        Transaction debitTransaction = new Transaction(); // Transient
        debitTransaction.setDate(LocalDate.now());
        debitTransaction.setAmount(amount);
//        debitTransaction.setType(TransactionType.DEBIT);
        debitTransaction = this.transactionRepository.save(debitTransaction);
        fromWallet.getTransactions().add(debitTransaction);

        Double toBalance = toWallet.getBalance();
        toWallet.setBalance(toBalance + amount);
        // Builder pattern
        Transaction crediTransaction = Transaction.builder().date(LocalDate.now())
                .amount(amount)
//                .type(TransactionType.CREDIT)
                .build();
        crediTransaction = this.transactionRepository.save(crediTransaction);
        toWallet.getTransactions().add(crediTransaction);
        return true;
    }

    @Override
    public Boolean deactivateWalletById(Integer id) {
        return null;
    }

    @Override
    public Boolean activateWalletById(Integer id) {
        return null;
    }

    @Override
    public List<Wallet> getAllWallets() {
        return this.walletRepository.findAll();
    }
}
