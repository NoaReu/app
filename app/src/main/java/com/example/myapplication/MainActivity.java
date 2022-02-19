package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //ImageView fbBtn; Facebook button
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager= getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.LogfragmentCon,new LoginFragment());

       // Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerionFragment2);
     /*   setContentView(R.layout.activity_main);


        googleBtn=findViewById(R.id.googleBtn);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc=GoogleSignIn.getClient(this,gso);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinintent =gsc.getSignInIntent();
                startActivity(signinintent);
            }
        });*/


    }

//google sign in
//    void signin() {
//        Intent signinintent =gsc.getSignInIntent();
//        startActivity(signinintent);
//        //Intent intent=new Intent(MainActivity.this,SecondActivity.class);
//       // startActivity(intent);
//    }


   /* public void loginFunc(View view){
        EditText emailText=findViewById(R.id.email);
        String email=emailText.getText().toString();
        EditText passText=findViewById(R.id.password);
        String password= passText.getText().toString();
        Button btn=(Button) findViewById(R.id.loginbtn);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Login success.",
                                    Toast.LENGTH_SHORT).show();
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(MainActivity.this,SecondActivity.class);
                                    startActivity(i);
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(MainActivity.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();


                        }
                    }
                });
    }
    public void registerFunc(View view){
        EditText emailText=findViewById(R.id.email);
        String email=emailText.getText().toString();
        EditText passText=findViewById(R.id.password);
        String password= passText.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Register Success.",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Register failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } );
    }*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
       /* if(requestCode==10000){
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigationToSecondActivity();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
            }
        }*/
    }
     void navigationToLoginFragment() {
        finish();
        Intent intent=new Intent(MainActivity.this,LoginFragment.class);


         startActivity(intent);
    }

}