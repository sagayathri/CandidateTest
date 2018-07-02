package com.pavers.candidatetest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.pavers.candidatetest.Controller.UserController;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collectReferences();

        displayAllUsers();

    }


    private void collectReferences() {

        rvUsers = findViewById(R.id.userRecyler);


    }

    private void displayAllUsers() {
        new UserController(this, rvUsers);
    }




}
