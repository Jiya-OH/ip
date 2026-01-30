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
    private Path pathFile;

    /**
     * Constructor for Storage that takes in a string value as file to be written to.
     * Creates a folder data within the same directory if it does not exist yet.
     * @param saveFileString name of the file to write to
     */
    public Storage(String saveFileString) {
        Path dataDirectory = Paths.get("data");
        try {
            Files.createDirectories(dataDirectory);
        } catch (IOException e) {
            System.err.println("Failed to create directory: " + e.getMessage());
        }
        this.pathFile = Paths.get("data/" + saveFileString);
    }

    /**
     * Takes in a list of tasks to encode and write into the save file.
     * Makes use of stringbuilder.
     * @param tasks list of tasks to write into the save file
     */
    public void saveList(TaskList tasks) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : tasks.getTaskList()) {
                sb.append(t.encodeTask()).append("\n");
            }
            Files.writeString(pathFile, sb.toString());
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
        if (!Files.exists(pathFile)) {
            throw new FileNotFoundException();
        }
        List<Task> newList = new ArrayList<>();
        try (Stream<String> streamTask = Files.lines(pathFile)) {
            streamTask.forEach(line -> newList.add(Parser.parseTaskLine(line)));
        }

        return newList;
    }

}
