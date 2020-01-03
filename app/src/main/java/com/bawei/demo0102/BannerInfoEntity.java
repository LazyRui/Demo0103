package com.bawei.demo0102;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ProjectName: Demo0102
 * PackageName: com.bawei.demo0102
 * ClassName:   BannerInfoEntity
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/2_8:51
 */
@Entity
public class BannerInfoEntity {
    private String imageUrl;
  //  private String jumpUrl;
   // private int rank;
    private String title;
    @Generated(hash = 137550902)
    public BannerInfoEntity(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }
    @Generated(hash = 1323257559)
    public BannerInfoEntity() {
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BannerInfoEntity{" +
                "imageUrl='" + imageUrl + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
