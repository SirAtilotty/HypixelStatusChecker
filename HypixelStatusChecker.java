import java.net.http.HttpClient;   //PROJE SADEKX TARAFINDAN BAŞLATILIP SİRATİLOTTY TARAFINDAN GELİŞTİRİLMİŞTİR
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.prefs.Preferences;
import javax.swing.*;
import java.awt.*;

public class HypixelStatusChecker {
    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences.userNodeForPackage(HypixelStatusChecker.class);
        String apiKey = prefs.get("apiKey", null);

        if (apiKey == null || apiKey.isEmpty()) {
            apiKey = JOptionPane.showInputDialog(null, "Enter Your API Key:", "API Key", JOptionPane.PLAIN_MESSAGE);
            if (apiKey == null || apiKey.isEmpty()) return;
            prefs.put("apiKey", apiKey);
        }

        String pname = JOptionPane.showInputDialog("Enter player name:");
        if (pname == null || pname.isEmpty()) return;

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
            prefs.remove("apiKey");
            JOptionPane.showMessageDialog(null, "Invalid API Key Please Try Again", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        String url3 = "https://api.hypixel.net/player?key=" + apiKey + "&uuid=" + uuid;
        HttpRequest request3 = HttpRequest.newBuilder()
                .uri(URI.create(url3))
                .build();
        HttpResponse<String> response3 = client.send(request3, HttpResponse.BodyHandlers.ofString());

        
        String rank = "No Rank";
        Color rankColor = Color.GRAY;

        if (response3.body().contains("\"monthlyPackageRank\":\"SUPERSTAR\"")) {
            rank = "MVP++";
            rankColor = new Color(255, 170, 0); // altın sarısı
        } else if (response3.body().contains("\"newPackageRank\":\"MVP_PLUS\"")) {
            rank = "MVP+";
            rankColor = new Color(0, 200, 255); // açık mavi
        } else if (response3.body().contains("\"newPackageRank\":\"MVP\"")) {
            rank = "MVP";
            rankColor = new Color(0, 200, 255);
        } else if (response3.body().contains("\"newPackageRank\":\"VIP_PLUS\"")) {
            rank = "VIP+";
            rankColor = new Color(0, 200, 0); // yeşil
        } else if (response3.body().contains("\"newPackageRank\":\"VIP\"")) {
            rank = "VIP";
            rankColor = new Color(0, 200, 0);
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

        JLabel rankLabel = new JLabel(rank);
        rankLabel.setFont(new Font("Arial", Font.BOLD, 13));
        rankLabel.setForeground(rankColor);
        rankLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statusLabel = new JLabel();
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(8));
        panel.add(imgLabel);
        panel.add(Box.createVerticalStrut(8));
        panel.add(nameLabel);
        panel.add(Box.createVerticalStrut(3));
        panel.add(rankLabel);
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
