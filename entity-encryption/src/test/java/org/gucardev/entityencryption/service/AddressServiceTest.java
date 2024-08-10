package org.gucardev.entityencryption.service;

import org.gucardev.entityencryption.dto.CreateAddressRequest;
import org.gucardev.entityencryption.entity.Address;
import org.gucardev.entityencryption.entity.User;
import org.gucardev.entityencryption.repository.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository mockAddressRepository;
    @Mock
    private UserService mockUserService;

    private AddressService addressServiceUnderTest;

    @BeforeEach
    void setUp() {
        addressServiceUnderTest = new AddressService(mockAddressRepository, mockUserService);
    }

    @Test
    void testGetAllAddresses() {
        // Setup
        // Configure AddressRepository.findAll(...).
        final Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        address.setCountry("country");
        final User user = new User();
        user.setName("name");
        address.setUser(user);
        final List<Address> addresses = List.of(address);
        when(mockAddressRepository.findAll()).thenReturn(addresses);

        // Run the test
        final List<Address> result = addressServiceUnderTest.getAllAddresses();

        // Verify the results
    }

    @Test
    void testGetAllAddresses_AddressRepositoryReturnsNoItems() {
        // Setup
        when(mockAddressRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Address> result = addressServiceUnderTest.getAllAddresses();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetAddressById() {
        // Setup
        // Configure AddressRepository.findById(...).
        final Address address1 = new Address();
        address1.setStreet("street");
        address1.setCity("city");
        address1.setCountry("country");
        final User user = new User();
        user.setName("name");
        address1.setUser(user);
        final Optional<Address> address = Optional.of(address1);
        when(mockAddressRepository.findById(0L)).thenReturn(address);

        // Run the test
        final Optional<Address> result = addressServiceUnderTest.getAddressById(0L);

        // Verify the results
    }

    @Test
    void testGetAddressById_AddressRepositoryReturnsAbsent() {
        // Setup
        when(mockAddressRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final Optional<Address> result = addressServiceUnderTest.getAddressById(0L);

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testCreateAddress() {
        // Setup
        final CreateAddressRequest request = new CreateAddressRequest();
        request.setStreet("street");
        request.setCity("city");
        request.setCountry("country");
        request.setUserId(0L);

        // Configure UserService.getUserById(...).
        final User user1 = new User();
        user1.setName("name");
        user1.setUsername("username");
        final Address address = new Address();
        address.setStreet("street");
        address.setCity("city");
        user1.setAddresses(List.of(address));
        final Optional<User> user = Optional.of(user1);
        when(mockUserService.getUserById(0L)).thenReturn(user);

        // Configure AddressRepository.save(...).
        final Address address1 = new Address();
        address1.setStreet("street");
        address1.setCity("city");
        address1.setCountry("country");
        final User user2 = new User();
        user2.setName("name");
        address1.setUser(user2);
        when(mockAddressRepository.save(any(Address.class))).thenReturn(address1);

        // Run the test
        final Address result = addressServiceUnderTest.createAddress(request);

        // Verify the results
    }

    @Test
    void testCreateAddress_UserServiceReturnsAbsent() {
        // Setup
        final CreateAddressRequest request = new CreateAddressRequest();
        request.setStreet("street");
        request.setCity("city");
        request.setCountry("country");
        request.setUserId(0L);

        when(mockUserService.getUserById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> addressServiceUnderTest.createAddress(request)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void testUpdateAddress() {
        // Setup
        final Address updatedAddress = new Address();
        updatedAddress.setStreet("street");
        updatedAddress.setCity("city");
        updatedAddress.setCountry("country");
        final User user = new User();
        user.setName("name");
        updatedAddress.setUser(user);

        // Configure AddressRepository.findById(...).
        final Address address1 = new Address();
        address1.setStreet("street");
        address1.setCity("city");
        address1.setCountry("country");
        final User user1 = new User();
        user1.setName("name");
        address1.setUser(user1);
        final Optional<Address> address = Optional.of(address1);
        when(mockAddressRepository.findById(0L)).thenReturn(address);

        // Configure AddressRepository.save(...).
        final Address address2 = new Address();
        address2.setStreet("street");
        address2.setCity("city");
        address2.setCountry("country");
        final User user2 = new User();
        user2.setName("name");
        address2.setUser(user2);
        when(mockAddressRepository.save(any(Address.class))).thenReturn(address2);

        // Run the test
        final Address result = addressServiceUnderTest.updateAddress(0L, updatedAddress);

        // Verify the results
    }

    @Test
    void testUpdateAddress_AddressRepositoryFindByIdReturnsAbsent() {
        // Setup
        final Address updatedAddress = new Address();
        updatedAddress.setStreet("street");
        updatedAddress.setCity("city");
        updatedAddress.setCountry("country");
        final User user = new User();
        user.setName("name");
        updatedAddress.setUser(user);

        when(mockAddressRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> addressServiceUnderTest.updateAddress(0L, updatedAddress))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testDeleteAddress() {
        // Setup
        // Run the test
        addressServiceUnderTest.deleteAddress(0L);

        // Verify the results
        verify(mockAddressRepository).deleteById(0L);
    }
}
