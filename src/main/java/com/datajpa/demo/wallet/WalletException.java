package com.datajpa.demo.wallet;
// Custom or user defined exception
public class WalletException extends RuntimeException { // unchecked by compiler
    public WalletException(String message) {
        super(message); // custom message
    }
}
