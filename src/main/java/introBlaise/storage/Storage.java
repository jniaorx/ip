package introBlaise.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/introBlaise.txt";

    public Storage() {
        createFile();
    }

    private void createFile() {
        try {
            Path dirPath = Paths.get(DIRECTORY_PATH);
            Path filePath = Paths.get(FILE_PATH);

            // Create directory if it doesn't exist
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Create file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public List<String> loadTasks() {
        List<String> tasks = new ArrayList<>();

        // Check if the file exists and is not empty
        try {
            Path filePath = Paths.get(FILE_PATH);
            if (Files.exists(filePath) && Files.size(filePath) > 0) {
                // Read from the file if it exists and is not empty
                try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        tasks.add(line);
                    }
                }
            } else {
                System.out.println("File is empty or does not exist. No tasks to load.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;
    }


    public void saveTasks(List<String> tasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String task : tasks) {
                bw.write(task);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void handleCorruptedFile() {
        System.out.println("Warning: Corrupted file detected. Resetting...");
        saveTasks(new ArrayList<>()); // Reset file with an empty task list
    }
}
