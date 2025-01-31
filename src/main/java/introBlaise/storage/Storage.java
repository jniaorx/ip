package introBlaise.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the storage operations for tasks, including creating, loading, saving, and handling corrupted files.
 * This class ensures that the necessary files and directories exist, and it provides functionality to read and
 * write tasks to a text file.
 */
public class Storage {
    // Path for the directory where tasks will be stored
    private static final String DIRECTORY_PATH = "data";

    // Path for the file containing tasks
    private static final String FILE_PATH = "data/introBlaise.txt";

    /**
     * Constructor that initializes the Storage object by ensuring the directory and file exist,
     * If they do not exist, it creates them.
     */
    public Storage() {
        createFile();
    }

    /**
     * Creates the necessary directory and file if they do not already exist.
     * The directory is created at "data" and the file is created at "data/introBlaise.txt".
     */
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

    /**
     * Loads the list of tasks from the file.
     * If the file exists an contains tasks, it reads each task and returns a list of strings.
     * If the file is empty or does not exist, an empty list is returned.
     *
     * @return A list of task descriptions read from the file.
     */
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

    /**
     * Saves the list of tasks to the file.
     * Each task is written on a new line in the file.
     *
     * @param tasks A list of task descriptions to save to the file.
     */
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

    /**
     * Handles a corrupted file by resetting the task list to an empty one.
     * This method is used to recover from a corrupted file and prevent further issues.
     */
    public void handleCorruptedFile() {
        System.out.println("Warning: Corrupted file detected. Resetting...");
        saveTasks(new ArrayList<>()); // Reset file with an empty task list
    }
}
