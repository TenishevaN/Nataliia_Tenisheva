package web_jstl;

import com.springboot.fullstack.model.Status;
import com.springboot.fullstack.repository.StatusRepository;
import util.ServiceUtil;

import org.apache.logging.log4j.LogManager;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * {@ code FieldStatusFilterTag} class represents the master status tag to display the status filter.
 * <br>
 *
 * @author Tenisheva N.I.
 * @version 1.0
 */
public class FieldStatusFilterTag extends SimpleTagSupport {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(FieldStatusFilterTag.class);
    private String currentLocale;
    private int idStatus;
    private StatusRepository statusRepository;

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    @Override
    public void doTag() {
        String output = "";
        JspWriter out = getJspContext().getOut();
        output = "<select name = status_id>";
        List<Status> listStatus = statusRepository.getAll(currentLocale);
        if (idStatus == -1) {
            output += "<option  value = -1  selected>" + ServiceUtil.getKey("all", currentLocale) + "</option>";
        } else {
            output += "<option  value = -1>" + ServiceUtil.getKey("all", currentLocale) + "</option>";
        }
        for (Status status : listStatus) {
            output += formOptionForSelectField(status);
        }
        output += "</select>";

        try {
            out.println(output);
        } catch (IOException e) {
            log.debug(e.getMessage());
        }
    }

    private String formOptionForSelectField(Status status) {

        if (status.getId() == idStatus) {
            return "<option  value = " + status.getId() + " selected>" + status.getName() + "</option>";
        } else {
            return "<option  value = " + status.getId() + ">" + status.getName() + "</option>";
        }
    }
}

