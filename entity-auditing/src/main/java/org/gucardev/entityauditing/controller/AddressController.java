package org.gucardev.entityauditing.controller;

import org.gucardev.entityauditing.dto.AddressDTO;
import org.gucardev.entityauditing.dto.AddressHistoryDTO;
import org.gucardev.entityauditing.dto.request.AddressRequest;
import org.gucardev.entityauditing.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDTO> create(@RequestBody AddressRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAll() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getById(id));
    }

    @PutMapping
    public ResponseEntity<AddressDTO> update(@RequestBody AddressRequest request) {
        return ResponseEntity.ok(addressService.update(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/revision/{id}")
    public ResponseEntity<List<AddressHistoryDTO>> getUserRevisions(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressHistory(id));
    }
}
