package com.pavers.candidatetest.Adaptors;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pavers.candidatetest.Modals.UserModal;
import com.pavers.candidatetest.R;

import java.util.List;

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.ContactViewHolder> {


    private List<UserModal> allUsers;



    public UserAdaptor(List<UserModal> _allUsers) {
        this.allUsers = _allUsers;
    }


    public static class ContactViewHolder extends RecyclerView.ViewHolder {
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
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, final int position) {

        UserModal modal = allUsers.get(position);

        contactViewHolder.userName.setText(modal.getUserHeaderModal().getUserName());

        contactViewHolder.clickedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Clicked", "Clicked Item" + position);

                UserModal currentUser = getItemAtPosition(position);
                

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
