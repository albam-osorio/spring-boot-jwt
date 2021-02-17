package co.gov.sic.app.polls.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.sic.app.polls.entities.Brand;
import co.gov.sic.app.polls.models.BrandModel;
import co.gov.sic.app.polls.repositories.BrandRepository;
import co.gov.sic.app.polls.services.api.BrandService;
import co.gov.sic.core.services.impl.QueryServiceImpl;
import lombok.val;

@Service
public class BrandServiceImpl extends QueryServiceImpl<Brand, BrandModel, Long> implements BrandService {

	@Autowired
	private BrandRepository repository;

	@Override
	protected BrandRepository getRepository() {
		return repository;
	}

	@Override
	protected BrandModel asModel(Brand entity) {
		val model = newModel();
		
		model.setId(entity.getId());
		model.setCode(entity.getCode());
		model.setName(entity.getName());
		
		return model;
	}

	@Override
	protected BrandModel newModel() {
		return new BrandModel();
	}
	
	@Override
	public List<BrandModel> findAll() {
		val entities = getRepository().findAll();
		val result = asModels(entities);
		return result;
	}
}