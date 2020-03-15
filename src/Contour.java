import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlIDREF;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contour extends CommonEntity {

    private List<Installation> installations;

    public Contour() { }

    public Contour(String name, String description) { super(name, description); }

    @XmlElementWrapper
    @XmlElement(name = "installation")
    public List<Installation> getInstallations() {
        if (installations == null) {
            installations = new ArrayList<>();
        }
        return installations;
    }

    public void setInstallations(List<Installation> installations) {
        this.installations = installations;
    }

    static public class Installation {
        private AppSystem appSystemRef;
        private FuncPlatformRelease releaseRef;
        private boolean valid;

        @XmlElement
        @XmlIDREF
        public AppSystem getAppSystemRef() { return appSystemRef; }

        public void setAppSystemRef(AppSystem appSystemRef) {
            this.appSystemRef = appSystemRef;
        }

        @XmlElement
        @XmlIDREF
        public FuncPlatformRelease getReleaseRef() {
            return releaseRef;
        }

        public void setReleaseRef(FuncPlatformRelease releaseRef) {
            this.releaseRef = releaseRef;
        }

        @XmlElement
        public boolean getValid() {
            return valid;
        }

        public void setValid(boolean isValidated) {
            this.valid = isValidated;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 71 * hash + Objects.hashCode(this.appSystemRef);
            hash = 71 * hash + Objects.hashCode(this.releaseRef);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Installation other = (Installation) obj;

            if (!Objects.equals(this.appSystemRef, other.appSystemRef)) {
                return false;
            }

            if (!Objects.equals(this.releaseRef, other.releaseRef)) {
                return false;
            }

            return true;
        }
    }
}
