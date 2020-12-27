package com.example.medicine.helper;

public class AlreadyPaidException extends Exception{
    public AlreadyPaidException() {
        super("This Payable was already paid fully!");
    }
}
