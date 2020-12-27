package com.example.medicine.service;

import com.example.medicine.helper.AlreadyPaidException;
//import com.example.medicine.helper.Payable;
import com.example.medicine.model.Meeting;
import com.example.medicine.model.Payment;
import com.example.medicine.model.User;
import com.example.medicine.model.UserRole;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentService {
    List<Payment> getAll();
    Payment getById(Long id);
    List<Payment> getByParent(Meeting payable);
    List<Payment> getByCashier(User user);
    Payment create(Payment payment) throws AlreadyPaidException;
//    List<Payment> getByStatusPaid(int status);
    Payment update(Payment payment) throws AlreadyPaidException;
    void deletePayment(Long id);

    List<Payment> getFrom(LocalDateTime date);

}
