package com.salafacil.SalaFacilSpace.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professor", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "matricula") })
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
	private String nome;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email deve ser válido")
	@Size(max = 100, message = "Email deve ter no máximo 100 caracteres")
	private String email;

	@NotBlank(message = "Senha é obrigatória")
	@Size(min = 6, max = 100, message = "Senha deve ter entre 6 e 100 caracteres")
	private String senha;

	@NotBlank(message = "Matrícula é obrigatória")
	@Size(max = 20, message = "Matrícula deve ter no máximo 20 caracteres")
	private String matricula;
}