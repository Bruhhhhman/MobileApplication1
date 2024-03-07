package ru.mirea.udalovna.buttonclicker;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.CheckBox;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private Button btnWhoAmI;
    private Button btnItIsNotMe;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textview);
        btnWhoAmI = findViewById(R.id.Btn1);
        btnItIsNotMe = findViewById(R.id.Btn2);
        checkBox = findViewById(R.id.checkBox);

        View.OnClickListener oclBtnWhoAmI = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("Мой номер по списку № Х");
                checkBox.toggle();
            }
        };
        btnWhoAmI.setOnClickListener(oclBtnWhoAmI);

    }
    public void onMyButtonClick(View view)
    {
        Toast.makeText(this, "Ещё один способ!", Toast.LENGTH_SHORT).show();
        checkBox.toggle();
    }

}