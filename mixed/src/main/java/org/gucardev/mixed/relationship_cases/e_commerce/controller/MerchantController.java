package org.gucardev.mixed.relationship_cases.e_commerce.controller;

import com.gucardev.springlearning.relationship_cases.e_commerce.dto.MerchantDTO;
import com.gucardev.springlearning.relationship_cases.e_commerce.entitiy.Merchant;
import com.gucardev.springlearning.relationship_cases.e_commerce.mapper.MerchantMapper;
import com.gucardev.springlearning.relationship_cases.e_commerce.repo.MerchantRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/merchants")
public class MerchantController {
    private final MerchantRepository merchantService;
    private final MerchantMapper merchantMapper;

    public MerchantController(MerchantRepository merchantService, MerchantMapper merchantMapper) {
        this.merchantService = merchantService;
        this.merchantMapper = merchantMapper;
    }

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        List<Merchant> merchants = merchantService.findAll();
        List<MerchantDTO> merchantDTOs = merchants.stream()
                .map(merchantMapper::toDtoWithoutProducts)
                .collect(Collectors.toList());
        return ResponseEntity.ok(merchantDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(merchantService.findById(id)
                .map(merchantMapper::toDto).orElse(null));
    }
}
