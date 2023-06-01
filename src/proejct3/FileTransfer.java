package proejct3;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*public class FileTransfer extends JFrame {

    private JPanel contentPane;
    private File sourceDirectory;
    private File targetDirectory;
    private String fileExtension;
    private boolean encryptFile;
    private boolean makeHiddenFile;
    private boolean compressFile;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FileTransfer frame = new FileTransfer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FileTransfer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSourceDirectory = new JButton("Kaynak Dizin Seç");
        btnSourceDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    sourceDirectory = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Kaynak dizin seçildi: " + sourceDirectory.getAbsolutePath());
                }
            }
        });
        btnSourceDirectory.setBounds(50, 50, 150, 30);
        contentPane.add(btnSourceDirectory);

        JButton btnTargetDirectory = new JButton("Hedef Dizin Seç");
        btnTargetDirectory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    targetDirectory = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Hedef dizin seçildi: " + targetDirectory.getAbsolutePath());
                }
            }
        });
        btnTargetDirectory.setBounds(250, 50, 150, 30);
        contentPane.add(btnTargetDirectory);

        JCheckBox checkBoxPng = new JCheckBox("PNG");
        checkBoxPng.setBounds(50, 100, 60, 20);
        contentPane.add(checkBoxPng);

        JCheckBox checkBoxPdf = new JCheckBox("PDF");
        checkBoxPdf.setBounds(120, 100, 60, 20);
        contentPane.add(checkBoxPdf);

        JCheckBox checkBoxTxt = new JCheckBox("TXT");
        checkBoxTxt.setBounds(190, 100, 60, 20);
        contentPane.add(checkBoxTxt);

        JCheckBox checkBoxEncrypt = new JCheckBox("Şifrele");
        checkBoxEncrypt.setBounds(250, 100, 70, 20);
        contentPane.add(checkBoxEncrypt);

        JCheckBox checkBoxHidden = new JCheckBox("Gizli Dosya Yap");
        checkBoxHidden.setBounds(320, 100, 110, 20);
        contentPane.add(checkBoxHidden);

        JCheckBox checkBoxCompress = new JCheckBox("Sıkıştır");
        checkBoxCompress.setBounds(50, 130, 80, 20);
        contentPane.add(checkBoxCompress);

        JButton btnTransfer = new JButton("Dosya Taşı");
        btnTransfer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (sourceDirectory == null || targetDirectory == null) {
                    JOptionPane.showMessageDialog(null, "Kaynak veya hedef dizin seçilmedi!");
                } else {
                    if (!checkBoxPng.isSelected() && !checkBoxPdf.isSelected() && !checkBoxTxt.isSelected()) {
                        JOptionPane.showMessageDialog(null, "En az bir dosya uzantısı seçilmelidir!");
                    } else {
                        if (checkBoxPng.isSelected()) {
                            fileExtension = "png";
                        } else if (checkBoxPdf.isSelected()) {
                            fileExtension = "pdf";
                        } else if (checkBoxTxt.isSelected()) {
                            fileExtension = "txt";
                        }

                        encryptFile = checkBoxEncrypt.isSelected();
                        makeHiddenFile = checkBoxHidden.isSelected();
                        compressFile = checkBoxCompress.isSelected();

                        File[] files = sourceDirectory.listFiles();
                        if (files != null) {
                            for (File file : files) {
                                if (file.isFile() && file.getName().toLowerCase().endsWith("." + fileExtension)) {
                                    try {
                                        File targetFile = new File(targetDirectory.getAbsolutePath() + File.separator + file.getName());
                                        if (compressFile) {
                                            targetFile = new File(targetFile.getAbsolutePath() + ".zip");
                                            compressFile(file, targetFile);
                                        } else {
                                            Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                                        }

                                        if (encryptFile) {
                                            encryptFile(targetFile);
                                        }

                                        if (makeHiddenFile) {
                                            makeHiddenFile(targetFile);
                                        }

                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Dosyalar taşındı!");
                        } else {
                            JOptionPane.showMessageDialog(null, "Kaynak dizin boş!");
                        }
                    }
                }
            }
        });
        btnTransfer.setBounds(170, 180, 100, 30);
        contentPane.add(btnTransfer);
    }

    private void compressFile(File sourceFile, File targetFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(targetFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
            zos.putNextEntry(zipEntry);

            FileInputStream fis = new FileInputStream(sourceFile);
            byte[] buffer = new byte[1024];
            int bytesRead;*/
           

