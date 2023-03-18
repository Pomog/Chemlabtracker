package lv.javaguru.java2.servify.domain;

import lv.javaguru.java2.servify.detail_builder.DetailBuilder;
import lv.javaguru.java2.servify.domain.Detail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceList {

    private List<Detail> detailPricesList = new ArrayList<>();

    public PriceList() {
        Detail bonnet = new DetailBuilder("Bonnet")
                .setPrice(BigDecimal.valueOf(200))
                .build();
        detailPricesList.add(bonnet);
        Detail boot = new DetailBuilder("Boot")
                .setPrice(BigDecimal.valueOf(180))
                .build();
        detailPricesList.add(boot);
        Detail roof = new DetailBuilder("Roof")
                .setPrice(BigDecimal.valueOf(250))
                .build();
        detailPricesList.add(roof);
        Detail frontBumper = new DetailBuilder("Bumper")
                .setPrice(BigDecimal.valueOf(180))
                .setLocation("Front")
                .build();
        detailPricesList.add(frontBumper);
        Detail rearBumper = new DetailBuilder("Bumper")
                .setPrice(BigDecimal.valueOf(150))
                .setLocation("Rear")
                .build();
        detailPricesList.add(rearBumper);
        Detail frontLeftDoor = new DetailBuilder("Door")
                .setPrice(BigDecimal.valueOf(180))
                .setLocation("Front")
                .setSide("Left")
                .build();
        detailPricesList.add(frontLeftDoor);
        Detail frontRightDoor = new DetailBuilder("Door")
                .setPrice(BigDecimal.valueOf(180))
                .setLocation("Front")
                .setSide("Right")
                .build();
        detailPricesList.add(frontRightDoor);
        Detail rearLeftDoor = new DetailBuilder("Door")
                .setPrice(BigDecimal.valueOf(180))
                .setLocation("Rear")
                .setSide("Left")
                .build();
        detailPricesList.add(rearLeftDoor);
        Detail rearRightDoor = new DetailBuilder("Door")
                .setPrice(BigDecimal.valueOf(180))
                .setLocation("Rear")
                .setSide("Right")
                .build();
        detailPricesList.add(rearRightDoor);
        Detail frontLeftWing = new DetailBuilder("Wing")
                .setPrice(BigDecimal.valueOf(130))
                .setLocation("Front")
                .setSide("Left")
                .build();
        detailPricesList.add(frontLeftWing);
        Detail frontRightWing = new DetailBuilder("Wing")
                .setPrice(BigDecimal.valueOf(130))
                .setLocation("Front")
                .setSide("Right")
                .build();
        detailPricesList.add(frontRightWing);
        Detail rearLeftWing = new DetailBuilder("Wing")
                .setPrice(BigDecimal.valueOf(160))
                .setLocation("Rear")
                .setSide("Left")
                .build();
        detailPricesList.add(rearLeftWing);
        Detail rearRightWing = new DetailBuilder("Wing")
                .setPrice(BigDecimal.valueOf(160))
                .setLocation("Rear")
                .setSide("Right")
                .build();
        detailPricesList.add(rearRightWing);
        Detail leftWingMirror = new DetailBuilder("Wing mirror")
                .setPrice(BigDecimal.valueOf(60))
                .setSide("Left")
                .build();
        detailPricesList.add(leftWingMirror);
        Detail rightWingMirror = new DetailBuilder("Wing mirror")
                .setPrice(BigDecimal.valueOf(60))
                .setSide("Right")
                .build();
        detailPricesList.add(rightWingMirror);
    }

    public List<Detail> getDetailPricesList() {
        return detailPricesList;
    }

}
