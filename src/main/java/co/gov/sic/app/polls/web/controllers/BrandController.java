package co.gov.sic.app.polls.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sic.app.polls.models.BrandModel;
import co.gov.sic.app.polls.services.api.BrandService;
import co.gov.sic.core.web.QueryRestController;
import lombok.val;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController extends QueryRestController<BrandModel, Long> {

	@Autowired
	private BrandService service;

	@Override
	protected BrandService getService() {
		return service;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(PATH_LIST)
	public ResponseEntity<List<BrandModel>> list() {
		val result = getService().findAll();
		return ResponseEntity.ok(result);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseEntity<List<BrandModel>> list(@PathVariable List<Long> ids) {
		return super.list(ids);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Override
	public ResponseEntity<BrandModel> get(@PathVariable Long id) {
		return super.get(id);
	}
}
