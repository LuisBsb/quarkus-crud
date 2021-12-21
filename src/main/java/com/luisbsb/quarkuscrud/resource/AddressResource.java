package com.luisbsb.quarkuscrud.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.luisbsb.quarkuscrud.dto.AddressDto;
import com.luisbsb.quarkuscrud.service.AddressService;

@Path("/address")
public class AddressResource {
    
    @Inject
    @RestClient
    AddressService addressService;
    
    @GET
    @Path("/buscaCep/{cep}")
    @Produces(MediaType.APPLICATION_JSON)
    public AddressDto buscaEmpresa(@PathParam("cep") String cep) {
        return addressService.getAdressByCep(cep);
    }

}
