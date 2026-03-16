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
    private JButton saveButton;
    private JLabel EncryptionOutput;
    private JButton devModeEnable;
    private JTextField devKey;
    private JButton openDevMenu;
    private JTextArea welcomeMSG;//TODO add color shift as well as dynamic text editing and fix background


    public JComboBox<String> EncryptionSelect() {
        return encryptionSelect;
    }

    public void reloadKeys() {

        DefaultComboBoxModel<String> model =
                new DefaultComboBoxModel<>();

        for (String key : DBHelperKeys.loadKeys()) {
            model.addElement(key);
        }

        encryptionSelect.setModel(model);
    }

    public void initSequence(MUI mui) {
        JOptionPane.showMessageDialog(mui, "Initiating Sequences");
        DBHelperKeys.clearKeys();
        encryptionSelect.removeAllItems();
        DBHelperKeys.seedFromData();
        reloadKeys();
        Util.print("Initiating Sequences");
    }

    public MUI() {
        setContentPane(MainPanle );
        setTitle("Encryption Software");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        setLocationRelativeTo(null);
        setVisible(true);
        try {
            setIconImage(ImageIO.read(new File("src/images/icon.jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        initSequence(this);

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
                    DBHelperLTDS.setVar("DevMode","true");
                } else {
                    Util.print("failed!");
                    Util.print(devKey.getText());
                    DBHelperLTDS.setVar("DevMode","false");
                }
            }
        });
        saveKey.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newKey = inputKey.getText().trim();

                if (!newKey.isEmpty()) {

                    DBHelperKeys.addKey(newKey);
                    encryptionSelect.addItem(newKey);

                    inputKey.setText("");
                }
            }
        });

        openDevMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Boolean.parseBoolean(DBHelperLTDS.getVar("DevMode"))) {
                        new DevConsole(MUI.this);

                } else {
                    JOptionPane.showMessageDialog(MUI.this,"Not Authorized");
                }
            }
        });

        final int[] current = {0};

        encryptionSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current[0]++;
                Object item = encryptionSelect.getSelectedItem();
                if (item != null) {
                    Util.print("Selected ComboBox Key:" + encryptionSelect.getSelectedItem().toString());
                    Util.print("Number of Times Selected:");
                    Util.printInt(current[0]);
                    Data.currentKey = encryptionSelect.getSelectedItem().toString();
                    Util.print("Selected Data Key: " + Data.currentKey);
                }
            }
        });
    }




    public static void main(String[] args) {
        new MUI();
        DBHelperKeys.init();
        if (DBHelperKeys.isDatabaseEmpty()) {
            DBHelperKeys.seedFromData();
        }


    }
}
