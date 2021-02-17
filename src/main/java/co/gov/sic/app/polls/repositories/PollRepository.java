package co.gov.sic.app.polls.repositories;

import co.gov.sic.app.polls.entities.Poll;
import co.gov.sic.core.repositories.IdentifiedDomainObjectRepository;

public interface PollRepository extends IdentifiedDomainObjectRepository<Poll, Long> {

}