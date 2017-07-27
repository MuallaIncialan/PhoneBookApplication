package com.example.emar10.rehberuygulamasi;

/**
 * Created by EMAR10 on 7.04.2017.
 */

public class Kisi {
    private String  isim;
    private String tel;

    public Kisi(String isim, String tel) {
        super();
        this.isim = isim;
        this.tel = tel;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
