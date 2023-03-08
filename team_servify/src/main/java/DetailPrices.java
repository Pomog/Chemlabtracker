import java.math.BigDecimal;

class DetailPrices {
    // Bonnet – капот.
    // Boot  – багажник.
    // Bumper  – бампер.
    // Roof - крыша.
    // Door – дверь.
    // Wing - крыло автомобиля
    // Wing mirror  – боковое зеркало.
    Detail bonnet = new Detail("bonnet","","");
    Detail boot = new Detail("boot","","");
    Detail frontBumper = new Detail("bumper","front","");
    Detail rearBumper = new Detail("bumper","rear","");
    Detail roof = new Detail("roof","","");
    Detail frontLeftDoor = new Detail("door","front","left");
    Detail frontRightDoor = new Detail("door","front","right");
    Detail rearLeftDoor = new Detail("door","rear","left");
    Detail rearRightDoor = new Detail("door","rear","right");
    Detail frontLeftWing = new Detail("wing","front","left");
    Detail frontRightWing = new Detail("wing","front","right");
    Detail rearLeftWing = new Detail("wing","rear","left");
    Detail rearRightWing = new Detail("wing","rear","right");
    Detail leftWingMirror = new Detail("wing mirror","","left");
    Detail rightWingMirror = new Detail("wing mirror","","right");
    void setPrices(){
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
