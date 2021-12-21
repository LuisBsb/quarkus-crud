package com.luisbsb.quarkuscrud.resource;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.luisbsb.quarkuscrud.dto.Movie;

@Path("/movies")
public class MovieResource {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200")
	@Operation(description = "Busca de filmes", summary = "Busca todos os filmes cadastrados")
	public Response getAll() {
		List<Movie> movies = Movie.listAll();
		return Response.ok(movies).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200")
	@Operation(description = "Busca de filme por ID", summary = "Busca de filme especifico cadastrado através do identificador")
	public Response getById(@PathParam("id") Long id) {
		return Movie.findByIdOptional(id)
				.map(movie -> Response.ok(movie).build())
				.orElse(Response.status(Response.Status.NOT_FOUND).build());
	}
	
	@GET
	@Path("country/{country}")
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200")
	@Operation(description = "Busca de filme por pais", summary = "Busca de filme especifico cadastrado através do país")
	public Response getByCountry(@PathParam("country") String country) {
		List<Movie> movies = Movie.list("SELECT m FROM Movie m where m.country = ?1 ORDER BY id DESC", country);
		return Response.ok(movies).build();
	}
	
	@GET
	@Path("title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200")
	@Operation(description = "Busca de filme do titulo", summary = "Busca de filme especifico cadastrado através do titulo")
	public Response getByTitle(@PathParam("title") String title) {
		return Movie.find("title", title)
				.singleResultOptional()
				.map(movie -> Response.ok(movie).build())
				.orElse(Response.status(Response.Status.NOT_FOUND).build());
	}
	
	@POST
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200")
	@Operation(description = "Cadastro de filme", summary = "Realiza o cadastrado de um novo filme")
	public Response create(Movie movie) {
		Movie.persist(movie);
		if(movie.isPersistent()) {
			return Response.created(URI.create("/movies"+movie.id)).build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@DELETE
	@Path("{id}")
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@APIResponse(responseCode = "200")
	@Operation(description = "Exclusão de filme", summary = "Realiza a exclusão de um filme")
	public Response deleteById(@PathParam("id") Long id) {
		boolean deleted = Movie.deleteById(id);
		if(deleted) {
			return Response.noContent().build();
		}
		
		return Response.status(Response.Status.BAD_REQUEST).build();
	}

}
