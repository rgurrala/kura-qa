package Support;

import Pages.*;

public class WebModel {

    private LoginPage loginPage = new LoginPage();
    private ElementUtils utils = new ElementUtils();
    private HomePage homePage = new HomePage();
    private OrganisationPage organisationPage = new OrganisationPage();
    private CommonMethodsPage commonMethodsPage = new CommonMethodsPage();
    private KuraAPPpage kuraAPPpage = new KuraAPPpage();
    private JourneyPage journeyPage = new JourneyPage();
    private AlarmsPage alarmsPage = new AlarmsPage();


    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ElementUtils getUtils() {
        return utils;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public OrganisationPage getOrganisationPage() {
        return organisationPage;
    }

    public CommonMethodsPage getCommonMethodsPage() {
        return commonMethodsPage;
    }

    public KuraAPPpage getKuraAPPpage() {
        return kuraAPPpage;
    }

    public JourneyPage getJourneyPage() {
        return journeyPage;
    }

    public AlarmsPage getAlarmsPage() {
        return alarmsPage;
    }

}
