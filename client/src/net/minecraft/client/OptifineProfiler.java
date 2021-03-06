package net.minecraft.client;

import net.minecraft.client.renderer.G;
import net.minecraft.logging.Profiler;
import optifine.Config;
import optifine.Lagometer;

public class OptifineProfiler extends Profiler {

	private static final int HASH_SCHEDULED_EXECUTABLES = "scheduledExecutables".hashCode();
	private static final int HASH_TICK = "tick".hashCode();
	private static final int HASH_PRE_RENDER_ERRORS = "preRenderErrors".hashCode();

	public void startSection(String name) {
		if (Lagometer.isActive()) {
			int i = name.hashCode();

			if (i == HASH_SCHEDULED_EXECUTABLES && name.equals("scheduledExecutables")) {
				Lagometer.timerScheduledExecutables.start();
			} else if (i == HASH_TICK && name.equals("tick") && Config.isMinecraftThread()) {
				Lagometer.timerScheduledExecutables.end();
				Lagometer.timerTick.start();
			} else if (i == HASH_PRE_RENDER_ERRORS && name.equals("preRenderErrors")) {
				Lagometer.timerTick.end();
			}
		}

		super.startSection(name);
	}

}
