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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Rrd  extends Activity implements View.OnClickListener {
    Button breg,bren,bdet;
    String msg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rrd);
        Bundle bundle = getIntent().getExtras();
        msg= bundle.getString("user");
        bren=(Button)findViewById(R.id.brenew);
        breg=(Button)findViewById(R.id.bregister);
        bdet=(Button)findViewById(R.id.bdetail);
        breg.setOnClickListener(this);
        bren.setOnClickListener(this);
        bdet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int n=v.getId();
        Intent intent=new Intent();
        switch (n){
            case R.id.bregister:

                intent.setClass(this,Source.class);
                intent.putExtra("user", msg);
                startActivity(intent);
                break;
            case R.id.brenew:
                MyDB md;
                md = new MyDB(this);
                String sr="",ds="",formattedDate="",lol="";
                int fre=0;
                SQLiteDatabase sd=md.getWritableDatabase();
                String selectQuery = "SELECT * FROM " + md.table+";";
                Cursor crs=sd.rawQuery(selectQuery,null);
                // Toast.makeText(this, crs.getColumnCount()+" LOL", Toast.LENGTH_LONG).show();
                if(crs.getColumnCount()>0) {
                    while (crs.moveToNext()) {
                        //Toast.makeText(this, "Wow " + crs.getString(1)+" ~ "+crs.getString(2) + " !", Toast.LENGTH_SHORT).show();
                        if (msg.equals(crs.getString(1))) {
                            //    Toast.makeText(this, "Welcome " + crs.getString(0) + " !", Toast.LENGTH_LONG).show()
                            sr=crs.getString(6);
                            ds=crs.getString(7);
                            fre=crs.getInt(8);
                            formattedDate=crs.getString(9);
                            lol=crs.getString(10);
                            if(sr.equals("NULL")||ds.equals("NULL")) {
                                Toast.makeText(this,"First Register your Pass !",Toast.LENGTH_LONG).show();
                                intent.setClass(this,Source.class);
                                intent.putExtra("user", msg);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(this,"Proceeding towards Payment Portal !",Toast.LENGTH_LONG).show();
                                intent.setClass(this, Pay.class);
                                intent.putExtra("sourcer", sr);
                                intent.putExtra("dester", ds);
                                intent.putExtra("user",msg);
                                intent.putExtra("farer",fre);
                                intent.putExtra("date",formattedDate);
                                intent.putExtra("concess",lol);
                                startActivity(intent);
                            }
                            break;
                        }
                    }
                }
                crs.close();
                md.close();
                sd.close();
                break;
            case R.id.bdetail:

                intent.setClass(this,Detail.class);
                intent.putExtra("user", msg);
                startActivity(intent);
                break;
        }
    }
}