package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public static double CLOSED=0.76,OPEN=1,pos;
    public Servo claw;
    public Claw(HardwareMap hardwareMap)
    {
       claw=hardwareMap.get(Servo.class,"servoClaw");
       claw.setPosition(1);
       //claw.setDirection(Servo.Direction.REVERSE);
    }
    public void open() {claw.setPosition(OPEN);}
    public void close()
   {
       claw.setPosition(CLOSED);
   }
    public void setPosition(double pos){
       claw.setPosition(pos);
   }
}
