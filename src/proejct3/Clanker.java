package proejct3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Clanker extends JFrame implements ActionListener {
    private JTextField kaynakDosyaTextField;
    private JTextField hedefDosyaTextField;
    private JButton kaynakDosyaSecButton;
    private JButton hedefDosyaSecButton;
    private JButton transferButton;
    private JCheckBox gizleCheckBox;
    private JCheckBox sikistirCheckBox;
    private JCheckBox sifreleCheckBox;

    public Clanker() {
        setTitle("Dosya Transfer Uygulaması");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel kaynakDosyaLabel = new JLabel("Kaynak Dosya:");
        kaynakDosyaLabel.setBounds(20, 20, 100, 25);
        panel.add(kaynakDosyaLabel);

        kaynakDosyaTextField = new JTextField();
        kaynakDosyaTextField.setBounds(120, 20, 200, 25);
        panel.add(kaynakDosyaTextField);

        kaynakDosyaSecButton = new JButton("Seç");
        kaynakDosyaSecButton.setBounds(330, 20, 50, 25);
        kaynakDosyaSecButton.addActionListener(this);
        panel.add(kaynakDosyaSecButton);

        JLabel hedefDosyaLabel = new JLabel("Hedef Dosya:");
        hedefDosyaLabel.setBounds(20, 60, 100, 25);
        panel.add(hedefDosyaLabel);

        hedefDosyaTextField = new JTextField();
        hedefDosyaTextField.setBounds(120, 60, 200, 25);
        panel.add(hedefDosyaTextField);

        hedefDosyaSecButton = new JButton("Seç");
        hedefDosyaSecButton.setBounds(330, 60, 50, 25);
        hedefDosyaSecButton.addActionListener(this);
        panel.add(hedefDosyaSecButton);

        gizleCheckBox = new JCheckBox("Dosyaları Gizle");
        gizleCheckBox.setBounds(20, 100, 150, 25);
        panel.add(gizleCheckBox);
        
        sikistirCheckBox = new JCheckBox("Dosyaları Sıkıştır");
        sikistirCheckBox.setBounds(170, 100, 150, 25);
        panel.add(sikistirCheckBox);
        
        sifreleCheckBox = new JCheckBox("Dosyaları Şifrele");
        sifreleCheckBox.setBounds(340, 100, 150, 25);
        panel.add(sifreleCheckBox);
        
        transferButton = new JButton("Transfer Et");
        transferButton.setBounds(20, 140, 100, 25);
        transferButton.addActionListener(this);
        panel.add(transferButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == kaynakDosyaSecButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDir = fileChooser.getSelectedFile();
                kaynakDosyaTextField.setText(selectedDir.getAbsolutePath());
            }
        } else if (e.getSource() == hedefDosyaSecButton) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDir = fileChooser.getSelectedFile();
                hedefDosyaTextField.setText(selectedDir.getAbsolutePath());
            }
        } else if (e.getSource() == transferButton) {
            String kaynakDosyaDizini = kaynakDosyaTextField.getText();
            String hedefDosyaDizini = hedefDosyaTextField.getText();

            if (kaynakDosyaDizini.isEmpty() || hedefDosyaDizini.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Lütfen kaynak ve hedef dosya dizinlerini seçin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            File kaynakDosya = new File(kaynakDosyaDizini);
            File hedefDosya = new File(hedefDosyaDizini);

            if (!kaynakDosya.exists() || !hedefDosya.exists()) {
                JOptionPane.showMessageDialog(this, "Seçilen kaynak veya hedef dosya dizini mevcut değil.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (gizleCheckBox.isSelected()) {
                try {
                    File gizliDosya = new File(hedefDosya.getAbsolutePath(), "gizli_dosya.txt");
                    Files.copy(kaynakDosya.toPath(), gizliDosya.toPath());
                    gizliDosya.isHidden();
                    JOptionPane.showMessageDialog(this, "Dosya başarıyla gizlendi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Dosya gizlenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                try {
                    File hedefDosyaKlasor = new File(hedefDosya.getAbsolutePath());
                    File[] dosyalar = kaynakDosya.listFiles();
                    for (File dosya : dosyalar) {
                        if (dosya.isFile()) {
                            File hedefDosya1 = new File(hedefDosyaKlasor, dosya.getName());
                            Files.copy(dosya.toPath(), hedefDosya1.toPath());
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Dosyalar başarıyla transfer edildi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Dosyalar transfer edilirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
            

        }
    }
    
    private void sikistir(File dosya) {
        String hedefDosyaAdi = dosya.getAbsolutePath();
        String sikistirilmisDosyaAdi = hedefDosyaAdi + ".zip";
        try (FileOutputStream fos = new FileOutputStream(sikistirilmisDosyaAdi);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(hedefDosyaAdi)) {

            zos.putNextEntry(new ZipEntry(dosya.getName()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            zos.flush();
            zos.finish();

            dosya.delete(); // Orjinal dosyayı silmek isterseniz bu satırı kullanabilirsiniz

            JOptionPane.showMessageDialog(null, "Dosya sıkıştırıldı: " + sikistirilmisDosyaAdi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Clanker frame = new Clanker();
        frame.setVisible(true);
    }
}

