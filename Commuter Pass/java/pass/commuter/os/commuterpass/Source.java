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
public class Source extends Activity implements View.OnClickListener {
    Button btam,bcen,bvel;
    String msg;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.source);
        Bundle bundle = getIntent().getExtras();
        msg= bundle.getString("user");
        btam=(Button)findViewById(R.id.btambaram);
        bcen=(Button)findViewById(R.id.bcentral);
        bvel=(Button)findViewById(R.id.bvelacherry);
        btam.setOnClickListener(this);
        bcen.setOnClickListener(this);
        bvel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int n=v.getId();
        Intent intent=new Intent();
        switch (n){
            case R.id.btambaram:

                intent.setClass(this,Tambaram.class);
                intent.putExtra("user", msg);
                startActivity(intent);
                break;
            case R.id.bcentral:
                intent.setClass(this,Central.class);
                intent.putExtra("user", msg);
                startActivity(intent);
                break;
            case R.id.bvelacherry:
                intent.setClass(this,Velachery.class);
                intent.putExtra("user", msg);
                startActivity(intent);
                break;
        }

    }
}
