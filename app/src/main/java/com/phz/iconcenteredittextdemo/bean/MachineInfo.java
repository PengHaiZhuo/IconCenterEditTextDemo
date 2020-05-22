package com.phz.iconcenteredittextdemo.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Transient;

/**
 * @author haizhuo
 * @introduction 机器数据
 */
@Entity
public class MachineInfo {

    @NotNull
    private String name;

    private String address;

    /**
     * 使用了Transient的话，那这条属性就从持久性中排除了
     */
    @Transient
    private String someThing;

    @Keep
    public MachineInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Generated(hash = 1515055871)
    public MachineInfo() {
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
