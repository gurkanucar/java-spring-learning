package org.gucardev.springboottest.controller;

import jakarta.validation.Valid;
import org.gucardev.springboottest.dto.AddressDTO;
import org.gucardev.springboottest.dto.request.AddressRequest;
import org.gucardev.springboottest.service.AddressService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@ConditionalOnExpression(
    "${address.controller.enabled:false}")
@RestController
@RequestMapping("/api/address")
public class AddressController {

  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<List<AddressDTO>> getAll(@PathVariable Long id) {
    List<AddressDTO> result = addressService.getAllByUserId(id);
    return ResponseEntity.ok(result);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
    return ResponseEntity.ok(addressService.getByIdDTO(id));
  }

  @PostMapping
  public ResponseEntity<AddressDTO> createAddress(@RequestBody @Valid AddressRequest addressRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(addressService.create(addressRequest));
  }

  @PutMapping
  public ResponseEntity<AddressDTO> updateAddress(@RequestBody @Valid AddressRequest addressRequest) {
    return ResponseEntity.ok(addressService.update(addressRequest));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<AddressDTO> deleteAddress(@PathVariable Long id) {
    addressService.delete(id);
    return ResponseEntity.ok().build();
  }
}
