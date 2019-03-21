package com.e.todolist_simpleexample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends Activity {
    private ArrayList<ToDoItem> toDoList = new ArrayList<>();
    private ToDoAdapter tdAdapter;
    private RecyclerView recyclerView;
    private EditText addToDoEditText;
    private Date DATATMP = new Date(); //todo tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set toDoList
        readFile("toDoList");
        recyclerView = findViewById(R.id.rvToDoList);
        tdAdapter = new ToDoAdapter(this, toDoList);
        recyclerView.setAdapter(tdAdapter); //todo fix it
        //todo extract in them
        getWindow().setStatusBarColor(Color.BLACK);
        //setupListViewListener(); //todo removeListener
    }

    /*fun for buttons

    public void onAdd(View view){
        addToDoEditText = findViewById(R.id.etTaskName);
        String text = addToDoEditText.getText().toString();
        tdAdapter.add(new ToDoItem(text, "deadline", false));
        addToDoEditText.setText("");
        writeFile("toDoList");
        tdAdapter.notifyDataSetChanged();
    }

    public void onRemove(View view){
        for (ToDoItem item : toDoList){
            if (item.isDone) toDoList.remove(item);
        }
        writeFile("toDoList");
        tdAdapter.notifyDataSetChanged();
    }

    public void onDone(View view){
        ArrayList<ToDoItem> doneList = getSelected();
        StringBuffer str = new StringBuffer("Done: \n");
        for (ToDoItem item : doneList) {
            str.append(item.name.toString());
            str.append("\n");
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    */
    /*todo test it
    private void setupListViewListener() {
        recyclerView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapter, View view, int pos, long id) {
                        getDoneMessage();
                        toDoList.remove(pos);
                        tdAdapter.notifyDataSetChanged();
                        writeFile("toDoList");
                        return true;
                    }
                });
    }
    */

    //save and load date
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

    //get selected list
    ArrayList<ToDoItem> getSelected(){
        ArrayList<ToDoItem> res = new ArrayList<>();
        for(ToDoItem item : toDoList){
            if(item.isDone) res.add(item);
        }
        return res;
    }

    //message
    void getDoneMessage(){
        String res = null;
        for(ToDoItem item : toDoList){
            if(item.isDone) res += (item.name + "\n");
        }
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }
}
