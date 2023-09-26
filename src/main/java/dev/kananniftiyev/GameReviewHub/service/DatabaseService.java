package dev.kananniftiyev.GameReviewHub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    @Value("${secrets.DB_CONNECTION}")
    private String dbConnection;

    public String getDbConnection() {
        return dbConnection;
    }

}
