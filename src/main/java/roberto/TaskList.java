package roberto;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import exceptions.TaskDoesNotExistException;
import task.Task;


/**
 * public class TaskList to store list of tasks
 */
public class TaskList {
    private List<Task> tasks;
    private Stack<List<Task>> historyOfTasks;


    /**
     * Simple constructor for TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.historyOfTasks = new Stack<>();
        historyOfTasks.push(tasks);
    }

    /**
     * Simple constructor for TaskList that takes in List of tasks
     * @param tasks list of tasks to be added in
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
        this.historyOfTasks = new Stack<>();
        historyOfTasks.push(tasks);
    }

    /**
     * Gets private field taskList
     * @return Current instance's list of task
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns size of private field taskList
     * @return Current instance's list of task size in integer
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Adds a new task to the private field taskList
     * @param newTask task to be added
     */
    public void addToList(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a task from the private field list if it exists
     * @param task task to be deleted
     */
    public void deleteTask(Task task) {
        boolean removeBool = tasks.remove(task);
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

    /**
     * Pops the list of tasks from its previous state
     */
    public void undoTaskFromHistory() {
        if (!historyOfTasks.isEmpty()) {
            tasks = historyOfTasks.pop();
        }
    }

    /**
     * Pushes the current state of task list to history
     */
    public void saveTaskToHistory() {
        List<Task> snapshotTasks = new ArrayList<>();
        for (Task task : tasks) {
            snapshotTasks.add(task.copy());
        }
        historyOfTasks.push(snapshotTasks);
    }
}

