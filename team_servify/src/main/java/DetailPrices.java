import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class DetailPrices {
    public static void main(String[] args) {


        Detail bonnet = new Detail("bonnet", "", "");
        Detail boot = new Detail("boot", "", "");
        Detail frontBumper = new Detail("bumper", "front", "");
        Detail rearBumper = new Detail("bumper", "rear", "");
        Detail roof = new Detail("roof", "", "");
        Detail frontLeftDoor = new Detail("door", "front", "left");
        Detail frontRightDoor = new Detail("door", "front", "right");
        Detail rearLeftDoor = new Detail("door", "rear", "left");
        Detail rearRightDoor = new Detail("door", "rear", "right");
        Detail frontLeftWing = new Detail("wing", "front", "left");
        Detail frontRightWing = new Detail("wing", "front", "right");
        Detail rearLeftWing = new Detail("wing", "rear", "left");
        Detail rearRightWing = new Detail("wing", "rear", "right");
        Detail leftWingMirror = new Detail("wing mirror", "", "left");
        Detail rightWingMirror = new Detail("wing mirror", "", "right");


        List<Detail> detailPricesList = new ArrayList<>();
        detailPricesList.add(bonnet);
        detailPricesList.add(boot);
        detailPricesList.add(roof);
        detailPricesList.add(frontBumper);
        detailPricesList.add(rearBumper);
        detailPricesList.add(frontLeftDoor);
        detailPricesList.add(frontRightDoor);
        detailPricesList.add(rearLeftDoor);
        detailPricesList.add(rearRightDoor);
        detailPricesList.add(frontLeftWing);
        detailPricesList.add(frontRightWing);
        detailPricesList.add(rearLeftWing);
        detailPricesList.add(rearRightWing);
        detailPricesList.add(leftWingMirror);
        detailPricesList.add(rightWingMirror);


        bonnet.setPrice(BigDecimal.valueOf(200));
        boot.setPrice(BigDecimal.valueOf(180));
        frontBumper.setPrice(BigDecimal.valueOf(180));
        rearBumper.setPrice(BigDecimal.valueOf(150));
        roof.setPrice(BigDecimal.valueOf(250));
        frontLeftDoor.setPrice(BigDecimal.valueOf(180));
        frontRightDoor.setPrice(BigDecimal.valueOf(180));
        rearLeftDoor.setPrice(BigDecimal.valueOf(180));
        rearRightDoor.setPrice(BigDecimal.valueOf(180));
        frontLeftWing.setPrice(BigDecimal.valueOf(130));
        frontRightWing.setPrice(BigDecimal.valueOf(130));
        rearLeftWing.setPrice(BigDecimal.valueOf(160));
        rearRightWing.setPrice(BigDecimal.valueOf(160));
        leftWingMirror.setPrice(BigDecimal.valueOf(60));
        rightWingMirror.setPrice(BigDecimal.valueOf(60));
    }
}
