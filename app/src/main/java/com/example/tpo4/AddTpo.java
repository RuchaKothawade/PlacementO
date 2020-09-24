package com.example.tpo4;




import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

public class AddTpo extends AppCompatActivity {

    TextView lst;
    EditText tponame;
    EditText tpoid;
    Button s,u,del,disp;

    Button sl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tpo2);

        s=(Button)findViewById(R.id.btnfind);
        u=(Button)findViewById(R.id.btnupdate);
        del=(Button)findViewById(R.id.btndelete);
        disp=(Button)findViewById(R.id.btnload);

        lst = (TextView) findViewById(R.id.list);
        tpoid = (EditText) findViewById(R.id.tpoID);
        tponame = (EditText) findViewById(R.id.tpoName);


    }
   
    public void addTpo(View view) {
        MyDBHandler2 dbHandler = new MyDBHandler2(this, null, null, 1);
        String id = tpoid.getText().toString();
        String name = tponame.getText().toString();
        Tpo t=new Tpo(id,name);
        dbHandler.addHandler(t);
        tpoid.setText("");
        tponame.setText("");
    }

    public void findTpo (View view) {
        MyDBHandler2 dbHandler = new MyDBHandler2(this, null, null, 1);
        Tpo t4 = dbHandler.findHandler(tponame.getText().toString());
        if (t4 != null) {
            lst.setText(String.valueOf(t4.getID()) +" "+ t4.getName());

        } else {
            lst.setText("No Match Found");
        }
    }

    public void loadTpo(View view) {
        MyDBHandler2 dbHandler = new MyDBHandler2(this, null, null, 1);
        lst.setText(dbHandler.loadHandler());
        tpoid.setText("");
        tponame.setText("");
    }

    public void deleteTpo(View view) {
        MyDBHandler2 dbHandler = new MyDBHandler2(this, null, null, 1);
        boolean result = dbHandler.deleteHandler(
                tpoid.getText().toString());
        if (result) {
            tpoid.setText("");
            tponame.setText("");
            lst.setText("Record Deleted");
        } else
            tpoid.setText("No Match Found");
    }

    public void updateTpo(View view) {
        MyDBHandler2 dbHandler = new MyDBHandler2(this, null, null, 1);
        boolean result = dbHandler.updateHandler(tpoid.getText().toString(), tponame.getText().toString());
        if (result) {
            tpoid.setText("");
            tponame.setText("");
            lst.setText("Record Updated");
        } else
            tpoid.setText("No Match Found");
    }
}

