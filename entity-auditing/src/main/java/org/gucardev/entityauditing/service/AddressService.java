package org.gucardev.entityauditing.service;

import org.gucardev.entityauditing.dto.AddressDTO;
import org.gucardev.entityauditing.dto.AddressHistoryDTO;
import org.gucardev.entityauditing.dto.request.AddressRequest;

import java.util.List;

public interface AddressService {

  List<AddressDTO> getAll();

  AddressDTO getById(Long id);

  AddressDTO create(AddressRequest addressRequest);

  AddressDTO update(AddressRequest addressRequest);

  void delete(Long id);

  List<AddressHistoryDTO> getAddressHistory(Long id);
}
