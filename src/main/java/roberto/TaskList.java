package roberto;

import exceptions.TaskDoesNotExistException;
import task.Task;

import java.util.*;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getSize() {
        return taskList.size();
    }

    //Adds input line to list
    public void addToList(Task newTask) {
        taskList.add(newTask);
    }

    public void deleteTask(Task task) {
        boolean removeBool = taskList.remove(task);
        if (!removeBool) {
            throw new TaskDoesNotExistException(0);
        }
    }

    public void markTask(Task task) {
        task.markAsDone(true);
    }

    public void unmarkTask(Task task) {
        task.markAsDone(false);
    }


}
