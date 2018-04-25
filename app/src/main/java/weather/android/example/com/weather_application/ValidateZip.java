package weather.android.example.com.weather_application;


import java.util.regex.Pattern;


public class ValidateZip {
    /**
     * Regular Expression to match the US Zip-Code
     */
    static final String regex = "^[0-9]{5}(?:-[0-9]{4})?$";


    //This method returns true if the parameter string is a valid zip code
    public static boolean isAValidZipCode(String zip) {
        return Pattern.matches(regex, zip);
    }

}