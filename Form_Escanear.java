//bibliotecas para ler QR-Code
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//bibliotecas para interface gráfica
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
//bibliotecas para ler imagem da galeria
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
//para lidar com a localização
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//para datas e banco de dados
import java.sql.*;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Form_Escanear extends javax.swing.JFrame {
    private JTextArea resultArea;
    private JButton openImageButton;

    public Form_Escanear() { 
        // design para o título
     super("Leitor de QR Code");
     JLabel titulo = new JLabel("Leitor de QR Code");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
         JLabel instrucoes = new JLabel("Escolha na galeria o print que você tirou da página anterior.");
        instrucoes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        instrucoes.setAlignmentX(Component.CENTER_ALIGNMENT);
        openImageButton = new JButton("Escolher Imagem da Galeria");
        
        
        //design para o botão de escolher imagem da galeria
openImageButton = new JButton("Escolher Imagem da Galeria");
        openImageButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        openImageButton.setBackground(new Color(66, 133, 244));
        openImageButton.setForeground(Color.WHITE);
        openImageButton.setFocusPainted(false);
        openImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        resultArea = new JTextArea(4, 40);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        resultArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(200, 100));
         scrollPane.setMaximumSize(new Dimension(320, 100));
         scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(instrucoes);
        panel.add(Box.createVerticalStrut(20));
        panel.add(openImageButton);
        panel.add(Box.createVerticalStrut(20));
        panel.add(scrollPane);

        add(panel);
        
        openImageButton.addActionListener((ActionEvent e) -> {
            String location = GeoLocation.getLocation();
            if (location.contains("Erro")) {
                JOptionPane.showMessageDialog(this, "Não foi possível obter a localização.");
                return;
            }

            String qrCode = readQRCodeFromImage();
            if (qrCode != null) {
                boolean sucesso = salvarPresencaNoBanco(qrCode);
                if (sucesso) {
                resultArea.setText("Localização: " + location);
                
                
            }
                else{
                    resultArea.setText("Erro ao registrar presença.");}
                    
                    
            } else {
                resultArea.setText("Nenhum QR Code detectado.");
            }
        });

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
       
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String readQRCodeFromImage() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();

        try { //leitura de qr-code
            BufferedImage image = ImageIO.read(file);
            if (image == null) return null;

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result qrResult = new MultiFormatReader().decode(bitmap);

            String conteudo = qrResult.getText();
            
            
             int firstDash = conteudo.indexOf('-');
            if (firstDash == -1) {
                JOptionPane.showMessageDialog(this, "QR Code inválido. Formato incorreto.");
                return null;
            }
          String ra = conteudo.substring(0, firstDash);
            String dataDoQR = conteudo.substring(firstDash + 1);
           // Validação do formato data
            if (!dataDoQR.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Data do QR Code inválida.");
                return null;
            }
                String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (dataAtual.equals(dataDoQR)) {
                    
                    return ra;
                } else {
                    JOptionPane.showMessageDialog(this, "QR Code expirado ou não é do dia atual.");
                    return null;
                }
         

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    return null;
}
               
private boolean salvarPresencaNoBanco(String qrText) {
    
    //salva a presença em um banco de dados
        try {
                Class.forName("com.mysql.cj.jdbc.Driver"); 
         try  (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alunos","root","")) {
           
           
String RA = qrText.trim();
if (RA.isEmpty()) {
    JOptionPane.showMessageDialog(this, "QR Code inválido. RA vazio.");
    return false;
}
String location = GeoLocation.getLocation();
        // Verifica se o usuário existe na tabela
        String checkUserSql = "SELECT * FROM tabela_alunos WHERE RA = ?";
       try (PreparedStatement userStmt = conn.prepareStatement(checkUserSql)){
        userStmt.setString(1, RA);
        ResultSet userRs = userStmt.executeQuery();
            if (!userRs.next()) {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado na base.");
            return false;
        } }
            //verifica presença
        String checkSql = "SELECT hora_saida FROM tabela_presenca WHERE RA = ? AND data = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setString(1, RA);
        checkStmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
        ResultSet rs = checkStmt.executeQuery();
        
         if (rs.next()) {
    Timestamp horaSaida = rs.getTimestamp("hora_saida");
    if (horaSaida == null) {
        // Atualiza com a hora de saída e a localização do aluno
        String updateSql = "UPDATE tabela_presenca SET hora_saida = ?, localizacao = ? WHERE RA = ? AND data = ?";
        try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            updateStmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            updateStmt.setString(2, location);
            updateStmt.setString(3, RA);
            updateStmt.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            updateStmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Presença confirmada com sucesso!","QR-Code",3);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Entrada e saída já registradas para RA: " +RA+" !" );
        return false;
    }
} else {
    // Insere a hora de entrada do aluno na sala
    String insertSql = "INSERT INTO tabela_presenca (RA, data, hora_entrada, localizacao) VALUES (?, ?, ?,?)";
    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
        insertStmt.setString(1, RA);
        insertStmt.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
        insertStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        insertStmt.setString(4, location);
        insertStmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "Entrada registrada para RA: " + RA+" !");
    }
}
        
return true;
        
            }  } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


}

            
    public static class GeoLocation {
        public static String getLocation() {
            //registra a localização do aluno baseado em coordenadas
            try {
                URL url = new URL("http://ip-api.com/json/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONObject json = new JSONObject(response.toString());
                if (json.getString("status").equals("success")) {
                    String city = json.getString("city");
                    String country = json.getString("country");
                    double lat = json.getDouble("lat");
                    double lon = json.getDouble("lon");

                    return city + ", " + country + " (Lat: " + lat + ", Lon: " + lon + ")";
                } else {
                    return "Erro ao obter localização.";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "Erro ao obter localização.";
            }
        }
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Escanear");
        setMinimumSize(new java.awt.Dimension(500, 400));
        getContentPane().setLayout(null);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(130, 340, 209, 23);

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(100, 84, 343, 186);

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel1.setText("Escaneie o QR-Code gerado para confirmar presença");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 40, 410, 21);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Form_Escanear());
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
