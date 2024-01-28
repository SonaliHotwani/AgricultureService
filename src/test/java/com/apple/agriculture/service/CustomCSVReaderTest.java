package com.apple.agriculture.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles(profiles = {"test"})
class CustomCSVReaderTest {

    @Value("${spring.datasource.url}")
    String jdbcUrl;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Autowired
    CustomCSVReader customCSVReader;

    @Test
    public void shouldReadCsvDataAndLoadInDB() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        String deleteSql = "DELETE from AGRICULTURE_CROP_PRODUCTION";
        PreparedStatement deleteStatement = connection.prepareStatement(deleteSql);
        deleteStatement.execute();
        customCSVReader.readAndLoadInDB("src/test/resources/sampleProductionData.csv");
        String sql = "SELECT * from AGRICULTURE_CROP_PRODUCTION";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        int count = 0;
        while (resultSet.next()) {
            count++;
        }
        assertEquals(4, count);
    }

}