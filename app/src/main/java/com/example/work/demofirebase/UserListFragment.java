package com.example.work.demofirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.work.demofirebase.adapter.UserListRecyclerViewAdapter;
import com.example.work.demofirebase.contract.IContract;
import com.example.work.demofirebase.model.User;
import com.example.work.demofirebase.presenter.UserPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListFragment extends Fragment implements IContract.IView {

    @BindView(R.id.rcv_userlist)
    RecyclerView rcvUserlist;
    @BindView(R.id.pb_waitShowData)
    ProgressBar pbWaitShowData;

    private UserPresenter userPresenter;

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_userlist, container, false);
        ButterKnife.bind(this, view);

        userPresenter = new UserPresenter(this);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        rcvUserlist.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL, false));

        pbWaitShowData.setVisibility(View.VISIBLE);
        callDatabaseUser(databaseReference);

        return view;
    }

    @Override
    public void showDataSuccess(List<User> userList) {
        pbWaitShowData.setVisibility(View.GONE);
        List<User> tempUserList = new ArrayList<>();
        tempUserList.clear();
        tempUserList.addAll(userList);
        showDataRecyclerView(tempUserList);
    }

    @Override
    public void showDataFailure() {
        pbWaitShowData.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Show Failure", Toast.LENGTH_SHORT).show();
    }

    private void callDatabaseUser(DatabaseReference databaseReference) {
        userPresenter.loadDatabaseUser(databaseReference);
    }

    private void showDataRecyclerView(List<User> userList) {
        UserListRecyclerViewAdapter userListRecyclerViewAdapter = new UserListRecyclerViewAdapter(getActivity(), userList);
        rcvUserlist.setAdapter(userListRecyclerViewAdapter);
        userListRecyclerViewAdapter.notifyDataSetChanged();
    }
}
