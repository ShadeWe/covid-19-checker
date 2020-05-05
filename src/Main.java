import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

    public static void main(String[] args) {

        try {

            URL url = new URL("https://covid-simple-api.now.sh/api/world");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;
            StringBuilder data = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                data.append(line);
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(data.toString());

            System.out.println("Deaths: " + (String)jsonObject.get("deaths"));
            System.out.println("Total cases: " + (String)jsonObject.get("totalCases"));
            System.out.println("Active cases: " + (String)jsonObject.get("activeCases"));
            System.out.println("Closed cases: " + (String)jsonObject.get("closedCases"));
            System.out.println("Recovered: " + (String)jsonObject.get("recovered"));

        } catch(MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
