package ua.bolshak.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class CustomTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        try{
            JspWriter writer = pageContext.getOut();
            writer.write("Author Bolshak Maksym");

        } catch (IOException e){
            throw new JspException();
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

}
