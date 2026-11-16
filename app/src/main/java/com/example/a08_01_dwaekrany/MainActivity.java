package com.example.a08_01_dwaekrany;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;



public class MainActivity extends AppCompatActivity {
    private final ActivityResultLauncher<Intent> summaryLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            String number = result.getData()
                                    .getStringExtra(SummaryActivity.EXTRA_NUMBER);

                            Toast.makeText(
                                    this,
                                    "Zapisano: " + number,
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.btnNext).setOnClickListener(v -> {
            Intent intent = new Intent(this, SummaryActivity.class);

            intent.putExtra(SummaryActivity.EXTRA_NAME,
                    etName.getText().toString());

            intent.putExtra(SummaryActivity.EXTRA_EMAIL,
                    etEmail.getText().toString());

            intent.putExtra(SummaryActivity.EXTRA_LEVEL,
                    sbLevel.getProgress());

            intent.putExtra(SummaryActivity.EXTRA_NEWSLETTER,
                    cbNewsletter.isChecked());

            summaryLauncher.launch(intent);
        });

        Intent intent = new Intent(this, SummaryActivity.class);

        intent.putExtra(SummaryActivity.EXTRA_NAME,
                etName.getText().toString());

        intent.putExtra(SummaryActivity.EXTRA_EMAIL,
                etEmail.getText().toString());

        intent.putExtra(SummaryActivity.EXTRA_LEVEL,
                sbLevel.getProgress());

        intent.putExtra(SummaryActivity.EXTRA_NEWSLETTER,
                cbNewsletter.isChecked());

        startActivity(intent);

    }


}