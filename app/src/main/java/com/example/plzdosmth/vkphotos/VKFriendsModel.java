package com.example.plzdosmth.vkphotos;

import android.widget.ImageView;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKPhotoArray;

import java.util.List;

class VKFriendsModel {

    private VKList listFriends;
    private String friendPhoto;

    public VKFriendsModel() {
        listFriends = new VKList();
    }

    public List<VKApiUser> getFriends(){
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, photo_200, user_id"));
        request.executeSyncWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                listFriends = (VKList) response.parsedModel;
            }
        });
        return listFriends;
    }


//https://github.com/VKCOM/vk-android-sdk/issues/126
    public String getProfilePhotoUrl(String ownerId) {

        VKRequest requestPhoto = new VKRequest("photos.get", VKParameters.from(VKApiConst.OWNER_ID,
                ownerId, VKApiConst.ALBUM_ID, "profile", VKApiConst.REV, "rev=1", VKApiConst.COUNT, "count=1"), VKRequest.HttpMethod.GET, VKPhotoArray.class);

        requestPhoto.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKPhotoArray avataraArray = (VKPhotoArray) response.parsedModel;

                for (VKApiPhoto avatarkaFull : avataraArray) {
                    friendPhoto =  avatarkaFull.photo_807;
                }
            }
        });

        return friendPhoto;
    }
}
