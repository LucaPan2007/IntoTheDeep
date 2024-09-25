package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TrajectoryBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.teamcode.common.AutoUtil.*;
import static org.firstinspires.ftc.teamcode.tasks.TaskBuilder.*;

@Autonomous(name = "Auto Test")
public class AutoTest extends AutoBase {
    @Override
    public void onInit() throws InterruptedException {
        Pose2d startingPose = new Pose2d(-14, -62, Math.toRadians(90)); // Example starting pose at (0, 0) with a heading of 90 degrees
        drive.pose=startingPose;

        TrajectoryActionBuilder mainTrajectoryBuilder = drive.actionBuilder(drive.pose)
                .afterTime(0.1,(pivot.up()))
                .waitSeconds(10000)
                .splineToConstantHeading(v(-3,-35),rad(90))
                .waitSeconds(1)
                .strafeTo(v(-3,-40))
                .strafeTo(v(-52,-40))
                .strafeToLinearHeading(v(-58,-54),rad(-155))
                .strafeToLinearHeading(v(-56,-40),rad(90))
                .strafeToLinearHeading(v(-58,-54),rad(-155))
                .strafeToLinearHeading(v(-58,-28),rad(160))
                .strafeToLinearHeading(v(-58,-54),rad(-155))
                .strafeTo(new Vector2d(-32,0))
                .strafeTo(new Vector2d(-25,0));



        Action testTrajectory = mainTrajectoryBuilder.build();
        Actions.runBlocking(
                new SequentialAction(
                      //  pivot.up()
                        /*
                        pivot.drop(),
                        (Action) sleepms(2000),
                        pivot.setParallelUp(),
                        (Action) sleepms(10000)

                         */

                )
        );
      // Actions.runBlocking(
        //       new SequentialAction(
         //       testTrajectoryBuilder.build()));
               // testTrajectory2Builder.build()
        //));

        task = serial(
                action(testTrajectory)

        );

    }
}
