package com.production.production_control.repository;

import com.production.production_control.entity.Production;
import com.production.production_control.dto.response.DashboardProductionTrendResponse;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductionRepository extends JpaRepository<Production, Long> {

    @Query(value = """
        SELECT\s
            TO_CHAR(production_date, 'Mon') as month,
            SUM(total_value) as value
        FROM production
        GROUP BY\s
            DATE_PART('month', production_date),
            TO_CHAR(production_date, 'Mon')
        ORDER BY DATE_PART('month', production_date)
       \s""",
            nativeQuery = true)
    List<Object[]> getProductionTrendRaw();

}