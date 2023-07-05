package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

public class MainForm {
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button1;

    public MainForm() {
        textField4.setVisible(false);
        button1.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(button1.getText() == "Collapse") {
                    String str1 = textField1.getText();
                    String str2 = textField2.getText();
                    String str3 = textField3.getText();
                    if(str1.length() == 0 || str2.length() == 0 || str3.length() == 0) {
                        JOptionPane.showMessageDialog(null, "Не все поля заполнены!");
                    } else {
                        textField1.setVisible(false);
                        textField2.setVisible(false);
                        textField3.setVisible(false);
                        textField4.setVisible(true);
                        textField4.setText(str1 + " " + str2 + " " + str3);
                        button1.setText("Expand");
                    }
                } else {
                    textField4.setVisible(false);
                    textField1.setVisible(true);
                    textField2.setVisible(true);
                    textField3.setVisible(true);
                    button1.setText("Collapse");
                }

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }



}
