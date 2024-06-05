package br.csi.PI_Backend;

public class BackEndUrl {
    private static BackEndUrl instance;
    private String backendUrl;

    private BackEndUrl() {
        backendUrl = "http://192.168.100.3:8080/PI_Backend/";
    }

    public static synchronized BackEndUrl getInstance() {
        if (instance == null) {
            instance = new BackEndUrl();
        }
        return instance;
    }

    public String getBackendUrl() {
        return backendUrl;
    }
}
