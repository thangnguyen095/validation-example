import oop.MTK2018.validation.Validation;
import oop.MTK2018.validation.announcer.Announcer;
import oop.MTK2018.validation.announcer.PopupAnnouncer;
import oop.MTK2018.validation.announcer.TextAnnouncer;
import oop.MTK2018.validation.constraint.Length;
import oop.MTK2018.validation.constraint.NotNull;
import oop.MTK2018.validation.constraint.Regex;
import oop.MTK2018.validation.exception.UnsupportedDataType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ValidationTest extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField textField1;
    private JTextPane textPane1;

    private Announcer announcer = new TextAnnouncer(textPane1);
    private Announcer ann2 = new PopupAnnouncer();
    private Validation validation = new Validation(announcer);

    @NotNull(msg = "Chuỗi không được phép rỗng")
    @Length(min = 3, msg = "Chiều dài tối thiểu là 3")
    @Regex(value = "\\d+", msg = "Giá trị không khớp với regex")
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
            JOptionPane.showMessageDialog(null,"Kiểm tra thành công!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        ValidationTest dialog = new ValidationTest();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
