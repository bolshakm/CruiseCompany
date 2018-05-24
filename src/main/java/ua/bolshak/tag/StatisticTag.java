package ua.bolshak.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class StatisticTag extends TagSupport {
    private String head;
    private int rows;

    public void setHead(String head) {
        this.head = head;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<table border='1' align='center'>");
            out.write("<thead><tr><th  colspan='2'>"+head+"</th></tr></thead>");
            out.write("<tbody><tr><td>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doAfterBody() throws JspException {
        if (rows-- > 1){
            try {
                pageContext.getOut().write("</td></tr><tr><td>");
            } catch (IOException e) {
                throw new JspTagException(e.getMessage());
            }
            return EVAL_BODY_AGAIN;
        } else {
            return SKIP_BODY;
        }
    }

    @Override
    public int doEndTag() throws JspException {
        try {
            pageContext.getOut().write("</td></tr></tbody></table>");
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_PAGE;
    }
}
