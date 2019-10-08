package no.vipps.twitchecom.repository;

import no.vipps.twitchecom.entity.Donation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonationRepository extends CrudRepository<Donation, Integer> {

    Optional<Donation> findByVippsOrderId(String vippsOrderId);

}
