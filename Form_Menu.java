//bibliotecas para gerar QR-Code
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
// visual
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
// define o dia da semana
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Form_Menu extends javax.swing.JFrame {
     public Form_Menu() {
        initComponents();
        jButton1.addActionListener(e -> gerarQRCode());
        this.setLocationRelativeTo(null);
        preencherInfoAula();
    }
     


//cada dia é relacionado a um professor através do switch
private void preencherInfoAula() {
    DayOfWeek dia = LocalDate.now().getDayOfWeek();

    switch (dia) {
        case MONDAY:
            jLabel3.setText("AULA: Programação de Soluções Computacionais");
            jLabel5.setText("PROFESSOR: Fernando Mori");
            jLabel6.setText("SALA: 402");
            break;
        case TUESDAY:
            jLabel3.setText("AULA: Programação de Soluções Computacionais");
            jLabel5.setText("PROFESSOR: Edgard Luiz");
            jLabel6.setText("SALA: 103");
            break;
        case WEDNESDAY:
            jLabel3.setText("AULA: Vida & Carreira");
            jLabel5.setText("PROFESSORA: Pamela Stupp");
            jLabel6.setText("SALA: 107");
            break;
        case THURSDAY:
            jLabel3.setText("AULA: Modelagem de Software");
            jLabel5.setText("PROFESSOR: Ricardo Garrido ");
            jLabel6.setText("SALA: 102");
            break;
        case FRIDAY:
            jLabel3.setText("AULA: Modelagem de Software");
            jLabel5.setText("PROFESSORA: Isabela Vasconcelos");
            jLabel6.setText("SALA: 105");
            break;
        default:
            jLabel3.setText("AULA: Nenhuma aula hoje");
            jLabel5.setText("PROFESSOR: -");
            jLabel6.setText("SALA: -");
    }
}



    
    private void gerarQRCode() {
        
        if (sessao.RA == null || sessao.RA.isEmpty()) {
        JOptionPane.showMessageDialog(this, "RA não está definido. Faça login.");
        return;
    }

      // Obtem data atual no formato yyyy-MM-dd
    LocalDate dataAtual = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    String dataFormatada = dataAtual.format(formatter);

    // Gera o conteúdo do QR Code com RA + Data
    String nomeUsuario = sessao.RA;
    String conteudoQRCode = nomeUsuario + "-" + dataFormatada;  
        
        

//gerar qr-Code
jButton1.setText("Gerar QR Code");
jLabel7.setText("QR Code será exibido aqui");
//define as dimensões do qr-code
    int largura = 300;
    int altura = 300;
    int alturaExtra = 50;
    
    QRCodeWriter writer = new QRCodeWriter();
    //converte para o formato QR-Code
    try { 
        BitMatrix matrix = writer.encode( conteudoQRCode, BarcodeFormat.QR_CODE, largura, altura);
        BufferedImage imagem = MatrixToImageWriter.toBufferedImage(matrix);
        BufferedImage finalImage = new BufferedImage(largura, altura + alturaExtra, BufferedImage.TYPE_INT_ARGB);
        
        //define a cor do fundo como branca e preenche toda imagem
           Graphics2D g = finalImage.createGraphics();
            g.setColor(Color.WHITE);
             g.fillRect(0, 0, largura, altura + alturaExtra);
             g.drawImage(imagem, 0, 0, null);
             //configura a cor e a fonte do RA que será mostrado abaixo do QR-Code
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.BLACK);
            String texto = nomeUsuario + " - " + dataFormatada;
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(nomeUsuario);
            int x = (largura - textWidth) / 2;
            int y = altura + ((alturaExtra + fm.getAscent()) / 2) - 5;
            g.drawString(texto, x, y);
            g.dispose();
            
            
        jLabel7.setIcon(new ImageIcon(finalImage)); // Aqui você exibe o QR Code no jLabel7
        jLabel7.setText("");   // Remove texto padrão, se tiver
    } catch (WriterException e) {
        JOptionPane.showMessageDialog(this, "Erro ao gerar QR Code: " + e.getMessage());
    }
}
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Menu");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel2.setText("Gerando QR-Code para validação da presença");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        jLabel3.setText("Aula: Modelagem de software");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 460, -1, -1));

        jLabel5.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        jLabel5.setText("Professor: Ricardo Garrido");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 490, 230, -1));

        jLabel6.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        jLabel6.setText("Sala: 102");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 520, 190, -1));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Gerar");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 400, -1, -1));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 360, 320));

        jTextField1.setText("qr-code");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 260, 50, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("ATENÇÃO! Após gerar o QR-Code, tire um print da tela e vá para a página Escanear.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 430, -1, -1));

        jMenu2.setText("Consultar Faltas");

        jMenuItem1.setText("Conferir Faltas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Escanear");

        jMenuItem4.setText("Escanear QR-Code");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Sair");

        jMenuItem2.setText("Sair");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem2);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      new Form_Consultarr().setVisible(true);
 
 
    }//GEN-LAST:event_jMenuItem1ActionPerformed
private void formWindowOpened(java.awt.event.WindowEvent evt){
    setExtendedState(Form_Menu.MAXIMIZED_BOTH);
}
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
         new Form_Escanear().setVisible(true);
         setExtendedState(Form_Escanear.MAXIMIZED_BOTH);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    
    
    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(() -> new Form_Menu().setVisible(true));
    
}

    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
