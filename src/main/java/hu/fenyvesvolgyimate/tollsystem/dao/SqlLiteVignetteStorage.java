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
        vignette2.setRegistrationNumber("abc-123");
        vignette2.setPrice(321);
        vignette2.setVehicleCategory("m3");
        vignette2.setType("yearly");
        vignette2.setValidFrom(new Date());
        vignette2.setValidTo(new Date());
        vignette2.setDateOfPurchase(new Date());

        return List.of(vignette, vignette2);
    }
}
