package com.luisbsb.quarkuscrud;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.luisbsb.quarkuscrud.dto.AddressDto;
import com.luisbsb.quarkuscrud.service.AddressService;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class AddressResourceTest {
	
	@InjectMocks
    @RestClient 
    AddressService addressService;

    @Test
    public void testCep() {
    	when(addressService.getAdressByCep(anyString())).thenReturn(getAddress());
    }
    
    public AddressDto getAddress() {
    	AddressDto dto = new AddressDto();
    	return dto;
    }
    
}
