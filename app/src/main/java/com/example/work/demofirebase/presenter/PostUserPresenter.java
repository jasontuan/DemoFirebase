package com.example.work.demofirebase.presenter;

import com.example.work.demofirebase.contract.IContract;
import com.example.work.demofirebase.interactor.PostUserInteractor;
import com.example.work.demofirebase.model.User;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class PostUserPresenter implements IContract.IViewPostInteractor {

    private IContract.IViewPostUser iViewPostUser;
    private PostUserInteractor postUserInteractor;

    public PostUserPresenter(IContract.IViewPostUser iViewPostUser) {
        this.iViewPostUser = iViewPostUser;
        postUserInteractor = new PostUserInteractor(this);
    }

    public void callPostUserPresenter(DatabaseReference databaseReference, List<User> userList, User user) {
        postUserInteractor.handingPostUser(databaseReference, userList, user);
    }

    public void loadDatabaseUser(DatabaseReference databaseReference) {
        postUserInteractor.getDatabaseUser(databaseReference);
    }

    @Override
    public void sendDataUserSuccess(List<User> userList) {
        iViewPostUser.showDataSuccess(userList);
    }

    @Override
    public void sendDataUserFailure() {
        iViewPostUser.showDataFailure();
    }

    @Override
    public void sendNotifiSuccess() {
        iViewPostUser.showNotifiSuccess();
    }

    @Override
    public void sendNotifiFailure() {
        iViewPostUser.showNotifiFailure();
    }
}
