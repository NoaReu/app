package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    String userName;
    String userEmail;
    FragmentManager fragmentManager;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //GoogleSignInClient mGoogleSignInClient;


    //should we do those as static attributes???
    int shiftNum;
    int shiftDay;
    int shiftMonth;
    int shiftYear;
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
        shiftNum = 0;
        shiftDay = 0;
        shiftMonth = 0;
        shiftYear = 0;
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.LogfragmentCon, new NFragment()).commit();

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
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.logFragS, new FragmentReg()).addToBackStack(null).commit();

    }

    public void regToLog() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String emailStr = ((EditText) findViewById(R.id.emailReg)).getText().toString();
        String passStr = ((EditText) findViewById(R.id.passwordReg)).getText().toString();

        mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            fragmentTransaction.replace(R.id.regFramS, new NFragment()).addToBackStack(null).commit();
                            Toast.makeText(MainActivity.this, "success to register", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "faild to register", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void loginFunc() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        String emailLog = ((EditText) findViewById(R.id.emailLog)).getText().toString();
        String passLog = ((EditText) findViewById(R.id.passwordLog)).getText().toString();
        mAuth.signInWithEmailAndPassword(emailLog, passLog)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            userEmail = emailLog;
                            fragmentTransaction.replace(R.id.logFragS, new HomeFragment()).addToBackStack(null).commit();
                        } else {
                            Toast.makeText(MainActivity.this, "hahahaha you faild to login", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void moveToLogin() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.regFramS, new NFragment()).addToBackStack(null).commit();
    }

    public void signOutFunc() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrag, new NFragment()).addToBackStack(null).commit();

        FirebaseAuth.getInstance().signOut();
    }

    public void paycheckFragFunc() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrag, new PaycheckFragment()).addToBackStack(null).commit();

    }

    public void shiftsFragFunc() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.homeFrag, new ShiftFragment()).addToBackStack(null).commit();

    }

    public void pickADay(CalendarView calendarView, int day, int month, int year) {

        this.shiftDay = day;
        this.shiftMonth = month + 1;
        this.shiftYear = year;

        String date = day + "/" + month + 1 + "/" + year;
        Toast.makeText(this, date, Toast.LENGTH_LONG).show();

    }

    public void setShiftPick(int sNum) {
        this.shiftNum = sNum;

    }


    public void updateNewShift() {
        //Todo: if every thing is good- send the new shift to the firebase

        //get from firebase the shifts of this date for this user.

        //if it is empty - create the new date at the firebase

        //else: if the parameters are not the same as returned from firebase- update firebase

    }
}