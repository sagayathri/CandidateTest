package com.pavers.candidatetest.Adaptors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pavers.candidatetest.ContactDetails;
import com.pavers.candidatetest.MainActivity;
import com.pavers.candidatetest.Modals.UserModal;
import com.pavers.candidatetest.R;

import java.io.Serializable;
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