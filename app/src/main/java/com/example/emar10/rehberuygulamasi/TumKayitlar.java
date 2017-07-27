package com.example.emar10.rehberuygulamasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TumKayitlar extends Activity {

    //    private String[] test = {"İsmet", "Fatih", "Mustafa", "Mualla"};
    final List<Kisi> kisiler = new ArrayList<Kisi>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.kisiler);
            final ListView tv = (ListView) findViewById(R.id.tvTumKayitlar);

//            ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, test);
//            tv.setAdapter(adp);


            Veritabani db = new Veritabani(TumKayitlar.this);
            try {
                db.baglantiyiAc();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            final ArrayList<String> tumKayitlar = db.tumKayitlar();
            db.baglantiyiKapat();

            final List<Kisi> kisiler = new ArrayList<Kisi>();

           for(int i=0;i<tumKayitlar.size();i++) {
               kisiler.add(new Kisi(db.isimler.get(i), db.teller.get(i)));
           }

          // kisiler.add(new Kisi(db.isimler.toString(),db.teller.toString()));
            /*
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, tumKayitlar);
            tv.setAdapter(adapter);
            */


            final ListView listemiz = (ListView) findViewById(R.id.tvTumKayitlar);
            OzelAdapter adaptorumuz = new OzelAdapter(this, kisiler);
            listemiz.setAdapter(adaptorumuz);
            //  kisiler.add(new Kisi(tumKayitlar.toString(),""));


            tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String getirilenisim = tumKayitlar.get(position);
                    String getirilenisimayrilmis[] = getirilenisim.split(",");// getirilen örneğin mualla,124587 objesini ayırıyor mualla ve 1234565 diye diziye atıyor
                    // String getirilenisimayrilmis[] = {"Mualla", "0123456789"};// slash

                    Intent intocan = new Intent(TumKayitlar.this, MainActivity.class);
                    intocan.putExtra("gidenIsim", getirilenisimayrilmis[0]);// listenin ilk objesi isim ismi gönderiyor
                    // intocan.putExtra("gidenIsim", getirilenisim);
                    intocan.putExtra("gidenTel", getirilenisimayrilmis[1]);// listenin ikinci objesi telefon numarası telefon numarasını gönderiyor
                    startActivity(intocan);


                }
            });

        } catch (Exception e) {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(TumKayitlar.this);
            builder2.setMessage("Hata Tüm Kayıtlarda!");
            builder2.setCancelable(false);
            builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    // TODO Auto-generated method stub
                    arg0.cancel();
                }
            });
            AlertDialog alert2 = builder2.create();
            alert2.show();
        }

    }

}