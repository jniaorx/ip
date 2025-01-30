import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class SaveData {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/introBlaise.txt";

    public SaveData() {
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

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                tasks.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return tasks;

    }

    private static Task stringToTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0].trim();
        String isDone = parts[1].trim();
        String description = parts[2].trim();
        String dates = parts[3].trim();

        switch (taskType) {
        case "T":
            Task todo = new ToDo(description);
            if ("1".equals(isDone)) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            Task deadline = new Deadline(description, dates);
            if ("1".equals(isDone)) {
                deadline.markAsDone();
            }
            return deadline;
        case "E":
            String[] eventDetails = dates.split(" to ");
            Task event = new Event(description, eventDetails[0], eventDetails[1]);
            if ("1".equals(isDone)) {
                event.markAsDone();
            }
            return event;
        default:
            System.out.println("Corrupted task format: " + line);
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
