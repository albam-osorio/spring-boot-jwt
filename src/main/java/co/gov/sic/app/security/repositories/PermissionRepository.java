package co.gov.sic.app.security.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.gov.sic.app.security.entities.Permission;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
	Permission findByName(String name);
}
