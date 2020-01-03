package com.bawei.demo0102;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * ProjectName: Demo0102
 * PackageName: com.bawei.demo0102
 * ClassName:   ICommodityList
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/3_8:44
 */
public interface ICommodityList {
    @GET(Api.COMMODITYLIST_URL)
    Call<ProductEntity> getData();
}
