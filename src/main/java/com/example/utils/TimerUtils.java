package com.example.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerUtils {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final int DELAY = 10;//设置时间长短


    public static void scheduleTask(Runnable task) {

        /**
         * TimeUnit.MINUTES 是单位分钟
         */
        scheduler.schedule(task, DELAY, TimeUnit.MINUTES);
    }
}
