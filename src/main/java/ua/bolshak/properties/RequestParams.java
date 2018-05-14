package ua.bolshak.properties;

import java.util.ResourceBundle;

public class RequestParams {
    private static RequestParams instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "requestparams";

    private RequestParams() {

    }

    public static RequestParams getInstance() {
        if (instance == null) {
            instance = new RequestParams();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
