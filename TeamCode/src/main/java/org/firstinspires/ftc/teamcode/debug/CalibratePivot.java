package org.firstinspires.ftc.teamcode.debug;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.Linkage;
import org.firstinspires.ftc.teamcode.subsystems.Pivot;

@TeleOp
public class CalibratePivot extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Pivot pivot= new Pivot(hardwareMap);
        Claw claw = new Claw(hardwareMap);
        Linkage linkage = new Linkage(hardwareMap);
        waitForStart();
        while(opModeIsActive() && !isStopRequested())
        {
            linkage.init();
            if(gamepad1.dpad_up)
                pivot.runtTo(pivot.getPos()+100,0.8);
            else if(gamepad1.dpad_down)
                pivot.runtTo(pivot.getPos()-100,0.8);
            else if(gamepad1.dpad_left)
                pivot.setParallelUp();
            else if(gamepad1.dpad_right)
                pivot.setParallelDown();
            telemetry.addData("das",pivot.getPos());
            telemetry.update();
        }
    }
}
