import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import javax.swing.*;
import java.awt.*;

/**
 * Project initiated by Sadekx, engineered and hardened by Sir_Atilotty.
 * Refactored for Cloudflare Serverless Architecture.
 */
public class HypixelProxy {

    // Your dedicated Cloudflare Worker endpoint
    private static final String PROXY_BASE_URL = "https://hypixel-status-proxy.aizen-kyoka-suigetsu-pi.workers.dev";

    public static void main(String[] args) throws Exception {
        String pname = JOptionPane.showInputDialog("Enter player name:");
        if (pname == null || pname.isEmpty()) return;

        HttpClient client = HttpClient.newHttpClient();

        // 1. Fetch Minecraft UUID from Mojang API
        String mojangUrl = "https://api.mojang.com/users/profiles/minecraft/" + pname;
        HttpRequest mojangRequest = HttpRequest.newBuilder()
                .uri(URI.create(mojangUrl))
                .build();
        HttpResponse<String> mojangResponse = client.send(mojangRequest, HttpResponse.BodyHandlers.ofString());

        if (mojangResponse.statusCode() == 404) {
            JOptionPane.showMessageDialog(null, "Player not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String body = mojangResponse.body().replace(" ", "");
        String uuid = body.split("\"id\":\"")[1].split("\"")[0];

        // 2. Fetch Player Status via Cloudflare Proxy Secure Gateway
        String statusUrl = PROXY_BASE_URL + "/status?uuid=" + uuid;
        HttpRequest statusRequest = HttpRequest.newBuilder()
                .uri(URI.create(statusUrl))
                .build();
        HttpResponse<String> statusResponse = client.send(statusRequest, HttpResponse.BodyHandlers.ofString());

        if (!statusResponse.body().contains("\"success\":true")) {
            JOptionPane.showMessageDialog(null, "API Proxy Gateway Error. Please check backend deployment.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Fetch Player Metadata (Rank) via Cloudflare Proxy Secure Gateway
        String playerUrl = PROXY_BASE_URL + "/player?uuid=" + uuid;
        HttpRequest playerRequest = HttpRequest.newBuilder()
                .uri(URI.create(playerUrl))
                .build();
        HttpResponse<String> playerResponse = client.send(playerRequest, HttpResponse.BodyHandlers.ofString());

        // 4. Evaluate Player Rank and Theme Color Mapping
        String rank = "No Rank";
        Color rankColor = Color.GRAY;

        if (playerResponse.body().contains("\"monthlyPackageRank\":\"SUPERSTAR\"")) {
            rank = "MVP++";
            rankColor = new Color(255, 170, 0); // Gold
        } else if (playerResponse.body().contains("\"newPackageRank\":\"MVP_PLUS\"")) {
            rank = "MVP+";
            rankColor = new Color(0, 200, 255); // Light Blue
        } else if (playerResponse.body().contains("\"newPackageRank\":\"MVP\"")) {
            rank = "MVP";
            rankColor = new Color(0, 200, 255);
        } else if (playerResponse.body().contains("\"newPackageRank\":\"VIP_PLUS\"")) {
            rank = "VIP+";
            rankColor = new Color(0, 200, 0); // Green
        } else if (playerResponse.body().contains("\"newPackageRank\":\"VIP\"")) {
            rank = "VIP";
            rankColor = new Color(0, 200, 0);
        }

        // 5. Download Player Avatar Render Assets
        String avatarUrl = "https://mc-heads.net/avatar/" + uuid + "/128";
        HttpRequest avatarRequest = HttpRequest.newBuilder()
                .uri(URI.create(avatarUrl))
                .header("User-Agent", "Mozilla/5.0")
                .build();
        HttpResponse<byte[]> avatarResponse = client.send(avatarRequest, HttpResponse.BodyHandlers.ofByteArray());
        ImageIcon avatarIcon = new ImageIcon(avatarResponse.body());

        // 6. Build Graphical User Interface Panel
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

        // 7. Inject Session Telemetry States dynamically into the View Matrix
        if (statusResponse.body().contains("\"online\":true")) {
            statusLabel.setText("Online");
            statusLabel.setForeground(new Color(0, 180, 0));

            if (statusResponse.body().contains("\"gameType\":")) {
                String gameType = statusResponse.body().split("\"gameType\":\"")[1].split("\"")[0];
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