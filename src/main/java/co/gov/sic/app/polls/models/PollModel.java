package co.gov.sic.app.polls.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import co.gov.sic.core.models.AuditableModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class PollModel extends AuditableModel<Long>{
	
	@NotNull(message = "El número de documento es un dato requerido")
	public Long documentNumber;
	
	@NotNull(message = "El email es un dato requerido")
	@Email(message = "Email debe ser una cuenta de correo valida")
	public String email;

	@NotNull(message = "La marca del PC es un dato requerido")
	public Long idBrand;

	@NotBlank(message = "El cometario es un dato requerido")
	public String comments;
}
