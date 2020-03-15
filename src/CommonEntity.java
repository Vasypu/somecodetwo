import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name", "description"})
public class CommonEntity {
    private String name = "";
    private String description = "";

    public CommonEntity() {
    }

    public CommonEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @XmlElement
    @XmlID
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}