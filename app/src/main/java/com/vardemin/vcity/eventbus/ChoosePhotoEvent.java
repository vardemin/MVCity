package com.vardemin.vcity.eventbus;

import android.net.Uri;

/**
 * Created by vard on 21.11.17.
 */

public class ChoosePhotoEvent {
    private Uri photo;

    public ChoosePhotoEvent(Uri uri) {
        this.photo = uri;
    }

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }
}
