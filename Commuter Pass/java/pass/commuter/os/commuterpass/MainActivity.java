package pass.commuter.os.commuterpass;
/**
 * Created on: 30-03-2016.
 *	Author: Harish Mohan
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends Activity implements View.OnClickListener {
Button sign,login;
    EditText user,pwd;
    String suser,spwd;
    MyDB md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=(Button)findViewById(R.id.loginb);
        login.setOnClickListener(this);
        sign=(Button)findViewById(R.id.signup);
        sign.setOnClickListener(this);
        user=(EditText)findViewById(R.id.edituser);
        pwd=(EditText)findViewById(R.id.editpswd);
        md=new MyDB(this);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch(id) {
            case R.id.signup:
            Intent intent = new Intent();
            intent.setClass(this, Signup.class);
            startActivity(intent); break;
            case R.id.loginb:
               /* */
                int k=0;
                suser=user.getText().toString();
                spwd=pwd.getText().toString();
                String selectQuery = "SELECT * FROM " + md.table+";";
                SQLiteDatabase sd=md.getWritableDatabase();
                Cursor crs=sd.rawQuery(selectQuery,null);
               // Toast.makeText(this, crs.getColumnCount()+" LOL", Toast.LENGTH_LONG).show();
                   if(crs.getColumnCount()>0) {
                       while (crs.moveToNext()) {
                          //Toast.makeText(this, "Wow " + crs.getString(1)+" ~ "+crs.getString(2) + " !", Toast.LENGTH_SHORT).show();
                           if (suser.equals(crs.getString(1)) && spwd.equals(crs.getString(2))) {
                           //    Toast.makeText(this, "Welcome " + crs.getString(0) + " !", Toast.LENGTH_LONG).show();
                               k = 1;
                                  break;
                           }
                           else k = 2;
                       }
                       if(k==1) {
                           Intent intent1 = new Intent();
                           intent1.setClass(this, Rrd.class);
                           intent1.putExtra("user", suser);
                           startActivity(intent1); break;
                       }
                       if (k == 2) {
                           Toast.makeText(this, "Invalid Login", Toast.LENGTH_LONG).show();
                           user.setText("");
                           pwd.setText("");
                       }
                   }  crs.close();
                    md.close();
                    sd.close();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Done by M.Harish ~ 3517 !",Toast.LENGTH_LONG).show();
    }
}
