package com.example.anurag.sample.Login;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anurag.sample.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText user_id, password;
    private Button loginButton;
    String user,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_id = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = user_id.getText().toString();
                pass = password.getText().toString();

                if (TextUtils.isEmpty(user) &&
                        TextUtils.isEmpty(pass)) {
                    user_id.setError("Enter user id");
                    password.setError("Enter password");
                    user_id.animate();
                } else {
                    insertIntoDatabase(user, pass);
                }

            }
        });


    }


    private void insertIntoDatabase(final String user, final String pass) {
        class SendAsync extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {

                String paramUser = params[0];
                String paramPass  = params[1];

                List<BasicNameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("user",user));
                nameValuePairs.add(new BasicNameValuePair("password",pass));

                try{

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://file-manager.hostinger.co.uk/6/index.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                }catch (ClientProtocolException e){

                }catch (IOException e){

                }

                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Toast.makeText(LoginActivity.this, "hi", Toast.LENGTH_SHORT).show();
            }


        }

        SendAsync sendAsync = new SendAsync();
        sendAsync.execute(user,pass);

    }




}
