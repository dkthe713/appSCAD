package ec.com.wilson.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

class FingerprintHandler extends  FingerprintManager.AuthenticationCallback{
    private Context context;

    public FingerprintHandler(Context context) {
        this.context = context;
    }

    public void startAutentication(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context,Manifest.permission.USE_FINGERPRINT)!=PackageManager.PERMISSION_GRANTED){
            return;
        }
        fingerprintManager.authenticate(cryptoObject, cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        Toast.makeText(context,"Ha fallado",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        context.startActivity(new Intent(context, LoginActivity.class));
    }
}
