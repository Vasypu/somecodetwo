import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    @XmlRootElement(name = "deploymentArchitecture")
    @XmlType(propOrder = {
            "contours",
            "applicationSystems",
            "platformReleases",
            "techPlatformMapping",
            "retiredTechPlatforms"})
    public class DeploymentArchitecture {

        private String version = "1.0";

        private List<String> applicationSystems;
        private List<String> contours;
        private List<String> platformReleases;
        private List<String> techPlatformMapping;
        private List<String> retiredTechPlatforms;

        @XmlAttribute
        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        @XmlElementWrapper
        @XmlElement(name = "applicationSystem")
        public List<String> getApplicationSystems() {
            if (applicationSystems == null) {
                //applicationSystems = Collections.EMPTY_LIST;
                applicationSystems = new ArrayList<>();
            }
            return applicationSystems;
        }
        //public void setApplicationSystems (List<String> applicationSystems) { this.applicationSystems = applicationSystems; }

        @XmlElementWrapper
        @XmlElement(name = "contour")
        public List<String> getContours() {
            if (contours == null) {
                contours = new ArrayList<>();
                //contours = Collections.EMPTY_LIST;
            }
            return contours;
        }

        /*@XmlJavaTypeAdapter(OptionalAdpaterTwo.class)
        public String getSomeCode() { return some; }*/

        @XmlElementWrapper
        @XmlElement(name = "platformRelease")
        public List<String> getPlatformReleases() {
            if (platformReleases == null) {
                platformReleases = new ArrayList<>();
                //platformReleases = Collections.EMPTY_LIST;
            }
            return platformReleases;
        }

        @XmlElementWrapper
        @XmlElement(name = "mapping")
        public List<String> getTechPlatformMapping() {
            if (techPlatformMapping == null) {
                techPlatformMapping = new ArrayList<>();
                //techPlatformMapping = Collections.EMPTY_LIST;
            }
            return techPlatformMapping;
        }

        @XmlElementWrapper
        @XmlElement(name = "entry")
        public List<String> getRetiredTechPlatforms() {
            if (retiredTechPlatforms == null) {
                retiredTechPlatforms = new ArrayList<>();
                //retiredTechPlatforms = Collections.EMPTY_LIST;
            }
            return retiredTechPlatforms;
        }

        public void setRetiredTechPlatforms(List<String> retiredTechPlatforms) {
            this.retiredTechPlatforms = retiredTechPlatforms;
        }

        public static void main(String[] args) throws JAXBException, FileNotFoundException {
            final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(DeploymentArchitecture.class.getSimpleName());
            JAXBContext context = JAXBContext.newInstance(DeploymentArchitecture.class);
            DeploymentArchitecture bean = new DeploymentArchitecture();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //Do the marshal operation
            //marshaller.marshal(bean, new FileOutputStream("/root/Загрузки/Student.xml"));
            System.out.println("java object converted to xml successfully.");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            DeploymentArchitecture unmarshal = (DeploymentArchitecture) unmarshaller.unmarshal(new File("/root/Загрузки/Student.xml"));
            //unmarshal.retiredTechPlatforms.add("asd");
            //unmarshal.retiredTechPlatforms.add("asd");

            if (unmarshal.getRetiredTechPlatforms().size() != 0) {
                System.out.println("Привет мир" + unmarshal.getRetiredTechPlatforms().size());
            }
//            if ("[]".equals(unmarshal.getContours()) && "[]".equals(unmarshal.getApplicationSystems()) &&
//                    "[]".equals(unmarshal.getPlatformReleases()) && "[]".equals(unmarshal.getTechPlatformMapping()) &&
//                    "[]".equals(unmarshal.getRetiredTechPlatforms())) {
//                System.out.println("В методе isDeploymentArchitect одна из переменных = null");
//                System.out.println("deploymentArchitect.getContours = " + unmarshal.getContours());
//                System.out.println("deploymentArchitect.getApplicationSystems = " + unmarshal.getApplicationSystems());
//                System.out.println("deploymentArchitect.getPlatformReleases = " + unmarshal.getPlatformReleases());
//                System.out.println("deploymentArchitect.getTechPlatformMapping = " + unmarshal.getTechPlatformMapping());
//                System.out.println("deploymentArchitect.getRetiredTechPlatforms = " + unmarshal.getRetiredTechPlatforms());
//            } else if (unmarshal.getContours() != null && unmarshal.getPlatformReleases() != null &&
//                    unmarshal.getRetiredTechPlatforms() != null) {
//                System.out.println("Получилось");
//                //System.out.println(a.getApplicationSystems());
//                System.out.println("suppliesGitRepository.branchName = " + unmarshal.getContours());
//                System.out.println("suppliesGitRepository.path = " + unmarshal.getPlatformReleases());
//                System.out.println("suppliesGitRepository.login = " + unmarshal.getRetiredTechPlatforms());
//            } else {
//                System.out.println("я тут был 4");
//                System.out.println("suppliesGitRepository.branchName = " + unmarshal.getContours());
//                System.out.println("suppliesGitRepository.path = " + unmarshal.getPlatformReleases());
//                System.out.println("suppliesGitRepository.login = " + unmarshal.getRetiredTechPlatforms());
//            }



        }
    }