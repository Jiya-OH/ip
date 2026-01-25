package roberto;

import exceptions.TaskDoesNotExistException;
import task.Task;

import java.util.*;

public class TaskList {
    private List<Task> taskList;


    /**
     * Simple constructor for TaskList
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Simple constructor for TaskList that takes in List</Task>
     * @param taskList list of tasks to be added in
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets private field taskList
     * @return Current instance's list of task
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns size of private field taskList
     * @return Current instance's list of task size in integer
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Adds a new task to the private field taskList
     * @param newTask task to be added
     */
    public void addToList(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Removes a task from the private field list if it exists
     * @param task task to be deleted
     */
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
