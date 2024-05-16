package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.Payload.CalendrierRequest;
import com.example.graddle.Agriculture.Services.CalendrierService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agriculture/calendrier")
@RequiredArgsConstructor
public class CalendrierController {

    private final CalendrierService calendrierService;

    @PostMapping("/addCalendrier")
    public void addCalendrier(@RequestBody CalendrierRequest calendrierRequest){
        calendrierService.addCalendrier(calendrierRequest);
    }

    @PutMapping("/updateCal/{id_calendrier}")
    public void updateCalendrier(@PathVariable Integer id_calendrier, @RequestBody CalendrierRequest calendrierRequest){
        calendrierService.updateCalendrier(id_calendrier, calendrierRequest);
    }

    @GetMapping("/getByMonth/{mois}")
    public List<Object[]> getByMonth(@PathVariable Integer mois){
        return calendrierService.getByMonth(mois);
    }

    @GetMapping("/notifDeb")
    public String notifDeb(){ return calendrierService.notifDeb(); }

    @GetMapping("/notifFin")
    public String notifFin(){ return calendrierService.notifFin(); }
}
