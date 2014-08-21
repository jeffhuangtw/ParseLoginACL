package com.example.jefftw.parseloginacl;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends Activity {

    static final String TAG = "ParseLoginACL";

    private boolean enableUserACL = true;
    private TextView userInfo;
    private EditText username;
    private EditText password;

    private Button signupButton;
    private Button loginButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userInfo = (TextView) findViewById(R.id.userInfo);
        username = (EditText) findViewById(R.id.input_username);
        password = (EditText) findViewById(R.id.input_password);
        signupButton = (Button) findViewById(R.id.btn_signup);
        setupSignUp();
        loginButton = (Button) findViewById(R.id.btn_login);
        setupLogIn();
        logoutButton = (Button) findViewById(R.id.btn_logout);
        setupLogout();
        toggleLoginState();
    }

    private void setupSignUp() {
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                if (enableUserACL) {
                    ParseACL defaultACL = new ParseACL();
                    defaultACL.setPublicReadAccess(false);
                    user.setACL(defaultACL);
                }
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Show a simple Toast message upon successful registration
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Signed up, please log in.",
                                    Toast.LENGTH_LONG).show();
                            toggleLoginState();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Sign up Error:" + e.getMessage(), Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                });
            }
        });
    }

    private void setupLogIn() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.logInInBackground(username.getText().toString(), password.getText().toString(), new LogInCallback() {
                    public void done(com.parse.ParseUser parseUser, com.parse.ParseException e) {
                        if (e == null) {
                            // Show a simple Toast message upon successful registration
                            Toast.makeText(getApplicationContext(),
                                    "Successfully Signed up, please log in.",
                                    Toast.LENGTH_LONG).show();
                            toggleLoginState();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Login in Error:" + e.getMessage(), Toast.LENGTH_LONG)
                                    .show();
                        }
                    }

                });
            }
        });
    }

    private void setupLogout() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.logOut();
                toggleLoginState();
            }
        });
    }

    private void toggleLoginState() {
        ParseUser user = ParseUser.getCurrentUser();
        if (user != null) {
            loginButton.setVisibility(View.INVISIBLE);
            signupButton.setVisibility(View.INVISIBLE);
            logoutButton.setVisibility(View.VISIBLE);
            userInfo.setText("Current User :" + user.getUsername());
        } else {
            loginButton.setVisibility(View.VISIBLE);
            signupButton.setVisibility(View.VISIBLE);
            logoutButton.setVisibility(View.INVISIBLE);
            userInfo.setText("Please SignUp/LogIn");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
