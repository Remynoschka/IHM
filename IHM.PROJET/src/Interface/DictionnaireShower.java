/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;
import Modele.Dictionnaire;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultListModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author Remynoschka
 */
public class DictionnaireShower extends javax.swing.JDialog {

    /**
     * Creates new form DictionnaireShower
     */
    public DictionnaireShower() {
        super(Fenetre.INSTANCE, true);
        initComponents();
        oldDico = (HashMap<String, List<String>>) Dictionnaire.INSTANCE.getDictionnaire().clone();
        String cancelName = "cancel";
        String okName = "ok";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), okName);        
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {                
                cancelButtonActionPerformed(e);
            }
        });
        actionMap.put(okName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                okButtonActionPerformed(e);
            }
        });
        
        abreviations.addListSelectionListener(new ListSelectionListener() {

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone(); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void valueChanged(ListSelectionEvent e) {
                ((DefaultListModel) significations.getModel()).removeAllElements();
                if (abreviations.getSelectedValue() != null){
                    for (String meanings : Dictionnaire.INSTANCE.getSignifications((String)abreviations.getSelectedValue()))
                        ((DefaultListModel) significations.getModel()).addElement(meanings);  
                }
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        abreviations = new javax.swing.JList();
        addAbrev = new javax.swing.JButton();
        supprAbrev = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        significations = new javax.swing.JList();
        addMeaningButton = new javax.swing.JButton();
        supprMeaning = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dictionnaire");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Abréviations"));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 250));

        abreviations.setModel(new DefaultListModel<String>());
        for (String abreviation : Dictionnaire.INSTANCE.getAbreviations())
        ((DefaultListModel) abreviations.getModel()).addElement(abreviation);
        abreviations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(abreviations);

        addAbrev.setText("Ajouter");
        addAbrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAbrevActionPerformed(evt);
            }
        });

        supprAbrev.setText("Supprimer");
        supprAbrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprAbrevActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addAbrev, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(supprAbrev, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addAbrev)
                    .addComponent(supprAbrev))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Significations"));
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 250));

        significations.setModel(new DefaultListModel<String>());
        if (abreviations.getSelectedValue()!=null){
            for (String meanings : Dictionnaire.INSTANCE.getSignifications((String)abreviations.getSelectedValue()))
            ((DefaultListModel) abreviations.getModel()).addElement(meanings);
        }
        jScrollPane1.setViewportView(significations);

        addMeaningButton.setText("Ajouter");
        addMeaningButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMeaningButtonActionPerformed(evt);
            }
        });

        supprMeaning.setText("Supprimer");
        supprMeaning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprMeaningActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addMeaningButton, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(supprMeaning, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addMeaningButton)
                    .addComponent(supprMeaning))
                .addContainerGap())
        );

        cancelButton.setText("Annuler");
        cancelButton.setAction(new AbstractAction("Annuler"){
            @Override
            public void actionPerformed(ActionEvent e) {
                DictionnaireShower.this.dispose();
            }
        });
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addAbrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAbrevActionPerformed
        TextInputShower texte = new TextInputShower();
        if (texte.getReturnStatus() == TextInputShower.RET_OK){
            ((DefaultListModel) abreviations.getModel()).addElement(texte.getTexte());
            Dictionnaire.INSTANCE.addAbreviation(texte.getTexte());
            abreviations.setSelectedIndex(abreviations.getModel().getSize()-1);
        }
    }//GEN-LAST:event_addAbrevActionPerformed

    private void supprAbrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprAbrevActionPerformed
        if (abreviations.getSelectedValue() != null){
            Dictionnaire.INSTANCE.removeAbreviation((String) abreviations.getSelectedValue());
            ((DefaultListModel) abreviations.getModel()).removeElementAt(abreviations.getSelectedIndex());        
            ((DefaultListModel) significations.getModel()).removeAllElements();
        }
    }//GEN-LAST:event_supprAbrevActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        Dictionnaire.INSTANCE.setDictionnaire(oldDico);
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // ajout des abreviations
        Enumeration abrevs = ((DefaultListModel) abreviations.getModel()).elements();
        while(abrevs.hasMoreElements()){
           Dictionnaire.INSTANCE.addAbreviation((String) abrevs.nextElement());
        }
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void supprMeaningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprMeaningActionPerformed
        if (significations.getSelectedValue() != null){
            Dictionnaire.INSTANCE.removeEntry((String) abreviations.getSelectedValue(), (String)significations.getSelectedValue());
            ((DefaultListModel) significations.getModel()).removeElementAt(significations.getSelectedIndex());        
            
        }
    }//GEN-LAST:event_supprMeaningActionPerformed

    private void addMeaningButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMeaningButtonActionPerformed
        TextInputShower texte = new TextInputShower();
        if (texte.getReturnStatus() == TextInputShower.RET_OK){
            ((DefaultListModel) significations.getModel()).addElement(texte.getTexte());
            Dictionnaire.INSTANCE.addEntry((String)abreviations.getSelectedValue(),texte.getTexte());
        }
    }//GEN-LAST:event_addMeaningButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList abreviations;
    private javax.swing.JButton addAbrev;
    private javax.swing.JButton addMeaningButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton okButton;
    private javax.swing.JList significations;
    private javax.swing.JButton supprAbrev;
    private javax.swing.JButton supprMeaning;
    // End of variables declaration//GEN-END:variables
    private HashMap<String, List<String>> oldDico; // Permet d'annuler les modifications faites
}
