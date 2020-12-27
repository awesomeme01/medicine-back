package com.example.medicine.service;

import com.example.medicine.helper.AlreadyPaidException;
//import com.example.medicine.helper.Payable;
import com.example.medicine.model.Meeting;
import com.example.medicine.model.Payment;
import com.example.medicine.model.User;
import com.example.medicine.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    MeetingsService meetingsService;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getById(Long id) {
        return paymentRepository.findById(id).get();
    }

    @Override
    public List<Payment> getByParent(Meeting payable) {
        return paymentRepository.findAll().stream().filter(x->x.getParent().equals(payable)).collect(Collectors.toList());
    }

    @Override
    public List<Payment> getByCashier(User user) {
        return paymentRepository.findAll().stream().filter(x->x.getCashier().equals(user)).collect(Collectors.toList());
    }

    @Override
    public Payment create(Payment payment) throws AlreadyPaidException{
        if(payment.getParent().getStatusPaid()==0){
            paymentRepository.save(payment);
            List<Payment> previousPayments = getByParent(payment.getParent());
            Double sum = (double)0;
            for(Payment p: previousPayments){
                sum += p.getAmountPaid();
            }
            if(sum >= (payment.getParent().getAmountToBePaid())){
                payment.getParent().setStatusPaid(1);
                meetingsService.create(payment.getParent());
            }
        }
        else{
            throw new AlreadyPaidException();
        }
        return payment;
    }

    @Override
    public Payment update(Payment payment) throws AlreadyPaidException{
        deletePayment(payment.getId());
        create(payment);
        return payment;
    }

    @Override
    public List<Payment> getFrom(LocalDateTime date) {
        return paymentRepository.findAll().stream().filter(x->x.getDateCreated().isAfter(date)).collect(Collectors.toList());
    }

    @Override
    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id).get();
        List<Payment> previousPayments = getByParent(payment.getParent());
        Double sum = (double)0;
        for(Payment payment1: previousPayments){
            sum+= payment1.getAmountPaid();
        }
        if(sum-payment.getAmountPaid() < payment.getParent().getAmountToBePaid()){
            payment.getParent().setStatusPaid(0);
            meetingsService.create((Meeting)payment.getParent());
        }
        paymentRepository.deleteById(id);
    }
}
