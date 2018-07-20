package pass.commuter.os.commuterpass;

/**
 * Created on: 30-03-2016.
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
import android.widget.TextView;
import android.widget.Toast;
public class Detail extends Activity  {
    TextView efnam,eunam,eemail,eadd,ephon,esour,edest,efar,econ,eval;
    MyDB md;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Bundle bundle = getIntent().getExtras();
        String msg= bundle.getString("user");
        md=new MyDB(this);
        efnam=(TextView)findViewById(R.id.fnam);
        eunam=(TextView)findViewById(R.id.unam);
        eemail=(TextView)findViewById(R.id.emid);
        ephon=(TextView)findViewById(R.id.phon);
        eadd=(TextView)findViewById(R.id.adre);
        esour=(TextView)findViewById(R.id.srce);
        edest=(TextView)findViewById(R.id.dst);
        efar=(TextView)findViewById(R.id.far);
        econ=(TextView)findViewById(R.id.concs);
        eval=(TextView)findViewById(R.id.vtil);
        String selectQuery = "SELECT * FROM " + md.table+";";
        SQLiteDatabase sd=md.getWritableDatabase();
        Cursor crs=sd.rawQuery(selectQuery,null);
        if(crs.getColumnCount()>0) {
            while (crs.moveToNext()) {
                // Toast.makeText(this, "Wow " + crs.getString(1)+" ~ "+crs.getString(2) + " !", Toast.LENGTH_SHORT).show();
                if (msg.equals(crs.getString(1))) {
                    efnam.setText("Full name: " + crs.getString(0));
                    eunam.setText("User name: " + crs.getString(1));
                    eemail.setText("Email ID: " + crs.getString(3));
                    ephon.setText("Phone Number: " + crs.getString(4));
                    eadd.setText("Address: " + crs.getString(5));
                    esour.setText("Source: " + crs.getString(6));
                    edest.setText("Destination: " + crs.getString(7));
                    efar.setText("Fare: " + crs.getInt(8));
                    eval.setText("Validity: "+crs.getString(9));
                    econ.setText("Concession: "+crs.getString(10));

                    break;
                }

            }
        }
        crs.close();
        md.close();
        sd.close();
    }
    }
