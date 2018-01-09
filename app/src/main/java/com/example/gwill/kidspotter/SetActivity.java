package com.example.gwill.kidspotter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Set;

public class SetActivity extends AppCompatActivity {

    private final int REQUEST_ENABLE_BT;
    private Set<BluetoothDevice> devices;
    {
        REQUEST_ENABLE_BT = 0;
    }

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        //code qui vérifie la présence du bt
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter == null) {
            Toast.makeText( getApplicationContext(), "Pas de Bluetooth",
                    Toast.LENGTH_SHORT).show();
            } else
            Toast.makeText(getApplicationContext(), "Avec Bluetooth",
                    Toast.LENGTH_SHORT).show();
        //demande a l'utilisateur d'activer le bt
        assert bluetoothAdapter != null;
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        //permet d'obtenir la liste des devices deja connus
        devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice blueDevice : devices) {
            Toast.makeText(getApplicationContext(), "Device = " + blueDevice.getName(), Toast.LENGTH_SHORT).show();
        }
        //permet de rendre visible le device
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);



    }
}
