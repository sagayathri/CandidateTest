package com.pavers.candidatetest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pavers.candidatetest.Modals.UserModal;

import java.sql.DataTruncation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactDetails extends AppCompatActivity {

    public ImageView userImagesrc;
    public TextView userName, userpermissionlevel, userTeam, userGender, userStartDate, userEndDate, userActiveState, userPayGrade;
    UserModal userModal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userName =(TextView) findViewById(R.id.username);
        userpermissionlevel =(TextView) findViewById(R.id.permissionlevel);
        userTeam =(TextView) findViewById(R.id.userteam);
        userGender =(TextView) findViewById(R.id.usergender);
        userStartDate =(TextView) findViewById(R.id.startDate);
        userEndDate =(TextView) findViewById(R.id.endDate);
        userActiveState =(TextView) findViewById(R.id.isActive);
        userPayGrade =(TextView) findViewById(R.id.payGrade);

       Intent intent = getIntent();
       userModal = (UserModal) intent.getSerializableExtra("currentUser");
       getUserDetails(userModal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit: {
                Intent intent = new Intent(this, EditContactDetails.class);
                intent.putExtra("editUser",userModal);
                startActivity(intent);
            }
            break;
            default:
                break;
        }
        return true;
    }

    public void getUserDetails(UserModal currentUser){
        Long start_date = Long.valueOf(String.valueOf(currentUser.getUserInfoModal().getUserStartDate()));
        Long end_date = Long.valueOf(String.valueOf(currentUser.getUserInfoModal().getUserLeaveDate()));

        String endDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String startDate = dateFormat.format(new Date((start_date)*1000L));
        if(end_date != 0){
           endDate = dateFormat.format(new Date((end_date)*1000));
        }else {
            endDate = "N/A";
        }
        userName.setText(currentUser.getUserHeaderModal().getUserName().toString());
        userpermissionlevel.setText(String.valueOf(currentUser.getUserHeaderModal().getUserPermissionLevel()));
        userTeam.setText(currentUser.getUserHeaderModal().getUserTeam().toString());
        userGender.setText(currentUser.getUserHeaderModal().getUserSex().toString());
        userStartDate.setText(startDate.toString());
        userEndDate.setText(endDate.toString());
        userActiveState.setText(String.valueOf(currentUser.getUserInfoModal().getIsActive()));
        userPayGrade.setText(String.valueOf(currentUser.getUserInfoModal().getPayGrade()));
    }
}