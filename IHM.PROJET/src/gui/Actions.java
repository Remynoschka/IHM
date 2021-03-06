package gui;

import core.Dictionnaire;
import core.Document;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Cette classe sert a lister les actions qui seront accessibles de plusieurs
 * manieres (ex : enregistrer, mode dessin...)
 *
 * @author francoisr
 */
public class Actions {

    /**
     * Enregistre le document courant
     */
    public static final Action ENREGISTRER = new AbstractAction("Enregistrer", new ImageIcon("./data/icons/save.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    /**
     * Enregistre le document courant
     */
    public static final Action ENREGISTRER_SOUS = new AbstractAction("Enregistrer sous...", null) {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("NOTOR", "notor");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(Fenetre.INSTANCE);
            TextEditor activeTab = (TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent();
            Document currentDoc = activeTab.getDocuments();
            currentDoc.save(chooser.getSelectedFile());
            System.out.println(chooser.getSelectedFile());
        }
    };
    /**
     * Enregistre tout les documents ouverts
     */
    public static final Action SAVE_ALL = new AbstractAction("Enregistrer Tout", new ImageIcon("./data/icons/save_as.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    /**
     * Permet de dessiner sur le document
     */
    public static final Action MODE_DESSIN = new AbstractAction("Mode dessin", null) {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    /**
     * Commande Undo
     */
    public static final Action UNDO = new AbstractAction("Précédent", new ImageIcon("./data/icons/undo.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO DONATIEN THOREZ
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    /**
     * Commande Redo
     */
    public static final Action REDO = new AbstractAction("Suivant", new ImageIcon("./data/icons/redo.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO DONATIEN THOREZ
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    /**
     * Action executee quand l'utilisateur veut creer un nouveau document
     */
    public static final Action NEW_DOCUMENT = new AbstractAction("Nouveau document", new ImageIcon("./data/icons/new_file.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            new NewDocumentForm(Fenetre.INSTANCE);
        }
    };
    /**
     * Action executee quand l'utilisateur veut ouvrir un document
     */
    public static final Action OPEN_DOCUMENT = new AbstractAction("Ouvrir", new ImageIcon("./data/icons/open.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("NOTOR", "notor");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(Fenetre.INSTANCE);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                System.out.println("You chose to open this file: "
                        + chooser.getSelectedFile().getName());
                FileInputStream fin;
                ObjectInputStream ois = null;
                try {
                    fin = new FileInputStream(chooser.getSelectedFile());
                    ois = new ObjectInputStream(fin);
                    //TODO Lire le fichier NOTOR
                    Document project = (Document) ois.readObject();
                    Fenetre.INSTANCE.newDocument(project);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    if (ois != null) {
                        try {
                            ois.close();
                        } catch (IOException ex) {
                        }
                    }
                }
            }
        }
    };
    /**
     * Genere le fichier courant au format PDF
     */
    public static final Action GENERATE_PDF = new AbstractAction("Génerer PDF", null) {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    };
    /**
     * Quitte l'application proprement
     */
    public static final Action QUITTER = new AbstractAction("Quitter", null) {
        @Override
        public void actionPerformed(ActionEvent e) {
            Dictionnaire.INSTANCE.save();
            if (Fenetre.INSTANCE.hasEditedText()) {
                int option = JOptionPane.showConfirmDialog(Fenetre.INSTANCE, "Vous êtes sur le point de fermer l'application en ayant des documents qui n'ont pas été sauvegardés. "
                        + "\nVoulez vous sauvegarder avant de continuer ?", "Attention", JOptionPane.YES_NO_CANCEL_OPTION);
                switch (option) {
                    case JOptionPane.YES_OPTION:
                        //TODO sauvegarder le document
                        Fenetre.INSTANCE.dispose();
                        System.exit(0);
                        break;
                    case JOptionPane.NO_OPTION:
                        Fenetre.INSTANCE.dispose();
                        System.exit(0);
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        break;
                }
            } else {

                Fenetre.INSTANCE.dispose();
                System.exit(0);
            }
        }
    };
    public static final Action BOLD = new AbstractAction("Gras", new ImageIcon("./data/icons/bold.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    public static final Action ITALIC = new AbstractAction("Italique", new ImageIcon("./data/icons/italic.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
    /**
     * Affiche le dictionnaire
     */
    public static final Action SHOW_DICTIONNAIRE = new AbstractAction("Dictionnaire", null) {
        @Override
        public void actionPerformed(ActionEvent e) {
            new DictionnaireShower();
        }
    };
    /**
     * Passe en mode ecriture
     */
    public static final Action WRITE_MODE = new AbstractAction("Ecriture", new ImageIcon("./data/icons/texte.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.TEXT);
            }
        }
    };
    /**
     * Passe en mode dessin libre
     */
    public static final Action CRAYON_MODE = new AbstractAction("Crayon", new ImageIcon("./data/icons/crayon.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.LIBRE);
            }
        }
    };
    /**
     * Passe en mode cercle
     */
    public static final Action CERCLE_MODE = new AbstractAction("Cercle", new ImageIcon("./data/icons/rond.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.OVAL);
            }
        }
    };
    /**
     * Passe en mode disque
     */
    public static final Action DISQUE_MODE = new AbstractAction("Disque", new ImageIcon("./data/icons/rond_fill.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.SOLID_OVAL);
            }
        }
    };
    /**
     * Passe en mode carre
     */
    public static final Action CARRE_MODE = new AbstractAction("Rectangle", new ImageIcon("./data/icons/carre.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.SQUARE);
            }
        }
    };
    /**
     * Passe en mode carre plein
     */
    public static final Action CARRE_PLEIN_MODE = new AbstractAction("Rectangle plein", new ImageIcon("./data/icons/carre_fill.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.SOLID_SQUARE);
            }
        }
    };
    /**
     * Passe en mode carre arrondi
     */
    public static final Action CARRE_ARRONDI_MODE = new AbstractAction("Rectangle arrondi", new ImageIcon("./data/icons/carre_rond.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.ROUND_RECT);
            }
        }
    };
    /**
     * Passe en mode carre arrondi plein
     */
    public static final Action CARRE_ARRONDI_PLEIN_MODE = new AbstractAction("Rectangle arrondi plein", new ImageIcon("./data/icons/carre_rond_fill.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Fenetre.INSTANCE.getTabManager().getTabCount() > 0) {
                ((TextEditor) Fenetre.INSTANCE.getTabManager().getSelectedComponent()).setDrawMode(TextEditor.SOLID_ROUND_RECT);
            }
        }
    };
}
