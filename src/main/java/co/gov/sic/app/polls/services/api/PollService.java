package co.gov.sic.app.polls.services.api;

import java.util.List;

import co.gov.sic.app.polls.models.PollModel;
import co.gov.sic.core.services.api.CrudService;

public interface PollService extends CrudService<PollModel, Long> {
	
	List<PollModel> findAll();

}
