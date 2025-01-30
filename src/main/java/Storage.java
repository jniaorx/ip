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


    public Task stringToTask(String line) {
        try {
            String[] parts = line.split(" \\| ");
            if (parts.length < 4) { // Check if the parts array has enough data
                System.out.println("There is currently no tasks to be done.");
                return null;
            }

            String taskType = parts[0].trim();
            String isDone = parts[1].trim();
            String description = parts[2].trim();

            switch (taskType) {
            case "T":
                Task todo = new ToDo(description);
                if ("1".equals(isDone)) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                String deadlineDate = parts[3].trim();
                Task deadline = new Deadline(description, deadlineDate);
                if ("1".equals(isDone)) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                String eventDate = parts[3].trim();
                String[] eventDetails = eventDate.split(" to ");
                Task event = new Event(description, eventDetails[0], eventDetails[1]);
                if ("1".equals(isDone)) {
                    event.markAsDone();
                }
                return event;
            default:
                System.out.println("Skipping corrupted task line: " + line);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error parsing task line: " + e.getMessage());
            return null;
        }
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
