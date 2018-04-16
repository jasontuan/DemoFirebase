package com.example.work.demofirebase.presenter;

import com.example.work.demofirebase.contract.IContract;
import com.example.work.demofirebase.interactor.UserInteractor;
import com.example.work.demofirebase.model.User;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class UserPresenter implements IContract.IViewInteractor {

    private IContract.IView iView;
    private UserInteractor userInteractor;

    public UserPresenter(IContract.IView iView) {
        this.iView = iView;
        userInteractor = new UserInteractor(this);
    }

    public void loadDatabaseUser(DatabaseReference databaseReference) {
        userInteractor.getDatabaseUser(databaseReference);
    }

    @Override
    public void sendDataUserSuccess(List<User> userList) {
        iView.showDataSuccess(userList);
    }

    @Override
    public void sendDataUserFailure() {
        iView.showDataFailure();

    }
}
