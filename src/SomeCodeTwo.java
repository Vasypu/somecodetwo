public class SomeCodeTwo {

    public static void main(String[] args) {

        ConfigBean bean = new ConfigBean();

        SysConfigBean CFG = bean.getConfigurationBean();
        CFG.getCfgSettings().getApplicationSystems().forEach(appSystem -> {
            
                    System.out.println("appSystem.getName = " + appSystem.getName());
        });

        CFG.getCfgSettings().getContours().forEach(as -> {
            System.out.println("as " + as);
        });

        AppSystem appSystem = new AppSystem();
        System.out.println(appSystem.getName());

        Contour contour = new Contour();
        if (contour == null) {
            System.out.println("contour == null");
        } else {
            System.out.println("Я тут был 1");
            contour.getInstallations().forEach(as -> {
                System.out.println("Я тут был 2");
                System.out.println("as " + as);
            });
        }

        CFG.getDeploymentArchitecture().getContours().forEach(as -> {
            System.out.println("as " + as);
        });

    }
}