package pass.commuter.os.commuterpass;

/**
 *  Created on: 25-03-2016.
 *	Author: Harish Mohan
 */

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class Pay extends Activity implements View.OnClickListener {
    TextView fsrc,fdest,ffare,fdate,fconer;
    MyDB md;
    String msg,srk,dsk,fn,pn,en,ad,pw,dater,coner;
    Button proceed;
    int farer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay);
        Bundle bundle = getIntent().getExtras();
        msg = bundle.getString("user");
        srk=bundle.getString("sourcer");
        dsk=bundle.getString("dester");
        farer=bundle.getInt("farer");
        dater=bundle.getString("date");
        coner=bundle.getString("concess");
        md = new MyDB(this);
        SQLiteDatabase sd=md.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + md.table+";";
        Cursor crs=sd.rawQuery(selectQuery,null);
        // Toast.makeText(this, crs.getColumnCount()+" LOL", Toast.LENGTH_LONG).show();
        if(crs.getColumnCount()>0) {
            while (crs.moveToNext()) {
                //Toast.makeText(this, "Wow " + crs.getString(1)+" ~ "+crs.getString(2) + " !", Toast.LENGTH_SHORT).show();
                if (msg.equals(crs.getString(1))) {
                    //    Toast.makeText(this, "Welcome " + crs.getString(0) + " !", Toast.LENGTH_LONG).show();
                        fn=crs.getString(0);
                    pw=crs.getString(2);
                    pn=crs.getString(4);
                    en=crs.getString(3);
                    ad=crs.getString(5);
                    break;
                }
            }
        }
        crs.close();
        md.close();
        sd.close();
        SQLiteDatabase sd1=md.getWritableDatabase();
        ContentValues cv1=new ContentValues();
        cv1.put(md.fsname,fn);
        cv1.put(md.usname,msg);
        cv1.put(md.psswd, pw);
        cv1.put(md.esmail, en);
        cv1.put(md.pshone, pn);
        cv1.put(md.asddress, ad);
        cv1.put(md.ssource, srk);
        cv1.put(md.sdestin, dsk);
        cv1.put(md.sfare, farer);
        cv1.put(md.sdatey,dater);
        cv1.put(md.sconcess,coner);
        String[] selec = {msg};
       long x= sd1.update(md.table,cv1,"username = ?",selec);
       // Toast.makeText(this,"LOL : "+x,Toast.LENGTH_LONG).show();
        cv1.clear();
        sd1.close();
        md.close();
         fsrc= (TextView) findViewById(R.id.psrc);
        fdest= (TextView) findViewById(R.id.pdet);
        ffare= (TextView) findViewById(R.id.pfare);
        fdate=(TextView)findViewById(R.id.pdate);
        fconer=(TextView)findViewById(R.id.pcon);
        proceed=(Button)findViewById(R.id.fpay);
        proceed.setOnClickListener(this);
        fsrc.setText("Source: " + srk);
        fdest.setText("Destination: "+dsk);
        ffare.setText("Fare: " + farer);
        fdate.setText("Pass Valid till: "+dater);
        fconer.setText("Concession : "+coner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"Done by M.Harish ~ 3517 !",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();
        intent.setClass(this, Webber.class);
        startActivity(intent);
    }
}