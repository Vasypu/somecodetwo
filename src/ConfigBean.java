import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@XmlRootElement(name = "settings")


public class ConfigBean {

    private List<AppSystem> applicationSystems;
    private List<FuncPlatformRelease> platformReleases;
    private List<String> contours;
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(ConfigBean.class.getSimpleName());
    public static final String CDS_DIR = System.getProperty("user.home") + File.separator + ".cdsgui";
    public static final String TECHPLATFORM_DIR = "techplatform";
    public static String SETTINGS_XML = CDS_DIR + File.separator + "cds-gui-config.xml";
    private static final String DEPLOY_ARCH_XML = CDS_DIR + File.separator + "deployment-architecture.xml";
    private static SysConfigBean CONFIGURATION;
    public static boolean flagRecreateCFG = false;
    private String version = "1.0";
    private String systemCatalogDvmName = "";

    @XmlAttribute
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement
    public String getSystemCatalogDvmName() {
        return systemCatalogDvmName;
    }

    public void setSystemCatalogDvmName(String systemCatalogDvmName) { this.systemCatalogDvmName = systemCatalogDvmName; }

    @XmlType
    public static class SystemsReference {
        String dvmName;

        public SystemsReference () {}

        public void setDvmName (String dvmName) { this.dvmName = dvmName; }
    }

    @XmlType
    public static class ArtifactorySettings {

        String url;
        String userName;
        String password;

        public ArtifactorySettings () {}

        public void setUrl (String url) { this.url = url; }

        @XmlElement
        public String getUrl () { return url; }

        public void setUserName (String userName) { this.userName = userName; }

        @XmlElement
        public String getUserName () { return userName; }

        public void setPassword (String password) { this.password = password; }

        @XmlElement
        public String getPassword () { return password; }
    }

    @XmlElementWrapper
    @XmlElement(name = "contour")
    public List<String> getContours() {
        if (contours == null) {
            contours = new ArrayList<>();
            //contours = Collections.EMPTY_LIST;
        }
        return contours;
    }

    @XmlElementWrapper
    @XmlElement(name = "applicationSystem")
    public List<AppSystem> getApplicationSystems() {
        if (applicationSystems == null) {
            //applicationSystems = Collections.EMPTY_LIST;
            applicationSystems = new ArrayList<>();
        }
        return applicationSystems;
    }

    @XmlElementWrapper
    @XmlElement(name = "platformRelease")
    public List<FuncPlatformRelease> getPlatformReleases() {
        if (platformReleases == null) {
            platformReleases = new ArrayList<>();
            //platformReleases = Collections.EMPTY_LIST;
        }
        return platformReleases;
    }

    public static class GitConfigBean {

        String repositoryUrl;
        String branchName;
        String path;
        String login;
        String password;

        public GitConfigBean () {}

        public void setRepositoryUrl (String repositoryUrl) { this.repositoryUrl = repositoryUrl; }

        @XmlJavaTypeAdapter(EmptyStringAdapter.class)
        public String getRepositoryUrl () { return repositoryUrl; }

        public void setBranchName (String branchName) { this.branchName = branchName; }

        @XmlJavaTypeAdapter(EmptyStringAdapter.class)
        public String getBranchName () { return branchName; }

        public void setPath (String path) { this.path = path; }

        @XmlJavaTypeAdapter(EmptyStringAdapter.class)
        public String getPath() { return path; }

        public void setLogin (String login) { this.login = login; }

        @XmlJavaTypeAdapter(EmptyStringAdapter.class)
        public String getLogin () { return login; }

        public void setPassword (String password) { this.password = password; }

        @XmlJavaTypeAdapter(EmptyStringAdapter.class)
        public String getPassword () { return password; }
    }

    SystemsReference systemsReference = new SystemsReference();

    ArtifactorySettings artifactorySettings = new ArtifactorySettings();

    GitConfigBean localPropertyGitRepository = new GitConfigBean();
    GitConfigBean dvmGitRepository = new GitConfigBean();
    GitConfigBean suppliesGitRepository = new GitConfigBean();

    public void setSystemsReference (SystemsReference systemsReference) {

        this.systemsReference = systemsReference;
    }
    @XmlElement
    public SystemsReference getSystemsReference () { return systemsReference; }

