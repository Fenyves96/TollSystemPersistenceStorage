package hu.fenyvesvolgyimate.tollsystem.dao;

import hu.fenyvesvolgyimate.tollsystem.entity.Vignette;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmergencyVignetteStorage implements VignetteStorage{
    @Override
    public List<Vignette> findVignettesByRegistrationNumber(String registrationNumber) {
        String line;
        List<Vignette> vignettes = new ArrayList<>();
        try {
            BufferedReader bufferreader = new BufferedReader(new FileReader(vignetteFilePath));
            while((line = bufferreader.readLine()) != null){
                Vignette vignette = parseVignette(line);
                if(vignette.getRegistrationNumber().equals(registrationNumber))
                    vignettes.add(vignette);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return vignettes;
    }

    String vignetteFilePath = "D:\\common\\vignettes.txt";
    final String SEPARATOR = ";";

//    private String generateVignetteRow(Vignette vignette) {
//        return vignette.getRegistrationNumber() + SEPARATOR +
//                vignette.getVehicleCategory() + SEPARATOR +
//                vignette.getType() + SEPARATOR +
//                vignette.getPrice() + SEPARATOR +
//                vignette.getDateOfPurchase() + SEPARATOR +
//                vignette.getValidFrom() + SEPARATOR +
//                vignette.getValidTo();
//    }

    private Vignette parseVignette(String line) {
        String [] vignetteAttributes = line.split(SEPARATOR);
        Vignette vignette = new Vignette();
        vignette.setRegistrationNumber(vignetteAttributes[0]);
        vignette.setVehicleCategory(vignetteAttributes[1]);
        vignette.setType(vignetteAttributes[2]);
        vignette.setPrice(Integer.parseInt(vignetteAttributes[3]));
        try {
            vignette.setDateOfPurchase(new SimpleDateFormat("yyyy-MM-dd").parse(vignetteAttributes[4]));
            vignette.setValidFrom(new SimpleDateFormat("yyyy-MM-dd").parse(vignetteAttributes[5]));
            vignette.setValidTo(new SimpleDateFormat("yyyy-MM-dd").parse(vignetteAttributes[6]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return vignette;
    }
}
