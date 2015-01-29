package com.farruxbahodirov.euler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.edmodo.rangebar.RangeBar;

public class MyActivity extends Activity implements View.OnClickListener {
    EditText eq;           //Поле для уравнения
    EditText y0;                    //У0
    EditText a;                     // начальные координаты
    EditText b;                     // конечные координаты
    EditText eps;                   //точность
    EditText answer;                //полный ответ
    TextView shortAnswer;           //ответ
    RangeBar rangeBar;              //интервал

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//элементы UI
        Button button = (Button) findViewById(R.id.button);//кнопка "Вычисления"
        button.setOnClickListener(this);//
        y0 = (EditText) findViewById(R.id.y0);
        eq = (EditText) findViewById(R.id.eq);
        a = (EditText) findViewById(R.id.a);
        b = (EditText) findViewById(R.id.b);
        eps = (EditText) findViewById(R.id.eps);
        answer = (EditText) findViewById(R.id.answer);
        shortAnswer = (TextView) findViewById(R.id.shortAnswer);
        rangeBar = (RangeBar) findViewById(R.id.rangeBar);
        rangeBar.setTickCount(10);
        rangeBar.setTickHeight(5);
        rangeBar.setThumbIndices(Integer.parseInt(a.getText().toString()) + 2, Integer.parseInt(b.getText().toString()) + 2);
        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onIndexChangeListener(RangeBar rangeBar, int leftThumbIndex, int rightThumbIndex) {
                a.setText(String.valueOf(leftThumbIndex - 2));
                b.setText(String.valueOf(rightThumbIndex - 2));
            }
        });
    }

    @Override
    public void onClick(View view) {
        calculate();
    }

    private void calculate() {
        EulerMethod euler = new EulerMethod(eq.getText().toString(), y0.getText().toString(),
                a.getText().toString(),
                b.getText().toString(),
                eps.getText().toString()
        );

        try {
            EulerAnswer eulerAnswer=euler.calculate();
            answer.setText(eulerAnswer.getAnswerString());
            shortAnswer.setText("x=" + String.format("%.2f", eulerAnswer.getX()) + "   y=" + String.format("%1.2f", eulerAnswer.getY()) + "\n");
            answer.requestFocus();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
            builder.setTitle(R.string.errorTitle);
            builder.setMessage(R.string.errorMessage);
            builder.setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    eq.requestFocus();
                }
            });
            builder.show();
        }
    }
}
