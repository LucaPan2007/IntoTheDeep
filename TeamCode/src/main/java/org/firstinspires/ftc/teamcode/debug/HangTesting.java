package org.firstinspires.ftc.teamcode.debug;

import android.service.autofill.UserData;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class HangTesting extends LinearOpMode {
    public DcMotor m1,m2;
    @Override
    public void runOpMode() throws InterruptedException {
        m1=hardwareMap.get(DcMotorEx.class,"m1");
        m2=hardwareMap.get(DcMotorEx.class,"m2");
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            m1.setPower(gamepad1.right_stick_y);
            m2.setPower(gamepad1.right_stick_y);
        }
    }
}

