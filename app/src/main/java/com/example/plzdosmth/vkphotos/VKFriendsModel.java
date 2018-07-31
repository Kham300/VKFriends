package com.example.plzdosmth.vkphotos;

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
    private String result = null;
    private final String ACCESS_TOKEN = "eb6d3453eb6d3453eb6d34530eeb086e1deeb6deb6d3453b03aaffd39295725c7c87404";

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

    public String getProfilePhotoUrl(final int ownerId) {

        VKRequest requestPhoto = new VKRequest("photos.get", VKParameters.from(VKApiConst.OWNER_ID,
                ownerId, VKApiConst.ALBUM_ID, "profile", VKApiConst.REV, 1, VKApiConst.COUNT, 1, VKApiConst.ACCESS_TOKEN, ACCESS_TOKEN, VKApiConst.VERSION, 5.77),
                VKRequest.HttpMethod.GET, VKPhotoArray.class);

        requestPhoto.executeSyncWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                VKPhotoArray avatarArray = (VKPhotoArray) response.parsedModel;

                for (VKApiPhoto avatarkaFull : avatarArray) {
                    result = avatarkaFull.photo_807;
                }
            }
        });

        return result;
    }

}
