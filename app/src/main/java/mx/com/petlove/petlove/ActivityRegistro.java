package mx.com.petlove.petlove;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


import java.util.Calendar;

public class ActivityRegistro extends AppCompatActivity implements View.OnClickListener {

    Button btnFecha;
    EditText txtFechaReg;
    private int dia,mes,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setTitle("Registro");
        btnFecha = (Button) findViewById(R.id.btnCalendario);
        txtFechaReg = (EditText) findViewById(R.id.txtRegisterFecha);

        txtFechaReg.setTag(txtFechaReg.getKeyListener());
        txtFechaReg.setKeyListener(null);


        btnFecha.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCalendario:
                final Calendar calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                ano = calendario.get(Calendar.YEAR);

                final DatePickerDialog picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtFechaReg.setText((dayOfMonth)+"/"+(month+1)+"/"+(year));
                    }
                }, dia, mes, ano);
                picker.updateDate(2000,0,1);
                picker.show();
                break;
        }
    }
}
