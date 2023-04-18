package ru.retail.expert.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.retail.expert.model.Shipment;

import java.time.LocalDate;
import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    @Query(value = "SELECT * FROM shipment AS sh " +
            "WHERE sh.shipment_date IN (:dates) AND " +
            "sh.chain_name IN (:chains) AND " +
            "sh.material_id IN (:products)",
            nativeQuery = true)
    List<Shipment> loadShipments(@Param("dates") List<LocalDate> dates,
                                 @Param("chains") List<String> chains,
                                 @Param("products") List<Long> products,
                                 Pageable pageable);
}
