package com.example.emar10.rehberuygulamasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.sql.SQLException;

public class MainActivity extends Activity implements View.OnClickListener {

    EditText eIsim, eTelefon, eGetir;
    Button bKisiGetir, bEkle, bSil, bGetir, bGuncelle, bCikis , button;
    ListView tvTumKayitlar;
    String gelenIsim;
    String gelenTel;


  //  final List<Kisi> kisiler=new ArrayList<Kisi>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eIsim = (EditText) findViewById(R.id.eIsim);
        eTelefon = (EditText) findViewById(R.id.eTelefon);
        eGetir = (EditText) findViewById(R.id.eGetir);
        //Button Kısmı
        bEkle = (Button) findViewById(R.id.bEkle);
        bGuncelle = (Button) findViewById(R.id.bGuncelle);
        bSil = (Button) findViewById(R.id.bSil);
        bGetir = (Button) findViewById(R.id.bGetir);
        bKisiGetir = (Button) findViewById(R.id.bKisiGetir);
        bCikis = (Button) findViewById(R.id.bCikis);
        button= (Button)findViewById(R.id. button);
        //butonların tıklanıp tıklanmadığını dinleme kısmı
        bEkle.setOnClickListener(this);
        bSil.setOnClickListener(this);
        bGuncelle.setOnClickListener(this);
        bGetir.setOnClickListener(this);
        bKisiGetir.setOnClickListener(this);
        bCikis.setOnClickListener(this);
        button.setOnClickListener(this);

        tvTumKayitlar = (ListView) findViewById(R.id.tvTumKayitlar);

        //listviewde tıklanma olayından sonra
        //burda TumKayitlardan gelen isim ve teli isim ve tel yerine yazıyoruz
        Intent i = getIntent();
        this.gelenIsim = i.getStringExtra("gidenIsim");
        eIsim.setText(gelenIsim);
        eGetir.setText(gelenIsim);
        this.gelenTel = i.getStringExtra("gidenTel");
        eTelefon.setText(gelenTel);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:


                try {
                    Veritabani db = new Veritabani(MainActivity.this);
                    db.baglantiyiAc();
                    eGetir.setText(db.getFirstName());
                    db.baglantiyiKapat();
                } catch (Exception e) {
                    // TODO: handle exception

                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Bir Hata Oluştu. İşleminizi Kontrol Edin!");
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

                break;
            case R.id.bCikis:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Çıkmak istediğinizden emin misiniz?");
                builder.setCancelable(false);
                builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        MainActivity.this.finish();
                    }
                });
                builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        // TODO Auto-generated method stub
                        arg0.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

                break;
            case R.id.bEkle:
                String ad = eIsim.getText().toString();
                String telefon = eTelefon.getText().toString();

                try {
                    Veritabani db = new Veritabani(MainActivity.this);
                    db.baglantiyiAc();
                    db.isimYasBilgisiniKaydet(ad, telefon);
                    db.baglantiyiKapat();
                } catch (Exception e) {
                    // TODO: handle exception

                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Bir Hata Oluştu. İşleminizi Kontrol Edin!");
                    builder2.setCancelable(false);
                    builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            arg0.cancel();
                        }
                    });
                    AlertDialog alert2 = builder2.create();
                    alert2.show();

                } finally {
                    //alert dialog ile durum oluşturma
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Ekleme İşlemi Başarılı");
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

                break;
            case R.id.bGetir:
                try {
                    Intent i = new Intent("android.emar10.rehberuygulamasi.TUMKAYITLAR");
                    startActivity(i);

                } catch (Exception e) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Bir Hata Oluştu. İşleminizi Kontrol Edin!");
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
              /*  Dialog hata = new Dialog(this);
                hata.setTitle("Getirme İşlemi");
                TextView tvHata = new TextView(this);
                tvHata.setText("Bir Hata Oluştu!");

                hata.setContentView(tvHata);
                hata.show();
        */
                break;
            case R.id.bGuncelle:
                try {
                    String adGuncelle = eIsim.getText().toString();
                    String yasGuncelle = eTelefon.getText().toString();
                    //   String idGuncelle = eGetir.getText().toString();

                    String guncellenecekisim = eGetir.getText().toString();

                    Veritabani dbGuncelle = new Veritabani(MainActivity.this);
                    try {
                        dbGuncelle.baglantiyiAc();
                    } catch (SQLException e) {
                        e.printStackTrace();


                    }
                    dbGuncelle.kaydiGuncelle(guncellenecekisim, adGuncelle, yasGuncelle);
                    dbGuncelle.baglantiyiKapat();
                } catch (Exception e) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Bir Hata Oluştu. İşleminizi Kontrol Edin!");
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

                break;

            case R.id.bSil:
                try {
                    String silinecekisim = eGetir.getText().toString();
                    //long silinecekisim = Long.parseLong(silinecekKayit);

                    Veritabani dbSil = new Veritabani(MainActivity.this);
                    try {
                        dbSil.baglantiyiAc();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    dbSil.kaydiSil(silinecekisim);

                    dbSil.baglantiyiKapat();
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Silme Başarılı!");
                    builder2.setCancelable(false);
                    builder2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            // TODO Auto-generated method stub
                            arg0.cancel();
                        }
                    });
                    AlertDialog alert2 = builder2.create();
                    alert2.show();
                } catch (Exception e) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Bir Hata Oluştu. İşleminizi Kontrol Edin!");
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

                break;
            case R.id.bKisiGetir:
                try {
                    String aranacakisim = eGetir.getText().toString();
                    // long aranacakisim = Long.parseLong(id);
                    Veritabani db = new Veritabani(MainActivity.this);
                    try {
                        db.baglantiyiAc();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    String eldeEdilenIsim = db.getName(aranacakisim);
                    String eldeEdilenTel = db.getTel(aranacakisim);

                    db.baglantiyiKapat();

                    eIsim.setText(eldeEdilenIsim);
                    eTelefon.setText(eldeEdilenTel);
                } catch (Exception e) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                    builder2.setMessage("Bir Hata Oluştu. İşleminizi Kontrol Edin!");
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
                break;

        }
    }


}
