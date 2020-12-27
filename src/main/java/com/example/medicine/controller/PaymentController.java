package com.example.medicine.controller;

import com.example.medicine.helper.AlreadyPaidException;
import com.example.medicine.helper.Response;
import com.example.medicine.model.Meeting;
import com.example.medicine.model.Payment;
import com.example.medicine.service.MeetingsService;
import com.example.medicine.service.PaymentService;
import com.example.medicine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@Secured({"ROLE_CASHIER", "ROLE_ADMIN"})
@CrossOrigin("*")
@RequestMapping("/api/payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    UserService userService;
    @Autowired
    MeetingsService meetingsService;
    //    List<Payment> getAll();
    //    Payment getById(Long id);
    //    List<Payment> getByParent(Payable payable);
    //    List<Payment> getByCashier(User user);
    //    Payment create(Payment payment) throws AlreadyPaidException;
    //    Payment update(Payment payment);
    //    void deletePayment(Long id);
    @Secured({"ROLE_CASHIER", "ROLE_ADMIN"})
    @GetMapping("/getAll")
    public Response getAll(){
        try{
            return new Response(true,"Все объекты платежей в базе","All payments in database", paymentService.getAll());
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured({"ROLE_CASHIER", "ROLE_ADMIN"})
    @GetMapping("/getFrom")
    public Response getFrom(@RequestBody LocalDateTime dateFrom){
        try{
            return new Response(true, "Все объекты платежей в базе c данной даты " + dateFrom, "All payments in database from " + dateFrom, paymentService.getFrom(dateFrom));
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured({"ROLE_CASHIER", "ROLE_ADMIN"})
    @GetMapping("/getById/{id}")
    public Response getById(@PathVariable Long id){
        try{
            return new Response(true, "Объект платежа с ID номером " + id, "Payment object with id = " + id, paymentService.getById(id));
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/getByCashier/{id}")
    public Response getByCashier(@PathVariable Long id){
        try{
            return new Response(true, "Платежи кассира с ID номером " + id, "Payments of cashier with id = " + id, paymentService.getByCashier(userService.getUserById(id)));
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }

    @Secured({"ROLE_CASHIER", "ROLE_ADMIN"})
    @PostMapping("/makeForMeeting/{id}")
    public Response makeForMeeting(@PathVariable Long id, @RequestBody Payment payment, Principal principal){
        try {
            Meeting meeting = meetingsService.getById(id);
            if(meeting == null){
                return new Response(false, "Приема с ID номером " + id + " не существует!",  "No meeting with id = " + id + " exists!", null,null);
            }
            payment.setCashier(userService.getByUsername(principal.getName()));
            return new Response(true, "Сделан платеж на прием с ID номером " + id, "Payment for meeting with ID " + id + " was saved!", paymentService.create(payment));
        }
        catch (AlreadyPaidException ex){
            return new Response(false, "Встреча с этим ID уже полностью оплачена", "Payment for the meeting with ID " + id + " was already made! \nERROR: " + ex.getMessage(), null, null);
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/updatePayment")
    public Response updatePayment(@RequestBody Payment payment, Principal principal){
        try{
            return new Response(true, "Обновлен платеж на прием. ID номер платежа " + payment.getId(), paymentService.update(payment));
        }catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
    @Secured("ROLE_ADMIN")
    @PostMapping("/delete/{id}")
    public Response delete(@PathVariable Long id){
        try{
            paymentService.deletePayment(id);
            return new Response(true, "Платеж с ID номером " + id + " был удален!", "Payment with id = " + id + " was deleted!", null,null );
        }
        catch (Exception ex){
            return new Response(false, "Непредвиденная ошибка на сервере","Unexpected Error: " + ex.getMessage(), ex.getStackTrace());
        }
    }
}
