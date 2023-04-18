package ru.retail.expert.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.retail.expert.model.ShipmentStats;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnalyseDao {

    private final JdbcTemplate jdbcTemplate;

    public List<ShipmentStats> getFullStats(Integer offset, Integer perPage) {
        String limit = "";
        if (offset == null && perPage != null) {
            limit = "LIMIT " + perPage;
        } else if (offset != null && perPage != null){
            limit = "LIMIT " + offset + ", " + perPage;
        }
        String sql = "SELECT chain_name, category_code, month(shipment_date) AS shipment_month, sum(total_cost) AS cost_sum, sum(volume) AS volume_sum, price_tag " +
                "FROM SHIPMENT AS sh " +
                "JOIN product AS p ON sh.material_id = p.material_id " +
                "GROUP BY chain_name, category_code, month(shipment_date), price_tag " + limit;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ShipmentStats.class));
    }
}
