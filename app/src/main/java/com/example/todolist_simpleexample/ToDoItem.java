package com.example.todolist_simpleexample;

public class ToDoItem {
    String name;
    String deadline;
    Boolean isDone;

    ToDoItem(String name, String deadline, Boolean isDone) {
        this.name = name;
        this.deadline = deadline;
        this.isDone = isDone;
    }
}
