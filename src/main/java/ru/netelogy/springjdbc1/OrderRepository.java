package ru.netelogy.springjdbc1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Repository
public class OrderRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String findProductByNameSql;

    @Autowired
    public OrderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.findProductByNameSql = read("find_product_by_name.sql");
    }
    public String getProductName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        List<String> productNames = jdbcTemplate.query(
                findProductByNameSql,
                params,
                (rs, rowNum) -> rs.getString("product_name")
        );
        return productNames.get(0);
    }
    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
