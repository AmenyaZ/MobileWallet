package com.example.mobilewallet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;



public class Login extends AppCompatActivity {

    private TextView About;
    private EditText ID;
    private EditText Pin;
    private Button Login;
    private String baseUrl;
    private String CustomerID;
    private String CustomerPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        About = findViewById(R.id.tvAbout);
        ID = findViewById(R.id.etID);
        Pin = findViewById(R.id.etpin);
        Login = findViewById(R.id.btnLogIn);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    CustomerID = ID.getText().toString();
                    CustomerPin = Pin.getText().toString();

                    ApiClient apiClient =
                            new ApiClient(
                                    baseUrl
                                    , CustomerID
                                    , CustomerPin
                            );

                    AsyncTask<Void, Void, String> execute = new ExecuteNetworkOperation(apiClient);
                    execute.execute();
                } catch (Exception ex) {
                }
            }
        });
    }
    public class ExecuteNetworkOperation extends AsyncTask<Void, Void, String> {

        private ApiClient apiClient;
        private String isValidCredentials;

        /**
         * Overload the constructor to pass objects to this class.
         */
        public ExecuteNetworkOperation(ApiClient apiAuthenticationClient) {
            this.apiClient = apiClient;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Display the progress bar.
           // findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                isValidCredentials = apiClient.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Hide the progress bar.
           // findViewById(R.id.loadingPanel).setVisibility(View.GONE);

            // Login Success
            if (isValidCredentials.equals("true")) {
                goToHomepage();
            }
            // Login Failure
            else {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Open a new activity window.
     */
    private void goToHomepage() {
        Bundle bundle = new Bundle();
        bundle.putString("Customer ID", CustomerID);
        bundle.putString("Customer Pin", CustomerPin);
        bundle.putString("baseUrl", baseUrl);

        Intent intent = new Intent(this, Homepage.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

