package no.vipps.twitchecom.repository;

import no.vipps.twitchecom.entity.OrderModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<OrderModel, Integer> {
    Optional<OrderModel> findByVippsOrderId(String vippsOrderId);

    Optional<OrderModel> findTopByOrderByIdDesc();


}
