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
import com.pavers.candidatetest.Modals.UserHeaderModal;
import com.pavers.candidatetest.Modals.UserImageModal;
import com.pavers.candidatetest.Modals.UserInfoModal;
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
    UserHeaderModal userHeaderModal;
    UserImageModal userImageModal;
    UserInfoModal userInfoModal;
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
        userHeaderModal = new UserHeaderModal();
        userImageModal = new UserImageModal();
        userInfoModal = new UserInfoModal();


        Intent intent = getIntent();
        userModal = (UserModal) intent.getSerializableExtra("editUser");
        userName.setText(userModal.getUserHeaderModal().getUserName().toString());
        userActiveState.setText(String.valueOf(userModal.getUserInfoModal().getIsActive()));
        userendDate.setText("0");
        userpermissionlevel.setSelection(userModal.getUserHeaderModal().getUserPermissionLevel());
        userPayGrade.setSelection(userModal.getUserInfoModal().getPayGrade());
        imageView.setImageURI(Uri.parse(String.valueOf(userModal.getUserImageModal().getPicture())));
        userHeaderModal.setUserID(userModal.getUserHeaderModal().getUserID());
        userInfoModal.setUserID(userModal.getUserHeaderModal().getUserID());
        userImageModal.setUserID(userModal.getUserHeaderModal().getUserID());
        userImageModal.setPicture(userModal.getUserImageModal().getPicture());
        String temp = userModal.getUserHeaderModal().getUserTeam().toString();
        for(int i=0; i<3;i++) {
            if (user_Team[i].equals(temp))
                userTeam.setSelection(i);
        }
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
                String endDate = userendDate.getText().toString();
                if (!endDate.equals("0")){
                    userController.deleteUser(userModal.getUserHeaderModal().getUserID());
                }
                setupdatedUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            break;
            default:
                break;
        }
        return true;
    }

    private void setupdatedUser() {
        /*userHeaderModal.setUserPermissionLevel(Integer.parseInt((String.valueOf(userpermissionlevel.getSelectedItem()))));
        userHeaderModal.setUserTeam((String) userTeam.getSelectedItem());
        userModal.setUserHeaderModal(userHeaderModal);
        userInfoModal.setPayGrade(Integer.parseInt(String.valueOf(userPayGrade.getSelectedItem())));
        userModal.setUserInfoModal(userInfoModal);
        userImageModal.setPicture(userImageModal.getPicture());
        userModal.setUserImageModal(userImageModal);
        userModal.setUserImageModal(userImageModal);*/
        UpdateUserModal updateUserModal = new UpdateUserModal();
        updateUserModal.setUserTeam((String) userTeam.getSelectedItem());
        updateUserModal.setUserPermissionLevel(Integer.parseInt((String.valueOf(userpermissionlevel.getSelectedItem()))));
        updateUserModal.setpayGrade(Integer.parseInt(String.valueOf(userPayGrade.getSelectedItem())));

        String postJSON = new Gson().toJson(updateUserModal);
        final RequestBody body = RequestBody.create(MediaType.parse("application/json"), postJSON);
        userController.setupdatedUser(userModal.getUserHeaderModal().getUserID(), body);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
