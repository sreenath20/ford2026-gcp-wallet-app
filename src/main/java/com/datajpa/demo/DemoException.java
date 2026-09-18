package com.datajpa.demo;

public class DemoException {
    public static void main(String[] args) {
        //int data = 100 / 0;
        String name = null;
        try {
            System.out.println(name.toUpperCase());
        }
        catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        System.out.println("End of applction");
    }
}
