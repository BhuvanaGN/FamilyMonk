package in.bhuvana.familymonk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText login_email, login_password;
    Button login_btn;
    TextView login_txtvw_toSingup;
    FirebaseAuth login_Authendication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_Authendication=FirebaseAuth.getInstance();
       login_email =findViewById(R.id.acc_email);
       login_password=findViewById(R.id.acc_password);
       login_btn=findViewById(R.id.btnLogin);
       login_txtvw_toSingup=findViewById(R.id.singUpLink);
       login_btn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String emailId=login_email.getText().toString();
              String password=login_password.getText().toString();
              if(emailId.isEmpty()){
                  login_email.setError("Please Enter Email");
                  login_email.requestFocus();
              }
              else if(password.isEmpty()){
                  login_password.setError("Please Enter Password");
                  login_password.requestFocus();
              }
             else if(emailId.isEmpty() && password.isEmpty()){
                  Toast.makeText(MainActivity.this,"Email and Password are Empty",Toast.LENGTH_SHORT).show();
              }
              else if(! ( emailId.isEmpty() && password.isEmpty())){
                login_Authendication.signInWithEmailAndPassword(emailId,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Login Error.Please try Again",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(new Intent(MainActivity.this,CdtDbtActivity.class));
                        }
                    }
                });
              }
              else{
                  Toast.makeText(MainActivity.this,"Error Occurred Try Again !!",Toast.LENGTH_SHORT).show();
              }
          }
      });
       login_txtvw_toSingup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i =new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(i);
           }
       });
    }
}