    public void setArtifactorySettings (ArtifactorySettings artifactorySettings) { this.artifactorySettings = artifactorySettings; }

    @XmlElement
    public ArtifactorySettings getArtifactorySettings () { return artifactorySettings; }

    public void setLocalPropertyGitRepository (GitConfigBean localPropertyGitRepository) { this.localPropertyGitRepository = localPropertyGitRepository; }

    @XmlElement
    public GitConfigBean getLocalPropertyGitRepository () { return localPropertyGitRepository; }

    public void setDvmGitRepository (GitConfigBean dvmGitRepository) { this.dvmGitRepository = dvmGitRepository; }

    @XmlElement
    public GitConfigBean getDvmGitRepository () { return dvmGitRepository; }

    public void setSuppliesGitRepository (GitConfigBean suppliesGitRepository) { this.suppliesGitRepository = suppliesGitRepository; }

    @XmlElement
    public GitConfigBean getSuppliesGitRepository () { return suppliesGitRepository; }

    public SysConfigBean getConfigurationBean() {
        try {

            CONFIGURATION = loadConfigration();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (CONFIGURATION == null) {
            System.out.println("Ошибка CONFIGURATION == null");
        }
        return CONFIGURATION;
    }


    SysConfigBean loadConfigration () throws JAXBException {

        File settingsFile = new File(SETTINGS_XML);
        File daFile = new File(DEPLOY_ARCH_XML);
        ConfigBean cfgSettings = null;
        DeploymentArchitecture cfgDa = null;
        if (!settingsFile.exists()) {
            createConfFile(settingsFile, new ConfigBean());
        } else {
            try {
                JAXBContext jaxbContextSettings = JAXBContext.newInstance(ConfigBean.class);
                Unmarshaller jaxbUnmarshallerSettings = jaxbContextSettings.createUnmarshaller();
                cfgSettings = (ConfigBean) jaxbUnmarshallerSettings.unmarshal(settingsFile);
            }  catch (Exception ex) {
                LOGGER.severe("Ошибка загрузки файла" + ex);
                ex.printStackTrace();
                if (flagRecreateCFG) {
                    recreateCFG(settingsFile, new ConfigBean());
                }
            }
        }
        if (!daFile.exists()) {
            createConfFile(daFile, new DeploymentArchitecture());
        } else {
            try {
                JAXBContext jaxbContextDa = JAXBContext.newInstance(DeploymentArchitecture.class);
                Unmarshaller jaxbUnmarshallerDa = jaxbContextDa.createUnmarshaller();
                cfgDa = (DeploymentArchitecture) jaxbUnmarshallerDa.unmarshal(daFile);
            } catch (Exception ex) {
                LOGGER.severe("Ошибка загрузки файла" + ex);
                ex.printStackTrace();
                if (flagRecreateCFG) {
                    recreateCFG(daFile, new DeploymentArchitecture());
                }
            }
        }
        return new SysConfigBean(cfgSettings, cfgDa);
    }

    public void recreateCFG(File file, Object obj) {

        try {
            createConfFile(file, obj);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    private static void createConfFile (File file, Object obj) throws JAXBException {

        File fileDir = new File(CDS_DIR);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        if (obj.getClass().equals(ConfigBean.class)) {
            JAXBContext context = JAXBContext.newInstance(ConfigBean.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try {
                file.createNewFile();
                marshaller.marshal(new ConfigBean(), file);
            } catch (Exception exe) {
                System.out.println("Ошибка " + exe);
            }
        } else if (obj.getClass().equals(DeploymentArchitecture.class)) {
            JAXBContext context = JAXBContext.newInstance(DeploymentArchitecture.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try {
                file.createNewFile();
                marshaller.marshal(new DeploymentArchitecture(), file);
                //Notification.show(/*resourceBundle.getString(ResourceKeys.AdminUI_File_NewFile_Caption.toString())*/"Был создан файл: " + SETTINGS_XML, Notification.Type.WARNING_MESSAGE);
            } catch (Exception exe) {
                System.out.println("Ошибка " + exe);
            }
        }
    }

    public static class EmptyStringAdapter extends XmlAdapter<String, String> {

        @Override
        public String unmarshal(String v) {
            if("".equals(v)) {
                return null;
            }
            return v;
        }

        @Override
        public String marshal(String v) {
            return v;
        }
    }
}