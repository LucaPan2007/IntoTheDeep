package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

@TeleOp(name = "TeleopEvent")
public class SeqTesting extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Linkage linkage = new Linkage(hardwareMap);
        Pivot pivot = new Pivot(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        claw.open();
        waitForStart();

        while(opModeIsActive() && !isStopRequested())
        {
            if(gamepad1.dpad_up)
            {
                linkage.init();
                pivot.drop();
                sleep(1000);
                pivot.setParallelUp();
                sleep(1000);
                linkage.extended();

            }
            if(gamepad1.dpad_down)
            {
                linkage.init();
                sleep(500);
                pivot.setParallelDown();
                pivot.collect();
                sleep(3000);
                claw.close();
            }
        }
    }
}
