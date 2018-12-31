import oop.MTK2018.validation.Validation;
import oop.MTK2018.validation.announcer.Announcer;
import oop.MTK2018.validation.announcer.TextAnnouncer;
import oop.MTK2018.validation.constraint.NotNull;
import oop.MTK2018.validation.exception.UnsupportedDataType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValidationTest extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textField1;
    private JLabel label;

    private Announcer announcer = new TextAnnouncer(label);
    private Validation validation = new Validation(announcer);
    @NotNull
    String test;

    public ValidationTest() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (UnsupportedDataType unsupportedDataType) {
                    unsupportedDataType.printStackTrace();
                }
            }
        });
    }

    private void onOK() throws UnsupportedDataType {
        // add your code here
//        dispose();
        test = textField1.getText();
        boolean valid = validation.validate(this);
        if(valid){
            JOptionPane.showMessageDialog(null,"Validation success!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ValidationTest dialog = new ValidationTest();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
