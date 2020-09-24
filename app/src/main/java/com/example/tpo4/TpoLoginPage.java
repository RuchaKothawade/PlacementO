package com.example.tpo4;




import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class TpoLoginPage extends AppCompatActivity {
    EditText nm;
    EditText ps;
    Button lgn;

    MyDBHandler2 dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpo_login_page);
        nm=(EditText)findViewById(R.id.UserID);
        ps=(EditText)findViewById(R.id.pass);
        lgn=(Button)findViewById(R.id.submit);
        dbHandler = new MyDBHandler2(this, null, null, 1);
        lgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=nm.getText().toString().trim();
                String password=ps.getText().toString().trim();
                Boolean res=dbHandler.checkUser(user,password);
                if(res==true)
                {
                    Toast.makeText(TpoLoginPage.this,"Successfully LogedIn",Toast.LENGTH_SHORT).show();
                   tpohomepagemethod();
                }
                else
                {
                    Toast.makeText(TpoLoginPage.this,"LogIn Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void tpohomepagemethod()
    {
        Intent i=new Intent(this,TpoHomePage.class);
        startActivity(i);
    }
}
