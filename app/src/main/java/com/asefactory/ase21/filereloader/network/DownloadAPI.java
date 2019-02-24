package com.asefactory.ase21.filereloader.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DownloadAPI {
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);
}
