package com.example.graddle.Agriculture.Controller;


import com.example.graddle.Agriculture.Payload.Stade_pRequest;
import com.example.graddle.Agriculture.Services.Stade_pService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agriculture/stade_p")
@RequiredArgsConstructor
public class Stade_pController {

    private final Stade_pService stade_pService;

    @PostMapping("/addStade")
    public void addStade(@RequestBody Stade_pRequest stade_pRequest){
        stade_pService.addStade(stade_pRequest);
    }

    @PutMapping("/updateS/{id_stade}")
    public void updateStade(@PathVariable Integer id_stade, @RequestBody Stade_pRequest stade_pRequest){
        stade_pService.updateStade(id_stade,stade_pRequest);
    }

    @GetMapping("/diagSt/{id_cultiver}")
    public List<Object[]> diagSt(@PathVariable Integer id_cultiver){
        return stade_pService.diagSt(id_cultiver);
    }
}
