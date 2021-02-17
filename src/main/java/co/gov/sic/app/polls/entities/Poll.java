package co.gov.sic.app.polls.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;

import co.gov.sic.core.domain.AuditableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "polls")
@DynamicUpdate
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class Poll extends AuditableEntity<Long>{
	
	public long documentNumber;
	
	@NotNull(message = "El email es un dato requerido")
	@Email(message = "Email debe ser una cuenta de correo valida")
	@Column(length = 50, nullable = false)
	public String email;

	@Column(name ="id_brand", nullable = false)
	public long idBrand;

	@NotBlank(message = "El cometario es un dato requerido")
	@Column(length = -1, nullable = false)
	public String comments;
	
}
