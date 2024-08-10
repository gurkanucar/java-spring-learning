package org.gucardev.springboottest.service.impl;

import org.gucardev.springboottest.dto.AddressDTO;
import org.gucardev.springboottest.dto.converter.AddressConverter;
import org.gucardev.springboottest.dto.request.AddressRequest;
import org.gucardev.springboottest.exception.NotFoundException;
import org.gucardev.springboottest.model.Address;
import org.gucardev.springboottest.repository.AddressRepository;
import org.gucardev.springboottest.service.AddressService;
import org.gucardev.springboottest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"address"})
@Slf4j
public class AddressServiceImpl implements AddressService {

  private final AddressRepository addressRepository;
  private final UserService userService;
  private final AddressConverter addressConverter;

  public AddressServiceImpl(
      AddressRepository addressRepository,
      UserService userService,
      AddressConverter addressConverter) {
    this.addressRepository = addressRepository;
    this.userService = userService;
    this.addressConverter = addressConverter;
  }

  /** Clear cache. */
  @CacheEvict(allEntries = true)
  @PostConstruct
  @Scheduled(fixedRateString = "${caching.config.address.cache-ttl}")
  public void clearCache() {
    log.info("Caches are cleared");
  }

  @Override
  @Cacheable(key = "#id")
  public List<AddressDTO> getAllByUserId(Long id) {
    if (Boolean.FALSE.equals(userService.userExistsById(id))) {
      throw new NotFoundException("user not found!");
    }
    return addressRepository.findAllByUser_Id(id).stream()
        .map(addressConverter::mapToDTO)
        .collect(Collectors.toList());
  }

  @Override
  @Cacheable(key = "#id")
  public AddressDTO getByIdDTO(Long id) {
    return addressConverter.mapToDTO(getById(id));
  }

  @Override
  public Address getById(Long id) {
    return addressRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("address not found!"));
  }

  @Override
  public AddressDTO create(AddressRequest addressRequest) {
    if (Boolean.FALSE.equals(userService.userExistsById(addressRequest.getUserId()))) {
      throw new NotFoundException("user not found!");
    }
    Address address = addressConverter.mapToEntity(addressRequest);

    return addressConverter.mapToDTO(addressRepository.save(address));
  }

  @Override
  public AddressDTO update(AddressRequest addressRequest) {
    Address existing = getById(addressRequest.getId());
    existing.setTitle(addressRequest.getTitle());
    existing.setDetail(addressRequest.getDetail());
    return addressConverter.mapToDTO(addressRepository.save(existing));
  }

  @Override
  public void delete(Long id) {
    Address existing = getById(id);
    addressRepository.delete(existing);
  }
}
