package com.example.graddle.Fermier;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fermier/auth")
@RequiredArgsConstructor
public class FermierController {

    private final FermierService fermierService;

    @PostMapping("/inscription")
    public void inscrireF (@RequestBody FermierRequest fermierRequest){ fermierService.inscrireF(fermierRequest); }

    @PostMapping("/authentification")
    public ResponseEntity<String> authentificationF(@RequestBody AuthRequest authRequest){ return fermierService.authentificationF(authRequest);}
}
