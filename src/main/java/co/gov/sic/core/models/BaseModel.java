package co.gov.sic.core.models;

import co.gov.sic.core.domain.IdentifiedDomainObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel<ID> implements IdentifiedDomainObject<ID> {

	private ID id;

}
