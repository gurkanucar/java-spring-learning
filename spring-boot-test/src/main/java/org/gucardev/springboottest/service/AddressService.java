package org.gucardev.springboottest.service;

import org.gucardev.springboottest.dto.AddressDTO;
import org.gucardev.springboottest.dto.request.AddressRequest;
import org.gucardev.springboottest.model.Address;

import java.util.List;

public interface AddressService {

  List<AddressDTO> getAllByUserId(Long id);

  AddressDTO getByIdDTO(Long id);

  Address getById(Long id);

  AddressDTO create(AddressRequest addressRequest);

  AddressDTO update(AddressRequest addressRequest);

  void delete(Long id);
}
