public class AppSystem extends CommonEntity {
    //private GitConfig gitConfig;
    private ConfigBean.GitConfigBean configBean;
    //private GitConfigBean configBean;

    public AppSystem() { }

    public AppSystem(String name, String description) {
        super(name, description);
    }

    //public GitConfig getGitConfig() { return gitConfig; }

    public ConfigBean.GitConfigBean getGitConfigBean() { return configBean; }
    //public GitConfigBean getGitConfigBean() { return configBean; }

    public void setGitConfigBean(ConfigBean.GitConfigBean configBean) { this.configBean = configBean; }
    //public void setGitConfigBean(GitConfigBean configBean) { this.configBean = configBean; }

    //public void setGitConfig(GitConfig gitConfig) { this.gitConfig = gitConfig; }
}
