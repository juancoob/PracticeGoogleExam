package com.juancoob.practicegoogleexam.util;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by Juan Antonio Cobos Obrero on 3/10/18.
 */
public class AppExecutors {
    private static final AppExecutors appExecutors = new AppExecutors();

    public static AppExecutors getInstance() {
        return appExecutors;
    }

    private AppExecutors() {
    }

    private final Executor mDiskIO = Executors.newSingleThreadExecutor();

    public Executor getDiskIO() {
        return mDiskIO;
    }
}
