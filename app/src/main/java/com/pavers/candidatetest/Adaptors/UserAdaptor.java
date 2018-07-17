package com.pavers.candidatetest.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pavers.candidatetest.ContactDetails;
import com.pavers.candidatetest.MainActivity;
import com.pavers.candidatetest.Modals.UserModal;
import com.pavers.candidatetest.R;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.ContactViewHolder> {


    private List<UserModal> allUsers;
    Activity context;

    public UserAdaptor(List<UserModal> _allUsers, Activity context) {
        this.allUsers = _allUsers;
        this.context = context;
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private View clickedView;
        TextView userName;
        ImageView imageView;

        ContactViewHolder(View view) {
            super(view);

            this.clickedView = view;

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Item Clicked", "Clicked");
                }
            });

            userName = view.findViewById(R.id.tvName);
            imageView = view.findViewById(R.id.ivProfile);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_allusers, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactViewHolder contactViewHolder, final int position) {

        UserModal modal = allUsers.get(position);
        contactViewHolder.userName.setText(modal.getUserHeaderModal().getUserName());
        /*String imgstr = modal.getUserImageModal().getPicture();
        URL url;
        {
            try {
                url = new URL(imgstr);
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                contactViewHolder.imageView.setImageBitmap(bmp);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        contactViewHolder.clickedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                UserModal currentUser = getItemAtPosition(position);
                Intent intent = new Intent(activity, ContactDetails.class);
                UserModal userModal = new UserModal();
                userModal.setUserHeaderModal(currentUser.getUserHeaderModal());
                userModal.setUserInfoModal(currentUser.getUserInfoModal());
                userModal.setUserImageModal(currentUser.getUserImageModal());
                intent.putExtra("currentUser", userModal);
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public UserModal getItemAtPosition(int position) {
        return allUsers.get(position);
    }
}