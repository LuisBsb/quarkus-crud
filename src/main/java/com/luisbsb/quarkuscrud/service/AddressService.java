package com.luisbsb.quarkuscrud.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.luisbsb.quarkuscrud.dto.AddressDto;

@Path("/ws")
@RegisterRestClient(configKey="address-api")
public interface AddressService {

    @GET
    @Path("/{cep}/json/")
    public AddressDto getAdressByCep(@PathParam("cep") String cep);
    
}
