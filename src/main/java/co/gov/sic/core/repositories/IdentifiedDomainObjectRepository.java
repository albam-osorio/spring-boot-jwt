package co.gov.sic.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import co.gov.sic.core.domain.IdentifiedDomainObject;

@NoRepositoryBean
public interface IdentifiedDomainObjectRepository<E extends IdentifiedDomainObject<ID>, ID>
		extends JpaRepository<E, ID> {
}