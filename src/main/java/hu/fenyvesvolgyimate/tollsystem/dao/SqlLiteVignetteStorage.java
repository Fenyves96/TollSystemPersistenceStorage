package hu.fenyvesvolgyimate.tollsystem.dao;

import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;

import java.util.Date;
import java.util.List;

public class SqlLiteVignetteStorage implements VignetteStorage {
    @Override
    public List<Vignette> findVignettesByRegistrationNumber(String registrationNumber) {
        Vignette vignette = new Vignette();
        vignette.setRegistrationNumber("abc-123");
        vignette.setPrice(123);
        vignette.setVehicleCategory("m3");
        vignette.setType("monthly");
        vignette.setValidFrom(new Date());
        vignette.setValidTo(new Date());
        vignette.setDateOfPurchase(new Date());

        Vignette vignette2 = new Vignette();
        vignette.setRegistrationNumber("abc-123");
        vignette.setPrice(321);
        vignette.setVehicleCategory("m3");
        vignette.setType("yearly");
        vignette.setValidFrom(new Date());
        vignette.setValidTo(new Date());
        vignette.setDateOfPurchase(new Date());

        return List.of(vignette, vignette2);
    }
}
