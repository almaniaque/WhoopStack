package com.whoopstack.devis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.whoopstack.devis.model.Devis;
import com.whoopstack.devis.service.DevisService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/devis")
public class DevisController {

    private final DevisService service;

    public DevisController(DevisService service) {
        this.service = service;
    }

    @GetMapping("/users/{userId}/devis")
    public ResponseEntity<List<Devis>> getAllDevis(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getAllDevis(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devis> getDevisById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getDevisById(id));
    }

    @PostMapping("/users/{userId}/clients/{clientId}/devis")
    public ResponseEntity<Devis> addDevis(
            @PathVariable Long userId, // ajout des lien pour client/freelance
            @PathVariable Long clientId, // ajout des lien pour client/freelance
            @RequestBody Devis devis) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addDevis(userId, clientId, devis));
    } // ^ ^ ajout des dep user et client

    @PutMapping("/{id}")
    public ResponseEntity<Devis> updateDevisById(@PathVariable Long id, @RequestBody Devis devis) {
        return ResponseEntity.ok(service.updateDevisById(id, devis));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        service.deleteDevis(id);
        return ResponseEntity.noContent().build();
    }
}