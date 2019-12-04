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
import com.google.firebase.auth.FirebaseAuthException;

public class SignUpActivity extends AppCompatActivity {

    EditText signup_email, signup_password;
    Button signup_btn;
    TextView signup_txtvw_toSingup;
    FirebaseAuth login_Authendication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        login_Authendication=FirebaseAuth.getInstance();
        signup_email =findViewById(R.id.acc_email);
        signup_password=findViewById(R.id.acc_password);
        signup_btn=findViewById(R.id.btnSignUp);
        signup_txtvw_toSingup=findViewById(R.id.loginLink);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailId=signup_email.getText().toString();
                String password=signup_password.getText().toString();
                if(emailId.isEmpty()){
                    signup_email.setError("Please Enter Email");
                    signup_email.requestFocus();
                }
                else if(password.isEmpty()){
                    signup_password.setError("Please Enter Password");
                    signup_password.requestFocus();
                }
                else if(emailId.isEmpty() && password.isEmpty()){
                    Toast.makeText(SignUpActivity.this,"Email and Password are Empty",Toast.LENGTH_SHORT).show();
                }
                else if(! (emailId.isEmpty() && password.isEmpty())){


                login_Authendication.createUserWithEmailAndPassword(emailId,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())  {

                                Toast.makeText(SignUpActivity.this,"SignUp Unsuccessful Try Again !!",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                startActivity(new Intent(SignUpActivity.this,CdtDbtActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Error Occurred Try Again !!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        signup_txtvw_toSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this,MainActivity.class);
            }
        });
    }
}
