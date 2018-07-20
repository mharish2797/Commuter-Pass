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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Central extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private Spinner spinner,spinner1;
    private RadioGroup radmonth,radcon;
    private RadioButton radbut;
    int sor=1,des=10,fare;
    Button bpass;
    String srk,msg,dsk;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.central);
        spinner = (Spinner)findViewById(R.id.cspinner);
        radmonth=(RadioGroup)findViewById(R.id.cradioGroup);
        radcon=(RadioGroup)findViewById(R.id.cradioGroup1);
        radmonth.check(R.id.camonth);
        radcon.check(R.id.cnon);

        Bundle bundle = getIntent().getExtras();
        msg= bundle.getString("user");

        List<String> categories = new ArrayList<String>();
        categories.add("Beach");
        categories.add("Central");
        categories.add("Perambur");
        categories.add("Villivakkam");
        categories.add("Ambattur");
        categories.add("ThirumullaiKovil");
        categories.add("Avadi");
        categories.add("Thiruvallur");
        categories.add("Mosur");
        categories.add("Arakkonam");
        categories.add("Tiruttani");
        bpass=(Button)findViewById(R.id.cpass);
        bpass.setOnClickListener(this);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);


        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(this);
        spinner1 = (Spinner)findViewById(R.id.cspinner1);
        spinner1.setAdapter(dataAdapter);

        spinner1.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.cspinner)
        {
            String item = parent.getItemAtPosition(position).toString();
            srk=parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            sor=position+1;
        }
        else if(spinner.getId() == R.id.cspinner1)
        {
            String item = parent.getItemAtPosition(position).toString();
            dsk=parent.getItemAtPosition(position).toString();

            // Showing selected spinner item
            des=position+1;
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        sor=1;des=10;
    }

    @Override
    public void onClick(View v) {
        if(sor==des)  Toast.makeText(this, "Souce-Destination cannot be the same", Toast.LENGTH_SHORT).show();
        else{
            int vlo=radcon.getCheckedRadioButtonId();
            float concess=0;
            String lol="";
            switch (vlo){
                case R.id.cnon: lol="None";concess=0; break;
                case R.id.cstud: lol="Student"; concess=4; break;
                case R.id.cwork: lol="Worker"; concess=2; break;
                case R.id.csnr: lol="Senior Citizen"; concess=3; break;
            }
            fare=sor-des;
            concess*=.1;
            if(fare<0) fare*=-1;
            int selectedId=radmonth.getCheckedRadioButtonId();
            radbut=(RadioButton)findViewById(selectedId);
            if(selectedId==R.id.camonth)
                fare=fare*28;
            else fare=fare*28*3;
            float x=fare*(1-concess);
            fare=(int)x;
            Calendar c = Calendar.getInstance();
            int currentDay=c.get(Calendar.DAY_OF_MONTH);
            if(selectedId==R.id.camonth)
                c.set(Calendar.DAY_OF_MONTH, currentDay+30);
            else
                c.set(Calendar.DAY_OF_MONTH, currentDay+90);
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());
            Toast.makeText(this, "Valid Till : " + formattedDate, Toast.LENGTH_LONG).show();

            String s="Fare: "+fare;
            Toast.makeText(this, "Selected Source: " + srk, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "Selected Destination: " + dsk, Toast.LENGTH_SHORT).show();

            Toast.makeText(this,s, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent();
            intent.setClass(this,Pay.class);
            intent.putExtra("sourcer", srk);
            intent.putExtra("dester", dsk);
            intent.putExtra("user", msg);
            intent.putExtra("farer", fare);
            intent.putExtra("date",formattedDate);
            intent.putExtra("concess",lol);
            startActivity(intent);

        }
    }
}
