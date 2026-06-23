package com.whoopstack.devis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.whoopstack.devis.model.Devis;
import com.whoopstack.devis.service.DevisService;

@RestController
@RequestMapping("/api/devis")
@CrossOrigin(origins = "http://localhost:8080")
public class DevisController {
    private final DevisService devisService;

    public DevisController(DevisService devisService) {
        this.devisService = devisService;
    }

    @GetMapping
    public List<Devis> getAllDevis() {
        return devisService.getAllDevis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devis> getDevisById(@PathVariable Long id) {
        Optional<Devis> devis = devisService.getDevisById(id);

        if (devis.isPresent()) {
            return ResponseEntity.ok(devis.get()); // 200 + le devis
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }

    @PostMapping
    public Devis createDevis(@RequestBody Devis devis) {
        return devisService.createDevis(devis);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devis> updateDevis(@PathVariable Long id, @RequestBody Devis devis) {
        return devisService.updateDevis(id, devis)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevis(@PathVariable Long id) {
        boolean supprime = devisService.deleteDevis(id);
        return supprime ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}