package org.firstinspires.ftc.teamcode.tasks;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.canvas.Canvas;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.opmodes.AutoBase;

public class ActionTask extends Task {
    Action action;
    Canvas canvas;
    boolean b = true;

    public ActionTask(Action action) {
        this.action = action;
    }

    @Override
    public void run() {
        super.run();
        canvas = new Canvas();
        action.preview(canvas);
    }

    @Override
    public void tick() {
        super.tick();

        if (!b) {
            state = State.FINISHED;
            return;
        }

        TelemetryPacket packet = new TelemetryPacket();
        packet.fieldOverlay().getOperations().addAll(canvas.getOperations());

        b = action.run(packet);

        FtcDashboard.getInstance().sendTelemetryPacket(packet);
    }
}
