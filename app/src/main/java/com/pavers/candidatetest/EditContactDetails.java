package com.pavers.candidatetest;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.pavers.candidatetest.API.API_Database;
import com.pavers.candidatetest.Controller.UserController;
import com.pavers.candidatetest.Modals.APIResponseModal;
import com.pavers.candidatetest.Modals.UpdateUserModal;
import com.pavers.candidatetest.Modals.UserModal;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class EditContactDetails extends AppCompatActivity {

    TextView userName, userActiveState;
    Spinner userpermissionlevel, userTeam, userPayGrade;
    EditText userendDate;
    ImageView imageView;
    UserModal userModal;
    API_Database api_database;
    String[] user_Team = {"Picking", "Packing", "Stock Control"};
    UserController userController = new UserController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_details);

        userName =(TextView) findViewById(R.id.username);
        userActiveState =(TextView) findViewById(R.id.isActive);
        userpermissionlevel =(Spinner) findViewById(R.id.permissionlevel);
        userTeam =(Spinner) findViewById(R.id.userteam);
        userPayGrade =(Spinner) findViewById(R.id.payGrade);
        userendDate = (EditText)findViewById(R.id.endDate);
        imageView = (ImageView)findViewById(R.id.imageView);

        Intent intent = getIntent();
        userModal = (UserModal) intent.getSerializableExtra("editUser");
        userName.setText(userModal.getUserHeaderModal().getUserName().toString());
        userActiveState.setText(String.valueOf(userModal.getUserInfoModal().getIsActive()));
        userendDate.setText("0");
        userpermissionlevel.setSelection(userModal.getUserHeaderModal().getUserPermissionLevel());
        userPayGrade.setSelection(userModal.getUserInfoModal().getPayGrade());
        imageView.setImageURI(Uri.parse(String.valueOf(userModal.getUserImageModal().getPicture())));
        String temp = userModal.getUserHeaderModal().getUserTeam().toString();
        for(int i=0; i<3;i++) {
            if (user_Team[i].equals(temp))
                userTeam.setSelection(i);
        }
        Config config = new Config();
        Retrofit rfDatabase = config.databaseServer();
        api_database = rfDatabase.create(API_Database.class);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.savemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save: {
                setupdatedUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                int temp = Integer.parseInt(userendDate.getText().toString());
                if (temp != 0){
                    userController.deleteUser(userModal.getUserHeaderModal().getUserID());
                }
            }
            break;
            default:
                break;
        }
        return true;
    }

    private void setupdatedUser() {
        UpdateUserModal updateUserModal = new UpdateUserModal();
        updateUserModal.setUserPermissionLevel(Integer.parseInt((String.valueOf(userpermissionlevel.getSelectedItem()))));
        updateUserModal.setUserPayGrade(String.valueOf(userPayGrade.getSelectedItem()));
        updateUserModal.setUserTeam((String) userTeam.getSelectedItem());
        updateUserModal.setPicture(userModal.getUserImageModal().getPicture());

        String postJSON = new Gson().toJson(updateUserModal);
        final RequestBody body = RequestBody.create(MediaType.parse("application/json"), postJSON);
        userController.setupdatedUser(userModal.getUserHeaderModal().getUserID(),body);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
