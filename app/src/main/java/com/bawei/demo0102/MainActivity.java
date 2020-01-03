package com.bawei.demo0102;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bawei.demo0102.greendao.BannerInfoEntityDao;
import com.bawei.demo0102.greendao.DaoMaster;
import com.bawei.demo0102.greendao.DaoSession;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.androidx.XBanner;

import org.greenrobot.greendao.query.Query;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends AppCompatActivity {

    String bannerUrl = "http://172.17.8.100/small/commodity/v1/bannerShow";
    String productUrl = "http://172.17.8.100/small/commodity/v1/commodityList";
    Handler handler = new Handler();
    private DaoMaster.DevOpenHelper devOpenHelper;
    private SQLiteDatabase writableDatabase;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoMaster.DevOpenHelper devOpenHelper1;
    private SQLiteDatabase writableDatabase1;
    private DaoMaster daoMaster1;
    private DaoSession daoSession1;
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devOpenHelper = new DaoMaster.DevOpenHelper(this, "banner.db", null);
        writableDatabase = devOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(writableDatabase);
        daoSession = daoMaster.newSession();

        /*
        devOpenHelper1 = new DaoMaster.DevOpenHelper(this, "product.db", null);
        writableDatabase1 = devOpenHelper.getWritableDatabase();
        daoMaster1 = new DaoMaster(writableDatabase);
        daoSession1 = daoMaster.newSession();*/


        final XBanner xBanner = findViewById(R.id.xbb);
        final RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView1 = findViewById(R.id.rv1);
        recyclerView2 = findViewById(R.id.rv2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setLayoutManager(new GridLayoutManager(this,2));
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        Request request = new Request.Builder().url(bannerUrl).get().build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                final IOException error = e;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("xxx", String.valueOf(error));
                    }
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        BannerEntity bannerEntity = new Gson().fromJson(string, BannerEntity.class);
                        final List<BannerEntity.ResultBean> result = bannerEntity.getResult();
                        if (result != null) {
                            for (BannerEntity.ResultBean resultBean : result) {
                                BannerInfoEntity bannerInfoEntity = new BannerInfoEntity();
                                bannerInfoEntity.setImageUrl(resultBean.getImageUrl());
                                bannerInfoEntity.setTitle(resultBean.getTitle());
                                daoSession.insert(bannerInfoEntity);
                            }
                        }
                        xBanner.setBannerData(result);
                        xBanner.loadImage(new XBanner.XBannerAdapter() {
                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position) {
                                ImageView imageView = (ImageView) view;

                                BannerEntity.ResultBean resultBean = result.get(position);
                                String imageUrl = resultBean.getImageUrl();

                                Glide.with(MainActivity.this).load(imageUrl).into(imageView);

                            }
                        });
                    }
                });
            }
        });


        Request request1 = new Request.Builder().url(productUrl).get().build();

        httpClient.newCall(request1).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String string = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ProductEntity productEntity = new Gson().fromJson(string, ProductEntity.class);

                        ProductEntity.ResultBean result = productEntity.getResult();
                        ProductEntity.ResultBean.RxxpBean rxxp = result.getRxxp();
                        List<ProductEntity.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxp.getCommodityList();
                        Adaipter adaipter = new Adaipter(MainActivity.this, commodityList);

                        recyclerView.setAdapter(adaipter);

                       /* for (ProductEntity.ResultBean.RxxpBean.CommodityListBean commodityListBean : commodityList) {
                            ProductInfoEntity productInfoEntity = new ProductInfoEntity();
                            productInfoEntity.setCommodityName(commodityListBean.getCommodityName());
                            productInfoEntity.setMasterPic(commodityListBean.getMasterPic());
                            productInfoEntity.setPrice(commodityListBean.getPrice());

                            daoSession.insert(productInfoEntity);
                        }*/


                    }
                });
            }
        });


        //查找数据

        BannerInfoEntityDao bannerInfoEntityDao = daoSession.getBannerInfoEntityDao();
        Query<BannerInfoEntity> build = bannerInfoEntityDao.queryBuilder().build();

        List<BannerInfoEntity> list = build.list();

        for (BannerInfoEntity bannerInfoEntity : list) {
            Log.i("xxx",bannerInfoEntity.toString());
        }

        //设置适配器
       /* Adaipter adaipter = new Adaipter(MainActivity.this, list);

        recyclerView.setAdapter(adaipter);*/


        ICommodityList data = RetrofitUtils.getInstance().getData(ICommodityList.class);
        data.getData().enqueue(new retrofit2.Callback<ProductEntity>() {
            @Override
            public void onResponse(retrofit2.Call<ProductEntity> call, retrofit2.Response<ProductEntity> response) {
                ProductEntity body = response.body();
                List<ProductEntity.ResultBean.MlssBean.CommodityListBeanXX> mlss = body.getResult().getMlss().getCommodityList();
                List<ProductEntity.ResultBean.PzshBean.CommodityListBeanX> pzsh = body.getResult().getPzsh().getCommodityList();

                Toast.makeText(MainActivity.this, ""+mlss.size(), Toast.LENGTH_SHORT).show();

                Bdaipter bdaipter = new Bdaipter(MainActivity.this, mlss);
                recyclerView1.setAdapter(bdaipter);


                Cdaipter cdaipter = new Cdaipter(MainActivity.this, pzsh);
                recyclerView2.setAdapter(cdaipter);


            }

            @Override
            public void onFailure(retrofit2.Call<ProductEntity> call, Throwable t) {

            }
        });


    }
}
