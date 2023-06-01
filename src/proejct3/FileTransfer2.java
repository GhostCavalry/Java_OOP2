package proejct3;


import java.awt.EventQueue;
import java.io.*;
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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FileTransfer2 extends JFrame {

    private JPanel contentPane;
    private File kaynakDizin;
    private File hedefDizin;
    private JCheckBox chckbxPng;
    private JCheckBox chckbxPdf;
    private JCheckBox chckbxTxt;
    private JCheckBox chckbxSifrele;
    private JCheckBox chckbxGizliDosya;
    private JCheckBox chckbxSikistir;
    private JTextField kaynakDosyaTextField;
    private JTextField hedefDosyaTextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FileTransfer2 frame = new FileTransfer2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FileTransfer2() {
    	
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnKaynakBul = new JButton("Kaynak Bul");
        btnKaynakBul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    kaynakDizin = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Kaynak dizin seçildi: " + kaynakDizin.getAbsolutePath());
                }
            }
        });
        btnKaynakBul.setBounds(50, 40, 150, 30);
        contentPane.add(btnKaynakBul);

        JButton btnHedefBul = new JButton("Hedef Bul");
        btnHedefBul.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    hedefDizin = fileChooser.getSelectedFile();
                    JOptionPane.showMessageDialog(null, "Hedef dizin seçildi: " + hedefDizin.getAbsolutePath());
                }
            }
        });
        btnHedefBul.setBounds(250, 40, 150, 30);
        contentPane.add(btnHedefBul);
        
        kaynakDosyaTextField = new JTextField();
        kaynakDosyaTextField.setBounds(45, 80, 160, 25);
        contentPane.add(kaynakDosyaTextField);
        
        hedefDosyaTextField = new JTextField();
        hedefDosyaTextField.setBounds(245, 80, 160, 25);
        contentPane.add(hedefDosyaTextField);

        chckbxPng = new JCheckBox("PNG");
        chckbxPng.setBounds(50, 120, 70, 30);
        contentPane.add(chckbxPng);

        chckbxPdf = new JCheckBox("PDF");
        chckbxPdf.setBounds(150, 120, 70, 30);
        contentPane.add(chckbxPdf);

        chckbxTxt = new JCheckBox("TXT");
        chckbxTxt.setBounds(250, 120, 70, 30);
        contentPane.add(chckbxTxt);

        chckbxSifrele = new JCheckBox("Şifrele");
        chckbxSifrele.setBounds(50, 160, 70, 30);
        contentPane.add(chckbxSifrele);

        chckbxGizliDosya = new JCheckBox("Gizli Dosya");
        chckbxGizliDosya.setBounds(150, 160, 100, 30);
        contentPane.add(chckbxGizliDosya);

        chckbxSikistir = new JCheckBox("Sıkıştır");
        chckbxSikistir.setBounds(250, 160, 70, 30);
        contentPane.add(chckbxSikistir);

        JButton btnDosyaTasima = new JButton("Dosya Taşı");
        btnDosyaTasima.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	
            	
            	
                if (kaynakDizin == null || hedefDizin == null) {
                    JOptionPane.showMessageDialog(null, "Kaynak veya hedef dizin seçilmedi!");
                } else {
                    boolean pngSecildi = chckbxPng.isSelected();
                    boolean pdfSecildi = chckbxPdf.isSelected();
                    boolean txtSecildi = chckbxTxt.isSelected();
                    boolean sifreleSecildi = chckbxSifrele.isSelected();
                    boolean gizliDosyaSecildi = chckbxGizliDosya.isSelected();
                    boolean sikistirSecildi = chckbxSikistir.isSelected();

                    File[] dosyalar = kaynakDizin.listFiles();
                    if (dosyalar != null) {
                        for (File dosya : dosyalar) {
                            String dosyaAdi = dosya.getName();
                            if (dosyaAdi.endsWith(".png") && pngSecildi) {
                                dosyaTasi(dosya, ".png", sifreleSecildi, gizliDosyaSecildi, sikistirSecildi);
                            } else if (dosyaAdi.endsWith(".pdf") && pdfSecildi) {
                                dosyaTasi(dosya, ".pdf", sifreleSecildi, gizliDosyaSecildi, sikistirSecildi);
                            } else if (dosyaAdi.endsWith(".txt") && txtSecildi) {
                                dosyaTasi(dosya, ".txt", sifreleSecildi, gizliDosyaSecildi, sikistirSecildi);
                            }
                        }
                        JOptionPane.showMessageDialog(null, "Dosyalar taşındı!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Kaynak dizin boş!");
                    }
                }
            }
        });
        btnDosyaTasima.setBounds(170, 220, 100, 30);
        contentPane.add(btnDosyaTasima);
    }

    private void dosyaTasi(File dosya, String uzanti, boolean sifreleSecildi, boolean gizliDosyaSecildi, boolean sikistirSecildi) {
        File hedefDosya = new File(hedefDizin.getAbsolutePath() + File.separator + dosya.getName());
        if (sifreleSecildi) {
            hedefDosya = sifrele(hedefDosya);
        }
        if (gizliDosyaSecildi) {
            gizliDosyaYap(hedefDosya);
        }
        try {
            Files.copy(dosya.toPath(), hedefDosya.toPath(), StandardCopyOption.REPLACE_EXISTING);
            if (sikistirSecildi) {
                sikistir(hedefDosya);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File sifrele(File dosya) {
    	
    	
    	
    	
    	
        // Şifreleme işlemi yapılacak algoritmayı burada uygulayın
        // Sifreleme algoritmasına göre dosya şifrelenmiş olarak kaydedilmelidir
        // Şifreleme sonucunda yeni bir dosya oluşturarak geri döndürün
        return dosya;
    }

    private void gizliDosyaYap(File dosya) {
        // Dosyayı gizli dosya olarak işaretleyecek kodu buraya yazın
    	
    	
    	
    	/*String yeniDosyaAdi = "gizli.txt";
    	
    	File hedefDosya = new File(hedefDizin, );
    	File yeniDosya = new File(hedefDosya.getAbsolutePath(), yeniDosyaAdi);*/
    	
    	String kaynakDosyaDizini = kaynakDosyaTextField.getText();
        String hedefDosyaDizini = hedefDosyaTextField.getText();
    	
    	File kaynakDosya = new File(kaynakDosyaDizini);
        File hedefDosya = new File(hedefDosyaDizini);
    	
    	try {
            File gizliDosya = new File(hedefDosya.getAbsolutePath(), "gizli_dosya.txt");
            Files.copy(kaynakDosya.toPath(), gizliDosya.toPath());
            gizliDosya.isHidden();
            JOptionPane.showMessageDialog(this, "Dosya başarıyla gizlendi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Dosya gizlenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
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
}


