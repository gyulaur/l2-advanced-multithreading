package com.company.advanced.multithreading.task4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ForkJoinPoolMain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForkJoinPoolMain.class);

    private static final AtomicInteger folderCount = new AtomicInteger(0);
    private static final AtomicInteger fileCount = new AtomicInteger(0);
    private static final AtomicLong fileSize = new AtomicLong(0);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Path path = Path.of("d:\\Apps\\exercism\\");
        Path path = Path.of("d:\\EPAM\\L2-mentoring\\\\");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<?> submit = executorService.submit(new ScannerRunnable(path));

        LOGGER.info("Executor started");
        submit.get();
        executorService.shutdown();
        LOGGER.info("Executor stoped");
        LOGGER.info("Main stoped");

        LOGGER.info("Folder Count: {}; File Count: {}; All File size: {} kb", folderCount, fileCount, (fileSize.doubleValue() / 1000));

    }

    public static class ScannerRunnable implements Runnable {

        private final Path path;

        public ScannerRunnable(Path path) {
            this.path = path;
        }

        @Override
        public void run() {
            LOGGER.info("Scanner started");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int numWorkers = Runtime.getRuntime().availableProcessors();
            ForkJoinPool pool = new ForkJoinPool(numWorkers);
            pool.invoke(new FolderTask(path, path.toFile().listFiles(File::isDirectory)));
            LOGGER.info("Scanner stoped");
        }
    }

    public static class FolderTask extends RecursiveAction {

        private final Path path;
        private final File[] folders;

        public FolderTask(Path path, File[] folders) {
            this.path = path;
            this.folders = folders;
        }

        @Override
        protected void compute() {
            LOGGER.info("{} Folder count: {}", path.getFileName(), folders.length);
            folderCount.addAndGet(folders.length);
            List<RecursiveAction> tasks = new ArrayList<>();
            File[] files = path.toFile().listFiles(File::isFile);
            if (folders.length > 0) {
                tasks = Arrays.stream(folders)
                        .map(directory -> new FolderTask(Path.of(directory.getPath()), directory.listFiles(File::isDirectory)))
                        .collect(Collectors.toList());
                tasks.add(new FileTask(files));
            } else if (files != null && files.length > 0) {
                tasks.add(new FileTask(files));
            }
            invokeAll(tasks);
        }
    }

    public static class FileTask extends RecursiveAction {

        private final File[] files;

        public FileTask(File[] files) {
            this.files = files;
        }

        @Override
        protected void compute() {
            LOGGER.info("Files Count {}", files.length);
            fileCount.addAndGet(files.length);
            var allFileSize = Arrays.stream(files)
                    .map(file -> {
                        long filesize = getFilesize(file);
                        LOGGER.info("{} File size: {}", file.getName(), filesize);
                        return filesize;
                    })
                    .reduce(0L, Long::sum);
            fileSize.addAndGet(allFileSize);
        }

        private long getFilesize(File file) {
            try {
                return Files.size(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0L;
        }
    }
}
