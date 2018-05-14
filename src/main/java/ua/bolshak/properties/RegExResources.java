package ua.bolshak.properties;

import java.util.ResourceBundle;

public class RegExResources {
    private static RegExResources instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "regex";

    private RegExResources() {

    }

    public static RegExResources getInstance() {
        if (instance == null) {
            instance = new RegExResources();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
