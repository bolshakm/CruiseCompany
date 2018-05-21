package ua.bolshak.properties;

import java.util.Locale;
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

    public void setUkrainianLocal(){
        resource = ResourceBundle.getBundle(BUNDLE_NAME, new Locale("uk", "UA"));
    }

    public void setEnglishLocal(){
        resource = ResourceBundle.getBundle(BUNDLE_NAME);
    }

    public String getProperty(String key) {
        return resource.getString(key);
    }
}
