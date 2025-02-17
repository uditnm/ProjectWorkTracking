package com.example.ProjectWorkTracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.Gson;

public class AddNewProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_project);
        Intent intent = getIntent();
    }
    public void addProject(View view) {
        EditText name = findViewById(R.id.editTextPname);
        EditText description = findViewById(R.id.editTextPdescription);
        EditText startDate = findViewById(R.id.editTextStartDate);
        EditText endDate = findViewById(R.id.editTextEndDate);
        EditText manager = findViewById(R.id.editTextTextPmanager);
        EditText budget = findViewById(R.id.editTextBudget);
        Spinner priority  = findViewById(R.id.spinner);
        CheckBox tech = findViewById(R.id.checkBox);
        CheckBox finance = findViewById(R.id.checkBox2);
        CheckBox marketing = findViewById(R.id.checkBox3);
        RadioGroup status = findViewById(R.id.radioGroupStatus);
        Boolean[] teams = new Boolean[3];
        teams[0] = tech.isChecked();
        teams[1] = finance.isChecked();
        teams[2] = marketing.isChecked();
        Boolean status1= false;
        if(status.getCheckedRadioButtonId() == R.id.radioButton2)
            status1 = true;
        int priority1;
        if(priority.getSelectedItem().toString().equals("High"))
            priority1=3;
        else if(priority.getSelectedItem().toString().equals("Medium"))
            priority1=2;
        else if(priority.getSelectedItem().toString().equals("Low"))
            priority1=1;
        else
            priority1=0;
        Project project = new Project(name.getText().toString(), description.getText().toString(), startDate.getText().toString(),
                endDate.getText().toString(), manager.getText().toString(),Integer.parseInt(budget.getText().toString()),
                priority1, teams, status1);
        //System.out.println(project.toString());
        MainActivity.Projects.add(project);
        System.out.println(MainActivity.Projects.toString());
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(MainActivity.Projects);
        editor.putString("task list", json);
        editor.apply();
    }

    public void savebtn(View view) {
        saveData();
    }
}