package ua.bolshak.properties;

import java.util.ResourceBundle;

public class TextResources {
    private static TextResources instance;
    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "text";

    private TextResources() {

    }

    public static TextResources getInstance() {
        if (instance == null) {
            instance = new TextResources();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
