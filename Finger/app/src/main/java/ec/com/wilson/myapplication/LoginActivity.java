package ec.com.wilson.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import ec.com.wilson.myapplication.Model.Usuario;

public class LoginActivity extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    RequestQueue requestQueue;
    JsonRequest jsonRequest;
    EditText et_User;
    EditText et_Password;
    Button btn_Ingresar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_User = (EditText) findViewById(R.id.et_User);
        et_Password = (EditText) findViewById(R.id.et_Pass);
        btn_Ingresar = (Button) findViewById(R.id.btn_Ingresar);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        btn_Ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        String url = "https://dkbiometricouce.000webhostapp.com/loginUsuario.php?user=" + et_User.getText().toString() + "&password=" + et_Password.getText().toString();
        jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        requestQueue.add(jsonRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(), "No se ha encontrado el usuario " + et_User.getText().toString() + error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(JSONObject response) {
        Usuario usuario = new Usuario();
        Toast.makeText(getApplicationContext(), "Se ha encontrado el usuario " + et_User.getText().toString(), Toast.LENGTH_SHORT).show();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try {
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));
            usuario.setPassword(jsonObject.optString("password"));
            usuario.setNombre(jsonObject.optString("nombre"));
            usuario.setApellido(jsonObject.optString("apellido"));
            usuario.setCedula(jsonObject.optString("cedula"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), "Usuario " + usuario.toString(), Toast.LENGTH_LONG).show();

        Intent pantallaBienvenida = new Intent(getApplicationContext(), Main2Activity.class);
        String nombres = usuario.getNombre() + " " + usuario.getApellido();
        pantallaBienvenida.putExtra(Main2Activity.nombres, nombres);
        pantallaBienvenida.putExtra(Main2Activity.user, usuario.getUser());
        startActivity(pantallaBienvenida);
    }
}
