package com.example.a08_01_dwaekrany;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private void confirm() {
        String number = "ZG/" +
                (1000 + new java.util.Random().nextInt(9000));

        Intent result = new Intent();
        result.putExtra(EXTRA_NUMBER, number);

        setResult(RESULT_OK, result);
        finish();
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btnConfirm).setOnClickListener(
                v -> confirm()
        );

        Intent intent = getIntent();

        String name = intent.getStringExtra(EXTRA_NAME);
        String email = intent.getStringExtra(EXTRA_EMAIL);
        int level = intent.getIntExtra(EXTRA_LEVEL, 0);
        boolean newsletter = intent.getBooleanExtra(EXTRA_NEWSLETTER, false);

        String summary =
                "Imię: " + name +
                        "\nE-mail: " + email +
                        "\nPoziom: " + level +
                        "\nNewsletter: " + (newsletter ? "tak" : "nie");

        TextView tvSummary = findViewById(R.id.tvSummary);
        tvSummary.setText(summary);
    }

    public static final String EXTRA_NAME =
            "com.example.dwaekrany.NAME";

    public static final String EXTRA_EMAIL =
            "com.example.dwaekrany.EMAIL";

    public static final String EXTRA_LEVEL =
            "com.example.dwaekrany.LEVEL";

    public static final String EXTRA_NEWSLETTER =
            "com.example.dwaekrany.NEWSLETTER";
    public static final String EXTRA_NUMBER =
            "com.example.dwaekrany.NUMBER";


}