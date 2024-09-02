package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.common.AutoUtil.*;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.VoltageSensor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.common.ActionQueue;
import org.firstinspires.ftc.teamcode.common.Log;
import org.firstinspires.ftc.teamcode.enums.AutoStartPos;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.tasks.Task;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public abstract class AutoBase extends LinearOpMode {
    public static State state = State.DEFAULT;
    static AutoBase instance = null;
    public AutoStartPos startPos = AutoStartPos.UNKNOWN;
    public OpenCvCamera camera;

    public MecanumDrive drive;
    public Task task;

    public double extendoPower, liftPower, hangPower;

    public ActionQueue actionQueue = new ActionQueue();

    public boolean full = true;

    public static AutoBase getInstance() {
        return instance;
    }

    double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }

        return result;
    }

    public void onInit() {
    }

    public void onInitTick() {
    }

    public void onStart() throws InterruptedException {
    }

    public void onStartTick() {
    }

    @Override
    public void runOpMode() throws InterruptedException {
        Log log = new Log(this.telemetry);
        instance = this;

        drive = new MecanumDrive(this.hardwareMap, p(0, 0, 0));

        onInit();

//        if (startPos == AutoStartPos.UNKNOWN) {
//            log.add("Auto starting position not set.");
//            log.tick();
//            return;
//        }

        state = State.INIT;
        enableVision();
        while (!isStarted() && !isStopRequested()) {
            log.add("A/X for Full");
            log.add("B/O for Detection");
            if (gamepad1.a) {
                full = true;
            }
            if (gamepad1.b) {
                full = false;
            }
            if (full) {
                log.add("Running Full");
            } else {
                log.add("Detection Only");
            }

            onInitTick();
            log.tick();
        }
        log.tick();

//        camera.closeCameraDeviceAsync(() -> {});
        onStart();

        state = State.START;
        if (task != null) task.start(this);

        while (opModeIsActive() && !isStopRequested()) {
            if (task != null) task.tick();
            onStartTick();

            log.tick();
            actionQueue.tick();
        }

    }

    public void enableVision() {
//        pipeline = new TSEDetectionPipeline(startPos);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        telemetry.addData("camera ", cameraMonitorViewId);
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

//        camera.setPipeline(pipeline);

        try {

            camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    camera.startStreaming(640, 360, OpenCvCameraRotation.UPRIGHT);
                }

                @Override
                public void onError(int errorCode) {
                    /*
                     * This will be called if the camera could not be opened
                     */
                }
            });
        } catch (Exception e) {

        }

    }

    public enum State {
        DEFAULT,
        INIT,
        START
    }
}
