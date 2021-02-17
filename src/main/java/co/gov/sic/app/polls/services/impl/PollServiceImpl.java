package co.gov.sic.app.polls.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sic.app.polls.entities.Poll;
import co.gov.sic.app.polls.models.PollModel;
import co.gov.sic.app.polls.repositories.PollRepository;
import co.gov.sic.app.polls.services.api.PollService;
import co.gov.sic.core.services.impl.CrudServiceImpl;
import lombok.val;

@Service
public class PollServiceImpl extends CrudServiceImpl<Poll, PollModel, Long> implements PollService {

	@Autowired
	private PollRepository repository;

	@Override
	protected PollRepository getRepository() {
		return repository;
	}

	@Override
	protected PollModel asModel(Poll entity) {
		val model = newModel();
		mapModel(entity, model);

		model.setDocumentNumber(entity.getDocumentNumber());
		model.setEmail(entity.getEmail());
		model.setIdBrand(entity.getIdBrand());
		model.setComments(entity.getComments());
		
		return model;
	}

	@Override
	protected PollModel newModel() {
		return new PollModel();
	}

	@Override
	protected Poll mergeEntity(PollModel model, Poll entity) {

		entity.setDocumentNumber(model.getDocumentNumber());
		entity.setEmail(model.getEmail());
		entity.setIdBrand(model.getIdBrand());
		entity.setComments(model.getComments());
		entity.setVersion(model.getVersion());

		return entity;
	}

	@Override
	protected Poll newEntity() {
		return new Poll();
	}

	@Override
	public List<PollModel> findAll() {
		val entities = getRepository().findAll();
		val result = asModels(entities);
		return result;
	}
}