package com.example.work.demofirebase.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.work.demofirebase.R;
import com.example.work.demofirebase.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListRecyclerViewAdapter extends RecyclerView.Adapter<UserListRecyclerViewAdapter.UserDataViewHolder> {

    private Context context;
    private List<User> userList;

    public UserListRecyclerViewAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new UserDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDataViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvId.setText(String.valueOf(user.getId()));
        holder.tvName.setText(user.getName());
        holder.tvAddress.setText(user.getAddress());
        holder.tvEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList != null ? userList.size() : 0;
    }

    class UserDataViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_email)
        TextView tvEmail;

        UserDataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
