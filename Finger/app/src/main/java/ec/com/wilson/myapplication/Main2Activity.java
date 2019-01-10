package ec.com.wilson.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import ec.com.wilson.myapplication.Model.Materia;

public class Main2Activity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {
    public static String nombres = "nombres";
    public static String user = "user";
    RequestQueue requestQueue;
    JsonRequest jsonRequest;
    TextView nameUsuario;
    TextView tv_date;
    Spinner spinnerMaterias;
    TextView materiaSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameUsuario = (TextView) findViewById(R.id.nameUsuario);
        materiaSelected = (TextView) findViewById(R.id.tv_materiaSelected);
        tv_date = (TextView) findViewById(R.id.tv_date);
        spinnerMaterias = (Spinner) findViewById(R.id.sp_materia);

        String nombre = getIntent().getStringExtra("nombres");
        String user = getIntent().getStringExtra("user");
        nameUsuario.setText("Bienvenido " + nombre);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Mostrar fecha de loggeo
        tiempoIngreso();
        consultarMaterias(user);
    }

    /**
     * Metodo que consulta las materias segun el usuario logeado
     */
    private void consultarMaterias(String nombre) {
        String url = "https://dkbiometricouce.000webhostapp.com/listaMateriasByUser.php?user=" + nombre;
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonRequest);
    }

    private void tiempoIngreso() {
        Date fechaActual = new Date();
//        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("EEEE, dd/MM/yyyy - HH:mm:ss");
        String mostrarFecha = formatoFecha.format(fechaActual);
        tv_date.setText(mostrarFecha);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se ha encontrado materias para el horario actual " + error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getApplicationContext(), "Se ha encontrado materias para el horario actual", Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        llenarListaMaterias(jsonArray);
    }

    private void llenarListaMaterias(JSONArray jsonArray) {
        ArrayList<Materia> materiaList = new ArrayList<>();
        ArrayList<String> listaMaterias = new ArrayList<>();
        JSONObject jsonObject = null;

        listaMaterias.add("Seleccione..");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Materia materia = new Materia();
            materia.setIdMateria(jsonObject.optString("id_materia"));
            materia.setNombre(jsonObject.optString("nombre_materia"));
            materiaList.add(materia);
            listaMaterias.add(materia.getNombre());
        }
        //Combo materias
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaMaterias);

        spinnerMaterias.setAdapter(adapter);
        spinnerMaterias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(adapterView.getContext(), "Materia seleccionada: " + adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
                materiaSelected.setText(adapterView.getItemAtPosition(i).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * Metodo que consulta las materias segun el usuario logeado
     */
    private void consultarEstudiantes(String nombre) {
        String url = "https://dkbiometricouce.000webhostapp.com/listaMateriasByUser.php?user=" + nombre;
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonRequest);
    }
}
