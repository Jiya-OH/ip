import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Storage {
    private Path pathFile;

    public Storage(String saveFileString) {
        this.pathFile = Paths.get(saveFileString);
    }


    public void saveList(TaskList tasks)  {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task t : tasks.getTaskList()) {
                sb.append(t.encodeTask()).append("\n");
            }
            Files.writeString(pathFile, sb.toString());
        } catch (IOException e){
            System.out.println("Error! Can't save task list to file");
        }
    }

    public List<Task> loadList() throws IOException{
        if (!Files.exists(pathFile)) {throw new FileNotFoundException();}
        List<Task> newList = new ArrayList<>();
        try (Stream<String> streamTask = Files.lines(pathFile)) {
            streamTask.forEach(line -> newList.add(Parser.parseTaskLine(line)));
        }

        return newList;
    }

}
