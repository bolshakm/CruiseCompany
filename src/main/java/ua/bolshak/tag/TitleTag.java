package ua.bolshak.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class TitleTag extends SimpleTagSupport {
    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public void doTag() throws JspException, IOException {
        if (value != null) {
            JspWriter out = getJspContext().getOut();
            out.println(value);
        } else {
            StringWriter sw = new StringWriter();
            getJspBody().invoke(sw);
            getJspContext().getOut().println(sw.toString());
        }
    }
}
