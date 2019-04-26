package com.example.todolist_simpleexample;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {
    private ArrayList<ToDoItem> toDoList = new ArrayList<>();
    private ToDoAdapter tdAdapter;
    private ListView listView;
    private EditText addToDoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set toDoList
        readFile("toDoList");
        listView = findViewById(R.id.lView);
        tdAdapter = new ToDoAdapter(this, toDoList);
        listView.setAdapter(tdAdapter);
        getWindow().setStatusBarColor(Color.BLACK);
    }

    //fun for buttons
    public void onAdd(View view) {
        addToDoEditText = findViewById(R.id.etTaskName);
        String text = addToDoEditText.getText().toString();
        toDoList.add(new ToDoItem(text, getTime(), false));
        addToDoEditText.setText("");
        writeFile("toDoList");
        tdAdapter.notifyDataSetChanged();
    }

    public void onRemove(View view) {
        toDoList.removeAll(getSelected());
        writeFile("toDoList");
        tdAdapter.notifyDataSetChanged();
    }

    public void onDone(View view) {
        ArrayList<ToDoItem> doneList = getSelected();
        StringBuffer str = new StringBuffer("Done: \n");
        for (ToDoItem item : doneList) {
            str.append(item.name);
            str.append("\n");
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    //save and load date
    void writeFile(String nameOfList) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(nameOfList, Context.MODE_PRIVATE)))) {
            for (ToDoItem item : toDoList) {
                bw.write(item.name + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void readFile(String nameOfList) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(nameOfList)))) {
            String str;
            while ((str = br.readLine()) != null) {
                toDoList.add(new ToDoItem(str, "deadline", false));
            }
        } catch (FileNotFoundException e) {
            toDoList = new ArrayList<>();
        } catch (IOException e) {
            toDoList = new ArrayList<>();
        }
    }

    //get selected list
    ArrayList<ToDoItem> getSelected() {
        ArrayList<ToDoItem> res = new ArrayList<>();
        for (ToDoItem item : toDoList) {
            if (item.isDone) res.add(item);
        }
        return res;
    }

    String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
