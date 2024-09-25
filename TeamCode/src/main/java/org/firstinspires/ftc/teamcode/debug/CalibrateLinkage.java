package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Linkage;

@TeleOp
public class CalibrateLinkage extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Linkage linkage = new Linkage(hardwareMap);
        waitForStart();
        while (opModeIsActive() && !isStopRequested())
        {
            if(gamepad1.dpad_up)
                linkage.runtTo(linkage.getPos()+20,0.3);
            if(gamepad1.dpad_down)
                linkage.runtTo(linkage.getPos()-20,0.3);

            telemetry.addData("das",linkage.getPos());
            telemetry.update();

        }
    }
}
