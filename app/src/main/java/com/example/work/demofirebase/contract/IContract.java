package com.example.work.demofirebase.contract;

import com.example.work.demofirebase.model.User;

import java.util.List;

public interface IContract {

    interface IViewInteractor {
        void sendDataUserSuccess(List<User> userList);

        void sendDataUserFailure();
    }

    interface IViewPostInteractor {
        void sendDataUserSuccess(List<User> userList);

        void sendDataUserFailure();

        void sendNotifiSuccess();

        void sendNotifiFailure();
    }

    interface IView {
        void showDataSuccess(List<User> userList);

        void showDataFailure();
    }

    interface IViewPostUser {
        void showDataSuccess(List<User> userList);

        void showDataFailure();

        void showNotifiSuccess();

        void showNotifiFailure();
    }
}
