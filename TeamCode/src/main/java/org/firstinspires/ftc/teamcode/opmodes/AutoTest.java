package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import static org.firstinspires.ftc.teamcode.common.AutoUtil.*;
import static org.firstinspires.ftc.teamcode.tasks.TaskBuilder.*;

@Autonomous(name = "Auto Test")
public class AutoTest extends AutoBase {
    @Override
    public void onInit() {
        TrajectoryActionBuilder testTrajectoryBuilder = drive.actionBuilder(drive.pose)
                .splineToSplineHeading(p(20, 20, Math.PI / 2), 0)
                .strafeToLinearHeading(v(40, 20),Math.PI / 2);

        TrajectoryActionBuilder testTrajectory2Builder = testTrajectoryBuilder.fresh()
                .strafeTo(v(20, 20));



        Action testTrajectory = testTrajectoryBuilder.build();
        Action testTrajectory2 = testTrajectory2Builder.build();

        Actions.runBlocking(new SequentialAction(
                testTrajectoryBuilder.build(),
                testTrajectory2Builder.build()
        ));
        task = serial(
                action(testTrajectory),
                action(testTrajectory2)
        );
    }
}
