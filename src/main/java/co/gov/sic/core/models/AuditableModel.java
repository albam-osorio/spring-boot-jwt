package co.gov.sic.core.models;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import co.gov.sic.core.domain.VersionableObject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public abstract class AuditableModel<ID> extends BaseModel<ID> implements VersionableObject {

	@NumberFormat
	private int version;

	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaCreacion;

	@DateTimeFormat(style = "M-")
	private LocalDateTime fechaModificacion;
}
