package com.yukado.greenraffle.service;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;

import java.util.HashMap;
import java.util.Map;

public class APIContextProvider {

    private static APIContext apiContext = null;

    // Deine PayPal-Konto-Credentials
    private static final String CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String CLIENT_SECRET = "YOUR_CLIENT_SECRET";
    private static final String MODE = "sandbox";  // oder "live"

    public static APIContext getAPIContext() {
        if (apiContext == null) {
            try {
                // Konfigurationseinstellungen für den APIContext
                Map<String, String> sdkConfig = new HashMap<>();
                sdkConfig.put("mode", MODE);

                // Erstelle ein OAuth Token Credential
                OAuthTokenCredential authTokenCredential = new OAuthTokenCredential(
                        CLIENT_ID, CLIENT_SECRET, sdkConfig
                );

                // Erstelle den APIContext
                apiContext = new APIContext(authTokenCredential.getAccessToken());
                apiContext.setConfigurationMap(sdkConfig);
            } catch (PayPalRESTException e) {
                e.printStackTrace();
                // Fehlerbehandlung falls erforderlich
            }
        }
        return apiContext;
    }
}
