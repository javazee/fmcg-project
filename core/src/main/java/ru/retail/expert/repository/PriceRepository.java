package ru.retail.expert.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.retail.expert.model.Price;
import ru.retail.expert.model.PricePk;

import java.util.List;
import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, PricePk> {

    @Query(value = "select * from price where material_id = :material_id and chain_name = :chain_name",
            nativeQuery = true
    )
    Optional<Price> findByMaterialIdAndChainName(@Param("material_id") Long materialId,
                                                 @Param("chain_name") String chainName);

    @Query(value = "select * from price where material_id = :material_id",
            nativeQuery = true)
    List<Price> findPriceByProduct(@Param("material_id") Long materialId, Pageable pageable);

    @Query(value = "select * from price where chain_name = :chain_name",
            nativeQuery = true)
    List<Price> findPriceByChainName(@Param("chain_name") String chainName, Pageable pageable);

    @Modifying
    @Query(value = "delete from price where material_id = :material_id and chain_name = :chain_name",
            nativeQuery = true
    )
    void deleteByMaterialIdAndChainName(@Param("material_id") Long materialId,
                                        @Param("chain_name") String chainName);

    @Modifying
    @Query(value = "delete from price where material_id = :material_id",
            nativeQuery = true
    )
    void deleteByMaterialId(@Param("material_id") Long materialId);

    @Modifying
    @Query(value = "delete from price where chain_name = :chain_name",
            nativeQuery = true
    )
    void deleteByChainName(@Param("chain_name") String chainName);
}
