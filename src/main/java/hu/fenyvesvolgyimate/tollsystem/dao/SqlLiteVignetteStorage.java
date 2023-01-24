package hu.fenyvesvolgyimate.tollsystem.dao;

import hu.fenyvesvolgyimate.tollsystem.entity.Vehicle;
import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class SqlLiteVignetteStorage implements VignetteStorage {
    public SqlLiteVignetteStorage(){
        createTableIfNotExists();
    }

    private Connection connect() {
        String url = "jdbc:sqlite:D://common/VIGNETTES.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void initDatas(){
        String url = "jdbc:sqlite:D://common/VIGNETTES.db";
        if(selectAll() != null)
            return;
        // SQL statement for creating a new table
        String sql = """
            INSERT INTO VIGNETTES(
            registration_number, vehicle_category, price, type, date_of_purchase, valid_from, valid_to) VALUES(
            'ABC-123', 'd1', 123, 'monthly', '2023-01-01', '2023-01-01', '2023-01-30')
            INSERT INTO VIGNETTES(
            registration_number, vehicle_category, price, type, date_of_purchase, valid_from, valid_to) VALUES(
            'ABC-123', 'd1', 123, 'monthly', '2023-01-01', '2022-01-01', '2022-01-30');
            """;
        try {
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Vignette> selectAll(){
        String sql = "SELECT * FROM VIGNETTES";

        try {
            Connection conn = this.connect();
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery(sql);

            List<Vignette> results = new ArrayList<>();
            // loop through the result set
            while (rs.next()) {
                String registrationNumber = rs.getString("registration_number");
                String vehicleCategory = rs.getString("vehicle_category");
                int price = rs.getInt("price");
                String type = rs.getString("type");
                Date dateOfPurchase = rs.getDate("date_of_purchase");
                Date validFrom = rs.getDate("valid_from");
                Date validTo = rs.getDate("valid_to");

                Vignette vignette = new Vignette();
                vignette.setRegistrationNumber(registrationNumber);
                vignette.setVehicleCategory(vehicleCategory);
                vignette.setPrice(price);
                vignette.setType(type);
                vignette.setDateOfPurchase(dateOfPurchase);
                vignette.setValidFrom(validFrom);
                vignette.setValidTo(validTo);

                results.add(vignette);
            }
            return results;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createTableIfNotExists() {
        Connection connection = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:D:/common/VIGNETTES.db");
            Statement stmt = connection.createStatement();
            String createTableSql = """
            CREATE TABLE IF NOT EXISTS VIGNETTES (
            id integer PRIMARY KEY,
            registration_number text NOT NULL,
            vehicle_category text NOT NULL,
            price real,
            type text NOT NULL,
            date_of_purchase datetime default current_timestamp,
            valid_from datetime default current_timestamp,
            valid_to datetime default current_timestamp
            )
            """;
            stmt.execute(createTableSql);
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    @Override
    public List<Vignette> findVignettesByRegistrationNumber(String registrationNumber) {
        List<Vignette> allVignettes = selectAll();
        return allVignettes.stream().filter(v -> Objects.equals(v.getRegistrationNumber(), registrationNumber)).toList();
    }
}
