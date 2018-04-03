package nghiepnguyen.com.lession3_prob1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Prob2Activity extends AppCompatActivity {
    TextView nameTextview;
    Button decideButton;
    EditText addFoodEditText;
    Button addFoodButton;
    List<String> arrayList = new ArrayList<>(Arrays.asList("Hamburger", "Pizza", "Mexican",
            "American", "Chinese"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prob2);

        nameTextview = findViewById(R.id.name_textview);
        addFoodEditText = findViewById(R.id.add_new_food_textview);
        decideButton = findViewById(R.id.decide_button);
        decideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random randomGenerator = new Random();
                int index = randomGenerator.nextInt(arrayList.size());
                nameTextview.setText(arrayList.get(index));
            }
        });

        addFoodButton = findViewById(R.id.add_food_button);
        addFoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(addFoodEditText.getText()))
                    Toast.makeText(Prob2Activity.this, "Input is empty", Toast.LENGTH_LONG).show();
                else {
                    String inputValue = addFoodEditText.getText().toString();
                    arrayList.add(inputValue);
                    nameTextview.setText(inputValue);
                }
            }
        });
    }
}
