package com.foodrush.mobile_api.repository;

import com.foodrush.mobile_api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {
    @Query(value = "select address_id as id, address, latitude, longitude, title, type, address.user_id, distance\n" +
            "from address join orders on address.id = orders.address_id\n" +
            "where address.user_id = ?\n" +
            "order by orders.id desc\n" +
            "limit 1\n", nativeQuery = true)
    Optional<Address> findByLastOrder(Long user_id);
}
