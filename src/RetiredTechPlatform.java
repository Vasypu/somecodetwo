import javax.xml.bind.annotation.XmlIDREF;

public class RetiredTechPlatform {
    private AppSystem appSystem;
    private String techPlatformFileName;

    @XmlIDREF
    public AppSystem getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(AppSystem appSystem) {
        this.appSystem = appSystem;
    }

    public String getTechPlatformFileName() {
        return techPlatformFileName;
    }

    public void setTechPlatformFileName(String techPlatformFileName) {
        this.techPlatformFileName = techPlatformFileName;
    }
}
