package com.bawei.demo0102;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * ProjectName: Demo0102
 * PackageName: com.bawei.demo0102.greendao
 * ClassName:   ProductInfoEntity
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2020/1/2_9:12
 */
@Entity
public class ProductInfoEntity {
    private String commodityName;
    private String masterPic;
    private int price;
    @Generated(hash = 713703618)
    public ProductInfoEntity(String commodityName, String masterPic, int price) {
        this.commodityName = commodityName;
        this.masterPic = masterPic;
        this.price = price;
    }
    @Generated(hash = 2026077526)
    public ProductInfoEntity() {
    }
    public String getCommodityName() {
        return this.commodityName;
    }
    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getMasterPic() {
        return this.masterPic;
    }
    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public int getPrice() {
        return this.price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}
