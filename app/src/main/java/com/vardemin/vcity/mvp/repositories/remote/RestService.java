package com.vardemin.vcity.mvp.repositories.remote;


import com.vardemin.vcity.data.models.Photo;
import com.vardemin.vcity.data.models.scheme.PhotoScheme;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RestService {
    @Multipart
    @POST("images")
    Observable<PhotoScheme> uploadImage(@Part MultipartBody.Part file, @Header("Authorization") String token);
}
