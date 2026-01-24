package roberto;

import task.Task;

import java.util.*;

public class TaskList {
    private List<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList){
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getSize(){
        return taskList.size();
    }

    //Adds input line to list
    public void addToList(Task newTask) {
        taskList.add(newTask);
    }

    public void deleteTask(Task task){
        taskList.remove(task);
    }

    public void markTask(Task task){
        task.markAsDone(true);
    }

    public void unmarkTask(Task task){
        task.markAsDone(false);
    }


}
