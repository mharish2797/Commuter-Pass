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
import android.widget.EditText;
import android.widget.Toast;
public class Signup extends Activity implements View.OnClickListener
{EditText fname,uname,pwd,cpwd,email,phone,address;
    Button go;
    String sfname="",suname="",spwd="",scpwd="",semail="",sphone="",saddress="";
    MyDB md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        fname=(EditText)findViewById(R.id.fname);
        uname=(EditText)findViewById(R.id.uname);
        pwd=(EditText)findViewById(R.id.pwd);
        cpwd=(EditText)findViewById(R.id.cpwd);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
        md=new MyDB(this);
        go=(Button)findViewById(R.id.go);
        go.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        suname=uname.getText().toString();
        sfname=fname.getText().toString();
        spwd=pwd.getText().toString();
        scpwd=cpwd.getText().toString();
        semail=email.getText().toString();
        sphone=phone.getText().toString();
        saddress=address.getText().toString();
        int fd=0,pl=sphone.length(),check=0,k=0;
        for(int i=0;i<semail.length();i++){
            if(semail.charAt(i)=='@') fd++;
        }
        String selectQuery = "SELECT * FROM " + md.table+";";
        SQLiteDatabase sd1=md.getWritableDatabase();
        Cursor crs=sd1.rawQuery(selectQuery,null);
        // Toast.makeText(this, crs.getColumnCount()+" LOL", Toast.LENGTH_LONG).show();
        if(crs.getColumnCount()>0) {
            while (crs.moveToNext()) {
                //Toast.makeText(this, "Wow " + crs.getString(1)+" ~ "+crs.getString(2) + " !", Toast.LENGTH_SHORT).show();
                if (suname.equals(crs.getString(1))) {
                    //    Toast.makeText(this, "Welcome " + crs.getString(0) + " !", Toast.LENGTH_LONG).show();
                    k = 1;
                    break;
                }

            }


        }  crs.close();
        md.close();
        sd1.close();

        if(!scpwd.equals(spwd)) {

            pwd.setText("");
            cpwd.setText("");

            Toast.makeText(this,"Password doesn't match",Toast.LENGTH_LONG).show();
        }
       else if(suname.equals("")||sfname.equals("")||spwd.equals("")||scpwd.equals("")||semail.equals("")||sphone.equals("")||saddress.equals("")){
            pwd.setText("");
            cpwd.setText("");
            Toast.makeText(this,"Field Blank !",Toast.LENGTH_LONG).show();
        }
        else if(k==1)  Toast.makeText(this,"User name aldready exists",Toast.LENGTH_SHORT).show();
        else if(pl!=10)  Toast.makeText(this,"Invalid Mobile Number!",Toast.LENGTH_SHORT).show();
        else if(fd!=1)  Toast.makeText(this,"Email Id Invalid!",Toast.LENGTH_SHORT).show();
        else {
            SQLiteDatabase sd=md.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(md.fsname,sfname);
            cv.put(md.usname,suname);
            cv.put(md.psswd, spwd);
            cv.put(md.esmail, semail);
            cv.put(md.pshone, sphone);
            cv.put(md.asddress, saddress);
            cv.put(md.ssource,"NULL");
            cv.put(md.sdestin,"NULL");
            cv.put(md.sfare,0);
            cv.put(md.sdatey,"NULL");
            cv.put(md.sconcess,"NULL");
           long x=sd.insert(md.table,null, cv);
              //Toast.makeText(this,"Welcome : "+x+" : "+cv.get(md.fsname)+" !",Toast.LENGTH_LONG).show();
            cv.clear();
            sd.close();
            md.close();
            Intent intent=new Intent();
            intent.setClass(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
