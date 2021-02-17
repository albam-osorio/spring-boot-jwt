package co.gov.sic.app.polls.services.api;

import java.util.List;

import co.gov.sic.app.polls.models.BrandModel;
import co.gov.sic.core.services.api.QueryService;

public interface BrandService extends QueryService<BrandModel, Long> {

	List<BrandModel> findAll();

}
