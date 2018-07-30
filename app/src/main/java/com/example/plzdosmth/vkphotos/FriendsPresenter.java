package com.example.plzdosmth.vkphotos;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

@InjectViewState
public class FriendsPresenter extends MvpPresenter<VkFriendsView> {

    private VKFriendsModel friendsModel;

    public FriendsPresenter() {
        this.friendsModel = new VKFriendsModel();
        login();
        getData();
    }

    public void getData(){
        loadFriends();
    }

    private void loadFriends() {
       getViewState().show((VKList<VKApiUser>) friendsModel.getFriends());
    }

    private void login(){
        getViewState().login();
    }
}
