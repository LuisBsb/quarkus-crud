package com.luisbsb.quarkuscrud.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie extends PanacheEntity {
	
	@NotBlank(message = "O tipo do filme deve ser informado.")
	@Column(length = 100)
	public String title;
	
	@NotBlank(message = "A descrição do filme deve ser informado.")
	@Column(columnDefinition="text")
	public String description;
	
	@NotBlank(message = "O diretor do filme deve ser informado.")
	@Column(length = 100)
	public String director;
	
	@NotBlank(message = "O país do filme deve ser informado.")
	@Column(length = 100)
	public String country;
	
}
