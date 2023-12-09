package edu.hw9;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFileProcessor {
    private final String rootPath;

    public ParallelFileProcessor(String rootPath) {
        this.rootPath = rootPath;
    }

    public boolean algorithm1(int minCountFiles) {
        try (var forkJoinPool = new ForkJoinPool()) {
            var countFilesTask = new CountFilesTask(new File(rootPath));
            int countFiles = forkJoinPool.invoke(countFilesTask);
            return countFiles >= minCountFiles;
        } catch (Exception ignored) {
            return false;
        }
    }

    public List<String> algorithm2(String extension, long minSizeFiles) {
        try (var forkJoinPool = new ForkJoinPool()) {
            var searchFilesTask = new SearchFilesTask(new File(rootPath), extension, minSizeFiles);
            return forkJoinPool.invoke(searchFilesTask);
        } catch (Exception ignored) {
            return List.of();
        }
    }

    static class CountFilesTask extends RecursiveTask<Integer> {
        private final File directory;

        CountFilesTask(File directory) {
            this.directory = directory;
        }

        @Override
        protected Integer compute() {
            var files = directory.listFiles();

            if (files == null) {
                return 0;
            }

            List<CountFilesTask> subtasks = new ArrayList<>();

            int count = 0;
            for (var file : files) {
                if (file.isDirectory()) {
                    var subtask = new CountFilesTask(file);
                    subtasks.add(subtask);
                    subtask.fork();
                } else {
                    count++;
                }
            }

            for (CountFilesTask subtask : subtasks) {
                count += subtask.join();
            }

            return count;
        }
    }

    static class SearchFilesTask extends RecursiveTask<List<String>> {
        private final File directory;
        private final String extension;
        private final long minSizeFile;

        SearchFilesTask(File directory, String extension, long minSizeFile) {
            this.directory = directory;
            this.extension = extension;
            this.minSizeFile = minSizeFile;
        }

        @Override
        protected List<String> compute() {
            File[] files = directory.listFiles();
            if (files == null) {
                return new ArrayList<>();
            }

            List<SearchFilesTask> subtasks = new ArrayList<>();
            List<String> foundFiles = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    SearchFilesTask subtask = new SearchFilesTask(file, extension, minSizeFile);
                    subtasks.add(subtask);
                    subtask.fork();
                } else {
                    if (file.getName().endsWith(extension) && file.length() >= minSizeFile) {
                        foundFiles.add(file.getAbsolutePath());
                    }
                }
            }

            for (SearchFilesTask subtask : subtasks) {
                foundFiles.addAll(subtask.join());
            }

            return foundFiles;
        }
    }
}
