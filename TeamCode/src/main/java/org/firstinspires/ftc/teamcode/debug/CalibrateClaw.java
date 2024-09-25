package org.firstinspires.ftc.teamcode.debug;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
@Config
@TeleOp
public class CalibrateClaw extends LinearOpMode {
    public static double POSITION;
    @Override
    public void runOpMode() throws InterruptedException {
        Claw claw = new Claw(hardwareMap);
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            if(!gamepad1.triangle && !gamepad1.square)
                claw.setPosition(POSITION);
            if(gamepad1.triangle)
                claw.open();
            else if(gamepad1.square)
                claw.close();
        }
    }
}
