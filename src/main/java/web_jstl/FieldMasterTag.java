package web_jstl;

import com.springboot.fullstack.model.AccountLocalization;
import com.springboot.fullstack.model.Language;
import com.springboot.fullstack.model.User;
import com.springboot.fullstack.repository.UserRepository;

import org.apache.logging.log4j.LogManager;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * {@ code FieldMasterTag} class represents the custom tag to display master for the different roles.
 * <br>
 *
 * @author Tenisheva N.I.
 * @version 1.0
 */
public class FieldMasterTag extends SimpleTagSupport {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(FieldMasterTag.class);

    private int idMaster;
    private String nameRole;
    private String currentLocale = "en";
    private String area;
    private int idLocale;
    private User currentMaster;
    private String output;
    private Map<User, List<AccountLocalization>> listMaster;
    private UserRepository userRepository;

    public void setCurrentLocale(String currentLocale) {
        this.currentLocale = currentLocale;
    }

    public void setIdMaster(String idMaster) {

        System.out.println(" currentLocale m " + currentLocale);
        System.out.println(" idMaster " + idMaster);
        if(!idMaster.isEmpty()) {
            this.idMaster = Integer.parseInt(idMaster);
            currentMaster = userRepository.getById(Long.valueOf(idMaster));
        }
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setListMaster(Map<User, List<AccountLocalization>> listMaster) {
        this.listMaster = listMaster;
    }

    @Override
    public void doTag() {

        JspWriter out = getJspContext().getOut();
        String output = getOutput();
        try {
            out.println(output);
        } catch (IOException e) {
            log.debug(" FieldMasterTag exception {} ", e.getMessage());
        }
    }

    private String getOutput() {


        idLocale = Language.getId(currentLocale);
        if (area.equals("list")) {
            return formOutPutForList();
        }
        if (("admin".equals(nameRole)) || ("manager".equals(nameRole))) {
            return formSelectMasterField();
        }
        return formOutPutFieldName();
    }

    private String formOutPutFieldName() {

        if (currentMaster != null) {
            try {
                return getNameForList();
            } catch (Exception ex) {
                log.debug(" master tag without name for localization {} ", currentLocale);
            }
        }
        return "";
    }

    private String formOutPutForList() {

        if (currentMaster != null) {
            try {
                return output = getNameForList();
            } catch (Exception ex) {
                log.debug(" master tag without name for localization {} ", currentLocale);
            }
        }
        return "";
    }

    private String getNameForList() {

        String name = listMaster.entrySet().stream()
                .filter(map -> map.getKey().equals(currentMaster))
                .findFirst()
                .get().getValue().stream()
                .filter(c -> c.getLanguageId() == idLocale)
                .findFirst().get().getName();
        if (name != null) {
            return name;
        }
        return "";
    }

    private String formSelectMasterField() {

        output = "<select name = masterId>";
        if (idMaster == -1) {
            output += "<option  value = -1></option>";
        }
        for (Map.Entry<User, List<AccountLocalization>> master : listMaster.entrySet()) {

            output += formOptionForSelectField(master);
        }
        output += "</select>";
        return output;
    }

    private String formOptionForSelectField(Map.Entry<User, List<AccountLocalization>> master) {

        String name = getNameForSelectMenu(master);
        if (master.getKey().getId() == idMaster) {
            return "<option  value=" + master.getKey().getId() + " selected>" + name + "</option>";
        }
        return "<option  value=" + master.getKey().getId() + ">" + name + "</option>";
    }

    private String getNameForSelectMenu(Map.Entry<User, List<AccountLocalization>> master) {

        try {
            String name = master.getValue().stream().filter(c -> c.getLanguageId() == idLocale).findFirst().get().getName();
            if (name != null) {
                return name;
            }
            return "";
        } catch (Exception ex) {
            log.debug(" master tag without name for localization {} ", currentLocale);
            return "";
        }
    }
}

