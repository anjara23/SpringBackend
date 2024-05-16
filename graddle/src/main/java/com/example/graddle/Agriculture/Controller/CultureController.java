package com.example.graddle.Agriculture.Controller;

import com.example.graddle.Agriculture.Payload.CultureRequest;
import com.example.graddle.Agriculture.Services.CultureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agriculture/culture")
@RequiredArgsConstructor
public class CultureController {

    private final CultureService cultureService;

    @PostMapping("/addCulture")
    public void addCulture(@RequestBody CultureRequest cultureRequest){
        cultureService.addCulture(cultureRequest);
    }

    @PutMapping("/doRecolte/{id_cultiver}")
    public void doRecolte(@PathVariable Integer id_cultiver, @RequestBody CultureRequest cultureRequest){
        cultureService.doRecolte(id_cultiver, cultureRequest);
    }

    @GetMapping("/getAllC")
    public List<Object[]> getAllC(){
        return cultureService.getAllC();
    }

    @GetMapping("/getByVar/{variete}")
    public List<Object[]> getByVariete(@PathVariable  String variete){
        return cultureService.getByVariete(variete);
    }

    @GetMapping("/getFinished")
    public List<Object[]> getFinished(){
        return cultureService.getFinished();
    }

    @GetMapping("/getByMonth/{mois}")
    public List<Object[]> getByMonth(@PathVariable Integer mois){
        return cultureService.getByMonth(mois);
    }

    @DeleteMapping("/delete/{id_cultiver}")
    public void deleteCulture(@PathVariable Integer id_cultiver){
        cultureService.deleteCulture(id_cultiver);
    }
}
