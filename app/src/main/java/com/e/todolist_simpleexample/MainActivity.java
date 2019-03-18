package com.e.todolist_simpleexample;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {
    private ArrayList<ToDoItem> toDoList = new ArrayList<>();
    private ToDoAdapter tdAdapter;
    private ListView toDoListView;
    private Date DATATMP = new Date(); //tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set toDoList
        readFile("toDoList");
        toDoListView = (ListView) findViewById(R.id.ToDoListView);
        tdAdapter = new ToDoAdapter(this, toDoList);
        toDoListView.setAdapter(tdAdapter);
        // setupListViewListener(); //todo removeListener
    }

    public void onAddToDo(View view){
        EditText addToDoEditText = findViewById(R.id.addToDoEditText);
        String text = addToDoEditText.getText().toString();
        tdAdapter.add(new ToDoItem(text, "deadline", false));
        addToDoEditText.setText("");
        writeFile("toDoList");
    }

    /*
    private void setupListViewListener() {
        toDoListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                        toDoList.remove(pos);
                        toDoListAdapter.notifyDataSetChanged();
                        writeFile("toDoList");
                        return true;
                    }
                });
    }
    */

    //todo
    void writeFile(String nameOfList) {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(nameOfList, Context.MODE_PRIVATE)))) {
            for (ToDoItem item : toDoList){
                bw.write(item.name + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //todo
    void readFile(String nameOfList) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(nameOfList)))) {
            String str;
            while((str = br.readLine()) != null) {
                toDoList.add(new ToDoItem(str, "deadline", false));
            }
        } catch (FileNotFoundException e) {
            toDoList = new ArrayList<>();
        } catch (IOException e) {
            toDoList = new ArrayList<>();
        }
    }
}
