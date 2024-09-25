package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Linkage {
    public static int EXTENDED=459,INIT=0;//change
    public DcMotor linkage;

    public Linkage(HardwareMap hardwareMap)
    {
        linkage = hardwareMap.get(DcMotorEx.class,"motorLinkage");

        //auxpiv.setDirection(Servo.Direction.REVERSE);

        linkage.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //linkage.setDirection(DcMotorSimple.Direction.REVERSE); eventually
        linkage.setTargetPosition(0);
        linkage.setPower(0.7);
        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public Action init()
    {
        linkage.setTargetPosition(INIT-60);
        linkage.setPower(-0.7);
        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        return null;
    }
    public Action extended()
    {
        linkage.setTargetPosition(EXTENDED);
        linkage.setPower(0.7);
        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        return null;
    }
    public int getPos()
    {
        return linkage.getCurrentPosition();
    }
    public void runtTo(int pos,double pow)
    {
        linkage.setTargetPosition(pos);
        if(pos<linkage.getCurrentPosition())
            pow=-pow;
        linkage.setPower(pow);
        linkage.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }



}
