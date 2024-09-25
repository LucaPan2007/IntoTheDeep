package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class Pivot {
    public static int COLLECT = 0, DROP = 4800;//change drop value

    public DcMotor pivot;
    public Servo wrist;

    public static double PARALLEL_DOWN = 0.45;
    public static double  PARALLEL_UP = 0.8;//change


    public Pivot(HardwareMap hardwareMap) throws InterruptedException {
        pivot= hardwareMap.get(DcMotorEx.class,"motorPivot");
        wrist = hardwareMap.get(Servo.class,"servoArm");//change

        pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //pivot.setDirection(DcMotorSimple.Direction.REVERSE); eventually
        pivot.setTargetPosition(COLLECT);
        pivot.setPower(1);
        pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        wrist.setPosition(PARALLEL_DOWN);//PARDOWN

    }

    public void collect() throws InterruptedException {

        pivot.setTargetPosition(COLLECT);
        pivot.setPower(-1);
        pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void drop() throws InterruptedException {

        pivot.setTargetPosition(DROP);
        pivot.setPower(1);
        pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }

    public void runtTo(int pos,double pow)
    {
        pivot.setTargetPosition(pos);
        if(pos<pivot.getCurrentPosition())
            pow=-pow;
        pivot.setPower(pow);
        pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    }
    public int getPos()
    {
        return pivot.getCurrentPosition();
    }

    public void setParallelDown() {wrist.setPosition(PARALLEL_DOWN);}

    public void setParallelUp() {wrist.setPosition(PARALLEL_UP);}




    //ACTIONS
    public class PivotDown implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            pivot.setTargetPosition(COLLECT);
            pivot.setPower(-1);
            pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            return false;
        }
    }


    public class PivotUp implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            pivot.setTargetPosition(DROP);
            pivot.setPower(1);
            pivot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            return false;
        }
    }



    public Action down(){return new PivotDown();}
    public Action up() {return new PivotUp();}
}
