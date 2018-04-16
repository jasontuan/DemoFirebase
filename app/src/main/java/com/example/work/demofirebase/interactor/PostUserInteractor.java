package com.example.work.demofirebase.interactor;

import com.example.work.demofirebase.contract.IContract;
import com.example.work.demofirebase.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PostUserInteractor {

    private IContract.IViewPostInteractor iViewPostInteractor;
    private List<User> userList;

    public PostUserInteractor(IContract.IViewPostInteractor iViewPostInteractor) {
        this.iViewPostInteractor = iViewPostInteractor;
        userList = new ArrayList<>();
    }

    public void getDatabaseUser(final DatabaseReference databaseReference) {
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildren() != null) {
                    userList.clear();
                    for (DataSnapshot itemDataSnapshot : dataSnapshot.getChildren()) {
                        userList.add(itemDataSnapshot.getValue(User.class));
                    }
                    iViewPostInteractor.sendDataUserSuccess(userList);
                } else {
                    iViewPostInteractor.sendDataUserFailure();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                iViewPostInteractor.sendDataUserFailure();
            }
        });
    }

    private boolean isCheckId(List<User> userList, int id) {
        for (User item : userList) {
            if (item.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void handingPostUser(DatabaseReference databaseReference, List<User> userList, User user) {
        if (user != null) {
            if (userList.size() != 0) {
                if (isCheckId(userList, user.getId())) {
                    iViewPostInteractor.sendNotifiFailure();
                } else {
                    databaseReference.child("User").child(String.valueOf(user.getId())).setValue(user);
                    iViewPostInteractor.sendNotifiSuccess();
                }
            } else {
                databaseReference.child("User").child(String.valueOf(user.getId())).setValue(user);
                userList.clear();
                iViewPostInteractor.sendNotifiSuccess();
            }
        } else {
            iViewPostInteractor.sendNotifiFailure();
        }
    }
}
