/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Document;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.security.acl.Owner;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
/**
 * Formulaire permettant la creation d'un nouveau document
 * @author francoisr
 */
public class NewDocumentForm extends javax.swing.JDialog {

    /**
     * Creates new form NewDocumentForm
     */
    public NewDocumentForm(java.awt.Frame parent) {
        super(parent, true);       
        initComponents();
        String cancelName = "cancel";
        String okName = "ok";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), okName);        
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {                
                boutonAnnulerActionPerformed(e);
            }
        });
        actionMap.put(okName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                
                boutonOkActionPerformed(e);
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        author = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        title = new javax.swing.JTextField();
        boutonAnnuler = new javax.swing.JButton();
        boutonOk = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        keywords = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nouveau Document");
        setModal(true);
        setResizable(false);

        jLabel1.setText("Auteur :");

        author.setText(System.getProperty("user.name" ));

        jLabel2.setText("Titre :");

        boutonAnnuler.setText("Annuler");
        boutonAnnuler.setAction(new AbstractAction("Annuler"){
            @Override
            public void actionPerformed(ActionEvent e) {
                NewDocumentForm.this.dispose();
            }
        });
        boutonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonAnnulerActionPerformed(evt);
            }
        });

        boutonOk.setText("OK");
        boutonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boutonOkActionPerformed(evt);
            }
        });

        jLabel3.setText("Mots clés :");

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 10)); // NOI18N
        jLabel4.setText("Séparez les mots clés par des ';'");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(boutonAnnuler)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boutonOk))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(author, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                                    .addComponent(title)))
                            .addComponent(keywords)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(author, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(keywords, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boutonAnnuler)
                    .addComponent(boutonOk))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boutonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonAnnulerActionPerformed
        dispose();
    }//GEN-LAST:event_boutonAnnulerActionPerformed

    private void boutonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boutonOkActionPerformed
        if (title.getText().matches("\\S+.*") && !title.getText().equals("")){
            ((Fenetre)this.getOwner()).newDocument(new Document(author.getText(), title.getText(), keywords.getText().split(";")));
            dispose();
        } else {
            JOptionPane.showMessageDialog(Fenetre.INSTANCE, "Vous devez indiquer un titre au document.\n Celui ne doit pas commencer par un espace.", "Opération Impossible", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_boutonOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField author;
    private javax.swing.JButton boutonAnnuler;
    private javax.swing.JButton boutonOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField keywords;
    private javax.swing.JTextField title;
    // End of variables declaration//GEN-END:variables
}
