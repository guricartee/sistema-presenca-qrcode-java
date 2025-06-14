
import static javax.swing.JOptionPane.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.*;


public class Login extends javax.swing.JFrame {
public Login() {
        initComponents();
        setVisible(true);
    }

 

    @SuppressWarnings("checked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(500, 400));
        getContentPane().setLayout(null);

        jLabel1.setBackground(new java.awt.Color(204, 255, 204));
        jLabel1.setFont(new java.awt.Font("Engravers MT", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 51));
        jLabel1.setText("Usuário");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 180, 90, 15);

        jLabel2.setFont(new java.awt.Font("Engravers MT", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 51));
        jLabel2.setText("Senha");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 230, 90, 18);

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2);
        jTextField2.setBounds(210, 180, 90, 20);

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setText("Acessar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(370, 310, 100, 25);

        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(210, 230, 90, 20);

        jLabel3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jLabel3.setText("Sistema de presença - Login");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(140, 20, 240, 21);

        jLabel5.setIcon(new javax.swing.ImageIcon("C:\\Users\\DELL\\OneDrive\\Área de Trabalho\\ulife.png")); // NOI18N
        jLabel5.setMaximumSize(new java.awt.Dimension(500, 400));
        jLabel5.setMinimumSize(new java.awt.Dimension(300, 200));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 500, 420);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                        
     try{
         //acesso ao  banco de dados
 Class.forName("com.mysql.cj.jdbc.Driver"); 
 Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alunos","root","");
 
String nomedigitado = jTextField2.getText();
String senhadigitada = new String(jPasswordField1.getPassword());
//vê se tem o registro no banco de dados
String sql = "SELECT * FROM tabela_alunos WHERE BINARY UserID = ? AND BINARY RA = ?";
PreparedStatement stmt = con.prepareStatement(sql);
stmt.setString(1, nomedigitado);
 stmt.setString(2, senhadigitada);

    ResultSet RS = stmt.executeQuery();

    
    if (nomedigitado.isEmpty() || senhadigitada.isEmpty()) {
    showMessageDialog(this, "Preencha usuário e senha.");
    return;
}
    if (RS.next()) {
            // Login bem-sucedido
            jTextField2.setText("");
            jPasswordField1.setText("");
            jTextField2.requestFocus();
            //salva o RA em uma classe chamada sessão
             sessao.RA = RS.getString("RA");
            new Form_Menu().setVisible(true);
            dispose(); // Fecha a janela de login
        } else {
            // Usuário ou senha inválidos
            showMessageDialog(this, "Usuário ou senha inválidos");
            jTextField2.requestFocus();
        }
    
        RS.close();
        stmt.close();
        con.close();

    } catch (ClassNotFoundException e) {
        showMessageDialog(this, "Driver JDBC não encontrado: " + e.getMessage());
    } catch (SQLException e) {
        showMessageDialog(this, "Erro de SQL: " + e.getMessage());
    }
     
           

                       
                                        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        
      
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed
       
    }//GEN-LAST:event_jPasswordField1ActionPerformed

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
