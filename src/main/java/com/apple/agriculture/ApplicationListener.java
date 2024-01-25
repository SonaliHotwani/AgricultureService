package com.apple.agriculture;

import com.apple.agriculture.service.CustomCSVReader;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@Profile(value = "!test")
public class ApplicationListener implements org.springframework.context.ApplicationListener<ApplicationStartedEvent> {

    final CustomCSVReader customCsvReader;

    public ApplicationListener(CustomCSVReader customCsvReader) {
        this.customCsvReader = customCsvReader;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        String csvFilePath = "src/main/resources/India_Agriculture_Crop_Production.csv";
        try {
            customCsvReader.readAndLoadInDB(csvFilePath);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
