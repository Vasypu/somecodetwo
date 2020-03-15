public class SysConfigBean {
    DeploymentArchitecture deploymentArchitecture;
    ConfigBean configurationBean;

    SysConfigBean (ConfigBean cfgSettings, DeploymentArchitecture cfgDa) {
        this.configurationBean = cfgSettings;
        this.deploymentArchitecture = cfgDa;
    }

    public DeploymentArchitecture getDeploymentArchitecture () { return deploymentArchitecture; }

    public ConfigBean getCfgSettings () { return configurationBean; }
}
