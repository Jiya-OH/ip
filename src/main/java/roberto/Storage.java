package roberto;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import task.Task;


/**
 * Public class storage to store task lists into file
 */
public class Storage {
    private static final String DATA_DIRECTORY = "data";
    private Path pathFile;

    /**
     * Constructor for Storage that takes in a string value as file to be written to.
     * Creates a folder data within the same directory if it does not exist yet.
     * @param saveFileString name of the file to write to
     */
    public Storage(String saveFileString) {
        assert saveFileString != null : "saveFileString must not be null";
        assert !saveFileString.isEmpty() : "saveFileString must not be empty";
        createDataDirectory();
        this.pathFile = Paths.get(DATA_DIRECTORY, saveFileString);
        assert pathFile != null : "pathFile must be initialized";
    }

    /**
     * Takes in a list of tasks to encode and write into the save file.
     * Makes use of stringbuilder.
     * @param tasks list of tasks to write into the save file
     */
    public void saveList(TaskList tasks) {
        assert tasks != null : "tasks must not be null";
        assert tasks.getTaskList() != null : "task list must not be null";
        assert pathFile != null : "pathFile must be initialized";

        String saveContent = encodeTasks(tasks.getTaskList());
        try {
            Files.writeString(pathFile, saveContent);
        } catch (IOException e) {
            System.out.println("Error! Can't save task list to file");
        }
    }

    /**
     * Reads strings from the save file and parses them into a list of task
     * @return List of tasks
     * @throws IOException
     */
    public List<Task> loadList() throws IOException {
        assert pathFile != null : "pathFile must be initialized";
        if (!Files.exists(pathFile)) {
            throw new FileNotFoundException();
        }
        List<Task> tasks = new ArrayList<>();
        try (Stream<String> streamTask = Files.lines(pathFile)) {
            streamTask.forEach(line -> {
                assert line != null : "line read from file must not be null";
                Task task = Parser.parseTaskLine(line);
                assert task != null : "parsed task must not be null";
                tasks.add(task);
            });
        }

        return tasks;
    }

    /**
     * Create data directory named "data" if it is missing.
     */
    private void createDataDirectory() {
        try {
            Files.createDirectories(Paths.get(DATA_DIRECTORY));
        } catch (IOException e) {
            System.err.println("Failed to create data directory: " + e.getMessage());
        }
    }

    /**
     * Creates encoded string based on the list of tasks to save to file
     * @param tasks list of tasks to encode
     * @return encoded string
     */
    private String encodeTasks(List<Task> tasks) {
        StringBuilder builder = new StringBuilder();

        for (Task task : tasks) {
            assert task != null : "task must not be null";
            builder.append(task.encodeTask()).append(System.lineSeparator());
        }

        return builder.toString();
    }
}
