package com.wujie.autoconnectwifi;

import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText ssidEditText;
    private EditText passwordEditText;
    private Button saveBtn;
    private WifiUtil wifiUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        wifiUtil = new WifiUtil(this);
        wifiUtil.startScan();
        for (WifiConfiguration configuration :
                wifiUtil.getConfigurations()) {

            Log.e("configuration", "ssid:"+configuration.SSID +"--id:"+ configuration.networkId +
                    "--priority" + configuration.priority + "--allowedAuthAlgorithms:"+configuration.allowedAuthAlgorithms +
            "--allowedGroupCiphers:"+configuration.allowedGroupCiphers +"--allowedKeyManagement:" +configuration.allowedKeyManagement +
                    "--allowedAuthAlgorithms:"+configuration.allowedAuthAlgorithms
            + "--allowedPairwiseCiphers:"+configuration.allowedPairwiseCiphers
            +"--hiddenSSID:"+configuration.hiddenSSID
            +"--wepTxKeyIndex:"+configuration.wepTxKeyIndex
            +"--wepKeys:"+configuration.wepKeys[0]
            +"--preSharedKey"+ configuration.preSharedKey
            +"--status:"+configuration.status);
        }

    }

    private void initView() {
        ssidEditText = (EditText) findViewById(R.id.edit_ssid);
        passwordEditText = (EditText) findViewById(R.id.eidt_password);
        saveBtn = (Button) findViewById(R.id.btn_save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                wifiUtil.mWifiManager.enableNetwork(20, true);
//                wifiUtil.mWifiManager.saveConfiguration();
//                wifiUtil.mWifiManager.reconnect();
                String ssid = ssidEditText.getText().toString().trim();
                String password = passwordEditText.getEditableText().toString().trim();
                wifiUtil.addNetWork(wifiUtil.createWifiInfo(ssid, password, password.length() == 0 ? 1 : 3));

//                for (WifiConfiguration configuration:
//                     wifiUtil.getConfigurations()) {
//                    if(configuration.SSID.equals("Tenda_1AF710")) {
//                        Boolean  connectState = wifiUtil.connectConfiguration(configuration.networkId);
//                        Log.e("configuration", configuration.SSID + configuration.networkId + "--" + configuration.priority);
//                    } else {
//                        Log.d("configuration", configuration.SSID + configuration.networkId + "--" + configuration.priority);
//                    }
//
//                }

            }
        });
    }
}
