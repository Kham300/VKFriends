package com.example.plzdosmth.vkphotos;

import com.arellomobile.mvp.MvpView;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;

public interface VkFriendsView extends MvpView{
    void show(final VKList<VKApiUser> list);
}
