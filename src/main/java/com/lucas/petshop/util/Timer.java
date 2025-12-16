package com.lucas.petshop.util;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Timer {

    public static void measure(String task, long time) {
        Long newTime = System.currentTimeMillis() - time;
        log.info("{} Time: {} ms", task, newTime);
    }
}
