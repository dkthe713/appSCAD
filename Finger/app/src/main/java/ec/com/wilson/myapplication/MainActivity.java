package ec.com.wilson.myapplication;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import ec.com.wilson.myapplication.Model.EnCryptor;

public class MainActivity extends AppCompatActivity {

    private KeyStore keyStore;
    private static final String KEY_NAME = "KEYMTF";
    private Cipher cipher;
    private TextView textView,info;
    private static final String SAMPLE_ALIAS = "MYALIAS";
    private DeCryptor decryptor;
    private EnCryptor encryptor;
    private String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        info = (TextView) findViewById(R.id.info);
        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);
        FingerprintManager fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (!fingerprintManager.isHardwareDetected()) {
            Toast.makeText(this, "Huella digital no accesible, ingrese mediante usuario y contraseña", Toast.LENGTH_LONG).show();
            //Redireccion cuando el dispositivo no cuenta con sensor de huella
            Intent pantallaLogin = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(pantallaLogin);
        } else {
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(this, "Registrar almenos una huella digital en configuracion", Toast.LENGTH_SHORT).show();
            } else {
                if (!keyguardManager.isKeyguardSecure()) {
                    Toast.makeText(this, "Pantalla de bloqueo no activada", Toast.LENGTH_SHORT).show();
                } else {
                    getKey();
                }
                if (cipherInit()) {
                    FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(cipher);
                    FingerprintHandler helper = new FingerprintHandler(this);
                    helper.startAutentication(fingerprintManager, cryptoObject);
                }
            }
        }
    }

    private boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            keyStore.load(null);
            SecretKey secretKey = (SecretKey) keyStore.getKey(KEY_NAME, null);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (CertificateException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
            return false;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return false;
        } catch (KeyStoreException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void decryptText() {
//        try {
////            info.setText("info huella");
//            info.setText(decryptor.decryptData(SAMPLE_ALIAS, encryptor.getEncryption(), encryptor.getIv()));
//            a = decryptor.decryptData(SAMPLE_ALIAS, encryptor.getEncryption(), encryptor.getIv());
//        } catch (UnrecoverableEntryException | NoSuchAlgorithmException |
//                KeyStoreException | NoSuchPaddingException | NoSuchProviderException |
//                IOException | InvalidKeyException e) {
//            Log.e("Error:", "decryptData() called with: " + e.getMessage(), e);
//        } catch (IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        }
    }

    private void getKey() {
        try {
//            decryptText();
            //keyStore = KeyStore.getInstance(KeyProperties.KEY_ALGORITHM_AES,"AndroidKeyStore");
            keyStore = KeyStore.getInstance("AndroidKeyStore");
            Toast.makeText(this, "KEY EN ANDROID: "+keyStore.toString()+"-"+keyStore, Toast.LENGTH_LONG).show();
            info.setText("info huella"+" KEY EN ANDROID:"+keyStore+" - Provider:"+keyStore.getProvider()+" - Hash:"+keyStore.hashCode()+" - Class:"+keyStore.getClass());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        try {
            keyStore.load(null);
            keyGenerator.init(new KeyGenParameterSpec.Builder(KEY_NAME, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7).build());
            keyGenerator.generateKey();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}