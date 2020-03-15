import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FuncPlatformRelease extends CommonEntity {
    private List<AppSystem> appSystemRefs;
    private boolean retired;
    private Date date;

    @XmlElement(name = "appSystemRef")
    @XmlIDREF
    public List<AppSystem> getAppSystemRefs() {
        if (appSystemRefs == null) {
            appSystemRefs = new ArrayList<>();
        }

        return appSystemRefs;
    }

    public void setAppSystemRefs(List<AppSystem> appSystemRefs) {
        this.appSystemRefs = appSystemRefs;
    }

    public boolean getRetired() {
        return retired;
    }

    public void setRetired(boolean stail) {
        this.retired = stail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date createDate) {
        this.date = createDate;
    }
}
