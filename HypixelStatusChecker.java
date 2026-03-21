import java.net.http.HttpClient;    //SADEKX TARAFINDAN BAŞLATILIP SİR_ATİLOTTY NİN KATKILARIYLA GELİŞTİRİLMİŞTİR
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import javax.swing.*;
import java.awt.*;

public class HypixelStatusChecker {
    public static void main(String[] args) throws Exception {
        String pname = JOptionPane.showInputDialog("Enter player name:");
        if (pname == null || pname.isEmpty()) return;

        String apiKey = "your ip key here";

        HttpClient client = HttpClient.newHttpClient();


        String url = "https://api.mojang.com/users/profiles/minecraft/" + pname;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            JOptionPane.showMessageDialog(null, "Player not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Hypixel API Error!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        String avatarUrl = "https://mc-heads.net/avatar/" + uuid + "/128";
        HttpRequest avatarRequest = HttpRequest.newBuilder()
                .uri(URI.create(avatarUrl))
                .header("User-Agent", "Mozilla/5.0")
                .build();
        HttpResponse<byte[]> avatarResponse = client.send(avatarRequest, HttpResponse.BodyHandlers.ofByteArray());
        ImageIcon avatarIcon = new ImageIcon(avatarResponse.body());


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel imgLabel = new JLabel(avatarIcon);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(pname);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(8));
        panel.add(imgLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(5));
        panel.add(statusLabel);

        if (response2.body().contains("\"online\":true")) {
            statusLabel.setText("Online");
            statusLabel.setForeground(new Color(0, 180, 0));

            if (response2.body().contains("\"gameType\":")) {
                String gameType = response2.body().split("\"gameType\":\"")[1].split("\"")[0];
                JLabel gameLabel = new JLabel("Game: " + gameType);
                gameLabel.setFont(new Font("Arial", Font.PLAIN, 13));
                gameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                panel.add(Box.createVerticalStrut(5));
                panel.add(gameLabel);
            }
        } else {
            statusLabel.setText("Offline");
            statusLabel.setForeground(new Color(200, 0, 0));
        }

        JOptionPane.showMessageDialog(null, panel, "Hypixel Status", JOptionPane.PLAIN_MESSAGE);
    }
}
