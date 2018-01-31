import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 *  
 *
 * @author Keith Wedinger <br>
 * Created On: 1/30/18
 */

public class CountryCode {
    String name;
    String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    static String getCountryCode(String country) throws Exception {
        if (country == null || country.isEmpty()) {
            throw new IllegalArgumentException("country parameter is missing or empty");
        }
        final String baseURL = "https://2ptbg09f8e.execute-api.us-east-1.amazonaws.com/Development/countrycode?country=";
        URL url = new URL(baseURL + URLEncoder.encode(country, "UTF-8"));
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");

        int responsecode = conn.getResponseCode();
        String code;
        if (200 == responsecode) {
            // Get the response data
            StringBuffer responseBuffer = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String responseLine;
            while ((responseLine = in.readLine()) != null) {
                responseBuffer.append(responseLine);
            }
            in.close();

            // Parse the JSON into the response object
            Gson gson = new Gson();
            CountryCode countryCode = gson.fromJson(responseBuffer.toString(), CountryCode.class);
            code = countryCode.getCode();
        } else {
            throw new RuntimeException("Attempt to get country code failed");
        }

        return code;
    }

}
