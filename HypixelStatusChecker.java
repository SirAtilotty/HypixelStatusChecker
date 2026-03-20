import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import javax.swing.JOptionPane;

public class HypixelStatusChecker {
    public static void main(String[] args) throws Exception {
        String pname = JOptionPane.showInputDialog("enter player name");
        if (pname == null || pname.isEmpty()) return;

        String apiKey = "Your Hypixel API key;

        HttpClient client = HttpClient.newHttpClient();

        String url = "https://api.mojang.com/users/profiles/minecraft/" + pname;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            JOptionPane.showMessageDialog(null, "Player Didnt Founded", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String body = response.body().replace(" ", "");
        String uuid = body.split("\"id\":\"")[1].split("\"")[0];

        String url2 = "https://api.hypixel.net/status?key=" + apiKey + "&uuid=" + uuid;
        HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(url2))
                .build();
        HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());

        if (!response2.body().contains("\"success\":true")) {
            JOptionPane.showMessageDialog(null, "Hypixel API Error", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(null, response2.body());
    }
}