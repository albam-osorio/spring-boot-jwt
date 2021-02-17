package co.gov.sic.app.polls.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.sic.app.polls.models.PollModel;
import co.gov.sic.app.polls.services.api.PollService;
import co.gov.sic.core.web.CrudRestController;
import lombok.val;

@RestController
@RequestMapping("/api/v1/polls")
public class PollController extends CrudRestController<PollModel, Long> {

	@Autowired
	private PollService service;

	@Override
	protected PollService getService() {
		return service;
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping(PATH_LIST)
	public ResponseEntity<List<PollModel>> list() {
		val result = getService().findAll();
		return ResponseEntity.ok(result);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<List<PollModel>> list(@PathVariable List<Long> ids) {
		return super.list(ids);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<PollModel> get(@PathVariable Long id) {
		return super.get(id);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> create(@Valid @RequestBody PollModel model, BindingResult bindingResult) {
		return super.create(model, bindingResult);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> update(@Valid @RequestBody PollModel model, BindingResult bindingResult) {
		return super.update(model, bindingResult);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return super.delete(id);
	}

	@PreAuthorize("hasRole('USER')")
	@Override
	public ResponseEntity<?> delete(@PathVariable Long id, @PathVariable Integer version) {
		return super.delete(id, version);
	}
}
