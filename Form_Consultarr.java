//importações necessárias para data,conexão com banco e design visual
import java.time.DayOfWeek;
import java.time.DateTimeException;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class  Form_Consultarr  extends javax.swing.JFrame {
            // Conjunto para armazenar os dias do mês atual que o aluno teve presença
            private Set<Integer> diasComPresenca;
    public Form_Consultarr() {
        initComponents();
        carregarPresencasDoBanco();
        int qtdFaltas = calcularFaltas();
labelFaltas.setText("Quantidade de faltas : " + qtdFaltas);
        aplicarRenderizador();
    }
    
    private void carregarPresencasDoBanco() {
        
        diasComPresenca = new HashSet<>();

        try  (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alunos","root","")) {
            

            // tabela que registra presença baseado no horário de saída do aluno
            
            String sql = "SELECT data FROM tabela_presenca WHERE MONTH(data) = ? AND hora_saida IS NOT NULL";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, LocalDate.now().getMonthValue()); // Mês atual

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LocalDate data = rs.getDate("data").toLocalDate();
                diasComPresenca.add(data.getDayOfMonth());
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void aplicarRenderizador() {
        //aplica vermelho para dias com falta e branco para dia com presença
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
setHorizontalAlignment(CENTER);
            c.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
         if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(245, 245, 245));
                } 
                else {
                    c.setBackground(Color.WHITE);
                }
            }
         try {
             
               String texto = value.toString().trim();
               int dia = Integer.parseInt(texto);
                LocalDate hoje = LocalDate.now();
                int mes = hoje.getMonthValue();
                int ano = hoje.getYear();
             
                if (value != null) {
                if (!texto.isEmpty()) {
                
                //garante que o dia seja válido no mês
                  if (dia >= 1 && dia <= hoje.lengthOfMonth()) {
                LocalDate data = LocalDate.of(ano,mes,dia);
                DayOfWeek diaSemana = data.getDayOfWeek();
                 
                //garante a verificação somente para dias úteis
                if (diaSemana!= DayOfWeek.SATURDAY && diaSemana != DayOfWeek.SUNDAY){
                if (!diasComPresenca.contains(dia)) {
                    c.setForeground(Color.WHITE);
                    c.setBackground(new Color(255, 100, 100)); // Cor de fundo para faltas
                } else {
                    c.setForeground(Color.BLACK);
                    c.setBackground(Color.WHITE);
                } }
                   else {
                        c.setForeground(Color.BLACK);
                        c.setBackground(Color.WHITE);
                        } }
                  }}
            
                  } catch (NumberFormatException | DateTimeException e) {
    // Valor não é um número válido de dia ou data inválida
    c.setForeground(Color.BLACK);
    c.setBackground(Color.WHITE);
} 
            return c;
        }
    
             };


// Aplica o renderizador a TODAS as colunas
 
for (int i = 0; i < jTable1.getColumnCount(); i++) {
    jTable1.getColumnModel().getColumn(i).setCellRenderer(renderer);
}
    //ajuste visuais na tabela
    jTable1.setRowHeight(30); 
    jTable1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14)); // Fonte das células
    jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); // Fonte do cabeçalho
    jTable1.getTableHeader().setBackground(new Color(220, 220, 220));
    jTable1.getTableHeader().setForeground(Color.BLACK);
    }
        
    private int calcularFaltas() {
    int faltas = 0;
    LocalDate hoje = LocalDate.now();
    int mes = hoje.getMonthValue();
    int ano = hoje.getYear();
    //quantos dias tem o mês
    int diasNoMes = hoje.lengthOfMonth();
    for (int dia = 1; dia <= diasNoMes; dia++) {
        LocalDate data = LocalDate.of(ano, mes, dia);
        
        // Verifica se é dia útil (segunda a sexta)
        if (data.getDayOfWeek().getValue() <= 5) {
            if (!diasComPresenca.contains(dia)) {
                faltas++;
            }
        }
    }

    return faltas;
    
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelFaltas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Presença");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Controle de presença");

        jTable1.setForeground(new java.awt.Color(0, 153, 153));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                { new Integer(1),  new Integer(2),  new Integer(3),  new Integer(4),  new Integer(5),  new Integer(6),  new Integer(7)},
                { new Integer(8),  new Integer(9),  new Integer(10),  new Integer(11),  new Integer(12),  new Integer(13),  new Integer(14)},
                { new Integer(15),  new Integer(16),  new Integer(17),  new Integer(18),  new Integer(19),  new Integer(20),  new Integer(21)},
                { new Integer(22),  new Integer(23),  new Integer(24),  new Integer(25),  new Integer(26),  new Integer(27),  new Integer(28)},
                { new Integer(29),  new Integer(30), null, null, null, null, null}
            },
            new String [] {
                "Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setToolTipText("1");
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setName(""); // NOI18N
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Junho");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Segunda - Programação de soluções computacionais");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Terça - Programação de soluções computacionais ");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("Quarta - Vida & Carreira");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText("Quinta - Modelagem de software");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setText("Sexta - Modelagem de software");

        labelFaltas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelFaltas.setForeground(new java.awt.Color(255, 0, 0));
        labelFaltas.setText("Quantidade de faltas : 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(480, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(551, 551, 551))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelFaltas)
                        .addGap(542, 542, 542))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(350, 350, 350))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(603, 603, 603))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(labelFaltas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(440, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelFaltas;
    // End of variables declaration//GEN-END:variables
}
