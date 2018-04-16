package com.example.work.demofirebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work.demofirebase.contract.IContract;
import com.example.work.demofirebase.model.User;
import com.example.work.demofirebase.presenter.PostUserPresenter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostUserFragment extends Fragment implements IContract.IViewPostUser, View.OnClickListener {

    @BindView(R.id.edt_id)
    EditText edtId;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.edt_address)
    EditText edtAddress;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.tv_chart_email)
    TextView tvChartEmail;
    @BindView(R.id.btn_postUser)
    Button btnPostUser;

    private PostUserPresenter postUserPresenter;
    private DatabaseReference databaseReference;
    private List<User> userList;

    public static PostUserFragment newInstance() {
        return new PostUserFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_postuser, container, false);
        ButterKnife.bind(this, view);
        postUserPresenter = new PostUserPresenter(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        userList = new ArrayList<>();

        callDatabaseUser(databaseReference);

        return view;
    }

    @Override
    public void showDataSuccess(List<User> userList) {
        btnPostUser.setOnClickListener(this);
        this.userList.clear();
        this.userList.addAll(userList);
    }

    @Override
    public void showDataFailure() {
        Log.d("TAG", "Get data failure");
    }

    @Override
    public void showNotifiSuccess() {
        Toast.makeText(getActivity(), "Post Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNotifiFailure() {
        Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnPostUser) {
            if (String.valueOf(edtId.getText()).trim().length() == 0) {
                Toast.makeText(getActivity(), "Id is empty!", Toast.LENGTH_SHORT).show();
            } else if (edtName.getText().toString().trim().length() == 0) {
                Toast.makeText(getActivity(), "Name is empty!", Toast.LENGTH_SHORT).show();
            } else if (edtAddress.getText().toString().trim().length() == 0) {
                Toast.makeText(getActivity(), "Address is empty!", Toast.LENGTH_SHORT).show();
            } else if (edtEmail.getText().toString().trim().length() == 0) {
                Toast.makeText(getActivity(), "Email is empty!", Toast.LENGTH_SHORT).show();
            } else {
                Integer id = Integer.parseInt(String.valueOf(edtId.getText()).trim());
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String email = edtEmail.getText().toString().trim() + tvChartEmail.getText().toString();
                User user = new User(id, name, address, email);
                callPostUser(databaseReference, userList, user);
            }
        }
    }

    private void callDatabaseUser(DatabaseReference databaseReference) {
        postUserPresenter.loadDatabaseUser(databaseReference);
    }

    private void callPostUser(DatabaseReference databaseReference, List<User> userList, User user) {
        postUserPresenter.callPostUserPresenter(databaseReference, userList, user);
    }

}
