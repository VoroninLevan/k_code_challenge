package provider;

import org.openqa.selenium.WebDriver;

public class Driver {

    private String mProfile;

    public Driver(ParameterReader parameterReader){
        mProfile = parameterReader.getProfile();
    }

    public void setBrowser(){
        switch (mProfile){
            case "Firefox":
                int x = 1;
                break;
            case "Chrome":
                break;
        }
    }
}
