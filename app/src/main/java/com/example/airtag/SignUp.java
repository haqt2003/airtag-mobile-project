package com.example.airtag;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class SignUp extends AppCompatActivity {

    private static final String TAG = "SignUp";
    private static final String URL = "https://us-central1-devfesst24.cloudfunctions.net/sendEmailAPI";

    TextView goToLogin;
    EditText email, password, confirmPassword;
    Button submit;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        client = new OkHttpClient();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        submit = findViewById(R.id.submit);
        goToLogin = findViewById(R.id.goToLogin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = email.getText().toString();
                sendEmail(emailInput);
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void sendEmail(String emailInput) {
        // Create JSON body for the request
        String jsonInputString = "{\"email\": \"" + emailInput + "\"}";

        // Create request body
        RequestBody body = RequestBody.create(
                jsonInputString, MediaType.parse("application/json; charset=utf-8"));

        // Create request
        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        // Perform network request asynchronously
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Failed to send email: ", e);
                runOnUiThread(() -> Toast.makeText(SignUp.this, "Failed to send email", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    runOnUiThread(() -> Toast.makeText(SignUp.this, "Account register successfully", Toast.LENGTH_SHORT).show());
                } else {
                    Log.e(TAG, "Email sending failed: " + response.code());
                    runOnUiThread(() -> Toast.makeText(SignUp.this, "Failed to send email", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}
