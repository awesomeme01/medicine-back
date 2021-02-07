package com.example.medicine.controller;

import com.example.medicine.helper.Response;
import com.example.medicine.model.Diagnose;
import com.example.medicine.repository.DiagnoseRepository;
import com.example.medicine.service.DiagnoseService;
import com.example.medicine.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/diagnose/receipt")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;
    @Autowired
    DiagnoseRepository diagnoseRepository;
    @GetMapping("/getByDiagnose/{id}")
    public Response getByDiagnose(@PathVariable Long diagnoseId){
        Diagnose diagnose = diagnoseRepository.findById(diagnoseId).get();
        return new Response(true, "","",receiptService.getAllByDiagnose(diagnose));
    }
}
