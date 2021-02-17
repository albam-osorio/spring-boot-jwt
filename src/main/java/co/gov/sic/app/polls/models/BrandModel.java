package co.gov.sic.app.polls.models;

import co.gov.sic.core.models.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
public class BrandModel extends BaseModel<Long>{
	public String code;
	public String name;
}
