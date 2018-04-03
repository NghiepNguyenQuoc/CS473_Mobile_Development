package nghiepnguyen.com.lession3_prob1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Prob1Activity extends AppCompatActivity {
    EditText inputEditText;
    TextView outputTextView;
    private final double kgPerPound = 0.45359237;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prob1);

        inputEditText = findViewById(R.id.edInput);
        outputTextView = findViewById(R.id.edOutput);
        Button btnConvert = findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(inputEditText.getText()))
                    Toast.makeText(Prob1Activity.this, "Input is empty", Toast.LENGTH_LONG).show();
                else if (!TextUtils.isDigitsOnly(inputEditText.getText()))
                    Toast.makeText(Prob1Activity.this, "Input is only a number", Toast.LENGTH_LONG).show();
                else {
                    double value = Double.parseDouble(inputEditText.getText().toString()) * kgPerPound;
                    outputTextView.setText(value + "");
                }
            }
        });
    }
}
