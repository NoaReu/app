package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    //GoogleSignInClient mGoogleSignInClient;
    String userEmail;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        /*GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);*/
        fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.LogfragmentCon,new NFragment()).commit();

    }

    @Override
    public void onStart() {
        super.onStart();
       /* GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);*/
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       // callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void logToReg() {
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.logFragS,new FragmentReg()).addToBackStack(null).commit();

    }

    public void regToLog(){
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        String emailStr=((EditText) findViewById(R.id.emailReg)).getText().toString();
        String passStr=((EditText) findViewById(R.id.passwordReg)).getText().toString();

        mAuth.createUserWithEmailAndPassword(emailStr,passStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            fragmentTransaction.replace(R.id.regFramS,new NFragment()).addToBackStack(null).commit();
                            Toast.makeText(MainActivity.this,"success to register",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this,"faild to register",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void loginFunc() {
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();

        String emailLog=((EditText) findViewById(R.id.emailLog)).getText().toString();
        String passLog=((EditText) findViewById(R.id.passwordLog)).getText().toString();
        mAuth.signInWithEmailAndPassword(emailLog,passLog)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userEmail=emailLog;
                            fragmentTransaction.replace(R.id.logFragS,new HomeFragment()).addToBackStack(null).commit();
                        } else {
                            Toast.makeText(MainActivity.this,"hahahaha you faild to login",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void moveToLogin() {
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.regFramS,new NFragment()).addToBackStack(null).commit();
    }

    public void signOutFunc() {
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrag,new NFragment()).addToBackStack(null).commit();

        FirebaseAuth.getInstance().signOut();
    }

    public void paycheckFragFunc() {
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrag,new PaycheckFragment()).addToBackStack(null).commit();

    }

    public void shiftsFragFunc() {
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrag,new ShiftFragment()).addToBackStack(null).commit();

    }
}