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
    private JTextField inputKey;
    private JTextField textField3;
    private JButton saveKey;
    private JButton button3;
    private JLabel EncryptionOutput;
    private JButton devModeEnable;
    private JTextField devKey;
    private JButton openDevMenue;

    public JComboBox<String> getEncryptionSelect() {
        return encryptionSelect;
    }

    DefaultComboBoxModel<String> model =
            (DefaultComboBoxModel<String>) encryptionSelect.getModel();

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

        for (String key : DBHelper.loadKeys()) {
            encryptionSelect.addItem(key);
        }

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
                    Data.devModeEnabled = true;
                } else {
                    Util.print("failed!");
                    Util.print(devKey.getText());
                    Data.devModeEnabled = false;
                }
            }
        });
        saveKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newKey = inputKey.getText().trim();

                if (!newKey.isEmpty()) {

                    DBHelper.addKey(newKey);
                    encryptionSelect.addItem(newKey);

                    inputKey.setText("");
                }
            }
        });

        openDevMenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Data.devModeEnabled.booleanValue()) {
                    DevConsole devConsole = new DevConsole();
                }
            }
        });
    }



    public static void main(String[] args) {
        new MUI();
        DBHelper.init();
        if (DBHelper.isDatabaseEmpty()) {
            DBHelper.seedFromData();
        }
    }
}
