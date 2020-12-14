package com.example.medicine.controller;

import com.example.medicine.helper.Response;
import com.example.medicine.model.Division;
import com.example.medicine.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/division")
public class DivisionController {
    @Autowired
    DivisionService divisionService;
    //    List<Division> getAll();
    //    Division create(Division division);
    //    void deleteById(Long id);

    @GetMapping("/getAll")
    public Response getAll(){
        return new Response(true, "All divisions", divisionService.getAll());
    }
    @PostMapping("/create")
    public Response create(@RequestBody Division division){
        return new Response(true, "Division created", divisionService.create(division));
    }
    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable Long id){
        divisionService.deleteById(id);
        return new Response(true, "Deleted division object",null);
    }

}
