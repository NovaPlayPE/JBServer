/*
 * This file is licensed under the MIT License (MIT).
 *
 * Copyright (c) 2014 Daniel Ennis <http://aikar.co>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package co.aikar.timings;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.lang.management.ManagementFactory;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import net.novatech.jbserver.player.Player;
import net.novatech.jbserver.server.Server;
import net.novatech.jbserver.utils.Utils;

import static co.aikar.timings.Timings.fullServerTickTimer;
import static co.aikar.timings.TimingsManager.MINUTE_REPORTS;

public class TimingsHistory {
	public static long lastMinuteTime;
	public static long timedTicks;
	public static long playerTicks;
	public static long entityTicks;
	public static long tileEntityTicks;
	public static long activatedEntityTicks;

	private final long endTime;
	private final long startTime;
	private final long totalTicks;
	// Represents all time spent running the server this history
	private final long totalTime;
	private final MinuteReport[] minuteReports;

	private final TimingsHistoryEntry[] entries;

	TimingsHistory() {
		this.endTime = System.currentTimeMillis() / 1000;
		this.startTime = TimingsManager.historyStart / 1000;

		if (timedTicks % 1200 != 0 || MINUTE_REPORTS.isEmpty()) {
			this.minuteReports = MINUTE_REPORTS.toArray(new MinuteReport[MINUTE_REPORTS.size() + 1]);
			this.minuteReports[this.minuteReports.length - 1] = new MinuteReport();
		} else {
			this.minuteReports = MINUTE_REPORTS.toArray(new MinuteReport[MINUTE_REPORTS.size()]);
		}

		long ticks = 0;
		for (MinuteReport mr : this.minuteReports) {
			ticks += mr.ticksRecord.timed;
		}

		this.totalTicks = ticks;
		this.totalTime = fullServerTickTimer.record.totalTime;
		this.entries = new TimingsHistoryEntry[TimingsManager.TIMINGS.size()];

		int i = 0;
		for (Timing timing : TimingsManager.TIMINGS) {
			this.entries[i++] = new TimingsHistoryEntry(timing);
		}

		final Map<Integer, AtomicInteger> entityCounts = new HashMap<>();
		final Map<Integer, AtomicInteger> blockEntityCounts = new HashMap<>();
		final Gson GSON = new Gson();
		Server.getInstance().getFactoryManager().getWorldFactory().worlds.forEach((s, world) -> {
			JsonArray jsonWorld = new JsonArray();
			world.getChunkManager().getChunkCache().cachedChunks.forEach(chunk -> {
				JsonArray jsonChunk = new JsonArray();
				jsonChunk.add(chunk.getVector().getX());
				jsonChunk.add(chunk.getVector().getZ());
				
				jsonWorld.add(jsonChunk);
			});
		});
	}

	static void resetTicks(boolean fullReset) {
		if (fullReset) {
			timedTicks = 0;
		}
		lastMinuteTime = System.nanoTime();
		playerTicks = 0;
		tileEntityTicks = 0;
		entityTicks = 0;
		activatedEntityTicks = 0;
	}

	JsonObject export() {
		JsonObject json = new JsonObject();
		json.addProperty("s", this.startTime);
		json.addProperty("e", this.endTime);
		json.addProperty("tk", this.totalTicks);
		json.addProperty("tm", this.totalTime);
//        json.add("w", this.levels);
		json.add("h", Utils.mapToArray(this.entries, (entry) -> {
			if (entry.data.count == 0) {
				return null;
			}
			return entry.export();
		}));
		json.add("mp", Utils.mapToArray(this.minuteReports, MinuteReport::export));
		return json;
	}

	static class MinuteReport {
		final long time = System.currentTimeMillis() / 1000;

		final TicksRecord ticksRecord = new TicksRecord();
		final PingRecord pingRecord = new PingRecord();
		final TimingData fst = Timings.fullServerTickTimer.minuteData.clone();
		final double tps = 1E9 / (System.nanoTime() - lastMinuteTime) * this.ticksRecord.timed;
		final double usedMemory = Timings.fullServerTickTimer.avgUsedMemory;
		final double freeMemory = Timings.fullServerTickTimer.avgFreeMemory;
		final double loadAvg = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();

		JsonArray export() {
			return Utils.toArray(this.time, Math.round(this.tps * 100D) / 100D,
					Math.round(this.pingRecord.avg * 100D) / 100D, this.fst.export(),
					Utils.toArray(this.ticksRecord.timed, this.ticksRecord.player, this.ticksRecord.entity,
							this.ticksRecord.activatedEntity, this.ticksRecord.tileEntity),
					this.usedMemory, this.freeMemory, this.loadAvg);
		}
	}

	private static class TicksRecord {
		final long timed;
		final long player;
		final long entity;
		final long activatedEntity;
		final long tileEntity;

		TicksRecord() {
			this.timed = timedTicks - (TimingsManager.MINUTE_REPORTS.size() * 1200);
			this.player = playerTicks;
			this.entity = entityTicks;
			this.activatedEntity = activatedEntityTicks;
			this.tileEntity = tileEntityTicks;
		}
	}

	private static class PingRecord {
		final double avg;

		PingRecord() {
			HashMap<UUID, Player> players = Server.getInstance().getFactoryManager().getPlayerFactory().getOnlinePlayers();
			int totalPing = 0;
			for (Player player : players.values()) {
//                totalPing += player.getPing();
			}
			this.avg = players.isEmpty() ? 0 : totalPing / players.size();
		}
	}
}