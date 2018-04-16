package com.example.work.demofirebase.interactor;

import com.example.work.demofirebase.UserListFragment;
import com.example.work.demofirebase.contract.IContract;
import com.example.work.demofirebase.model.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserInteractor {

    private List<User> userList;
    private IContract.IViewInteractor iViewInteractor;

    public UserInteractor(IContract.IViewInteractor iViewInteractor) {
        this.iViewInteractor = iViewInteractor;
        userList = new ArrayList<>();
    }

    public void getDatabaseUser(DatabaseReference databaseReference) {
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildren() != null) {
                    userList.clear();
                    for (DataSnapshot itemDataSnapshot : dataSnapshot.getChildren()) {
                        userList.add(itemDataSnapshot.getValue(User.class));
                    }
                    iViewInteractor.sendDataUserSuccess(userList);
                } else {
                    iViewInteractor.sendDataUserFailure();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                iViewInteractor.sendDataUserFailure();
            }
        });
    }
}
