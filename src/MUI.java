import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MUI extends JFrame {

    private JPanel MainPanle;
    private JTabbedPane tabbedPane1;
    private JComboBox encryptionSelect;
    private JTextField Input;
    private JButton encryptButton;
    private JTextField textField2;
    private JTextField textField3;
    private JButton addButton;
    private JButton button3;
    private JLabel EncryptionOutput;
    private JButton devModeEnable;
    private JTextField devKey;

    public MUI() {
        setContentPane(MainPanle );
        setTitle("Encryption Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            setIconImage(ImageIO.read(new File("src/images/icon.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        encryptionSelect.addItem(Data.sq1);
        encryptionSelect.addItem(Data.sq2);
        encryptionSelect.addItem(Data.sq3);
        encryptionSelect.addItem(Data.sq4);


        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncryptionOutput.setText(Input.getText());
            }
        });
        devModeEnable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(devKey.getText().equals(Data.hashToCheck)){
                    Util.print("Dev Mode Active!");
                } else {
                    Util.print("failed!");
                    Util.print(devKey.getText());
                }
            }
        });
    }

    public static void main(String[] args) {
        new MUI();
    }
}
