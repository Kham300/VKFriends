package com.example.plzdosmth.vkphotos;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUser;
import com.vk.sdk.api.model.VKList;
import com.vk.sdk.api.model.VKPhotoArray;

import java.util.List;

class VKFriendsModel {

    private VKList listFriends;
    private VKList friendPhotos;

    public VKFriendsModel() {
        listFriends = new VKList();
    }

    public List<VKApiUser> getFriends(){
        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, "first_name, last_name, photo_200, photo_max_orig"));
        request.executeSyncWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                listFriends = (VKList) response.parsedModel;
            }
        });
        return listFriends;
    }

//    public String getProfilePhotoUrl(int ownerId) {
//        VKRequest requestPhoto = new VKRequest("photos.get", VKParameters.from(VKApiConst.OWNER_ID,
//                ownerId, VKApiConst.ALBUM_ID, "profile"), VKRequest.HttpMethod.GET, VKPhotoArray.class);
//
//        requestPhoto.executeSyncWithListener(new VKRequest.VKRequestListener() {
//            @Override
//            public void onComplete(VKResponse response) {
//                    super.onComplete(response);
//                    friendPhotos = (VKList) response.parsedModel;
//            }
//        });
//
//    }
}
