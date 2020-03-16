import javax.xml.bind.annotation.XmlIDREF;

public class TechPlatformMapping {
    private AppSystem appSystem;
    private FuncPlatformRelease platformRelease;
    private String techPlatformFileName;

    @XmlIDREF
    public AppSystem getAppSystem() {
        return appSystem;
    }

    public void setAppSystem(AppSystem appSystem) {
        this.appSystem = appSystem;
    }

    @XmlIDREF
    public FuncPlatformRelease getPlatformRelease() {
        return platformRelease;
    }

    public void setPlatformRelease(FuncPlatformRelease platformRelease) {
        this.platformRelease = platformRelease;
    }

    public String getTechPlatformFileName() {
        return techPlatformFileName;
    }

    public void setTechPlatformFileName(String techPlatformFileName) {
        this.techPlatformFileName = techPlatformFileName;
    }
}
