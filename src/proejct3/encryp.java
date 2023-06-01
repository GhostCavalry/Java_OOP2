package proejct3;

public class encryp {
    public static void main(String[] args) {
        String originalMessage = "Merhaba Dünya!";
        int key = 3;
        
        System.out.println("Orijinal Mesaj: " + originalMessage);
        
        String encryptedMessage = encrypt(originalMessage, key);
        System.out.println("Şifrelenmiş Mesaj: " + encryptedMessage);
        
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Şifresi Çözülmüş Mesaj: " + decryptedMessage);
    }
    
    public static String encrypt(String message, int key) {
        StringBuilder encrypted = new StringBuilder();
        
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            
            if (Character.isLetter(c)) {
                char encryptedChar = (char) (c + key);
                
                if ((Character.isLowerCase(c) && encryptedChar > 'z') || (Character.isUpperCase(c) && encryptedChar > 'Z')) {
                    encryptedChar = (char) (c - (26 - key));
                }
                
                encrypted.append(encryptedChar);
            } else {
                encrypted.append(c);
            }
        }
        
        return encrypted.toString();
    }
    
    public static String decrypt(String message, int key) {
        StringBuilder decrypted = new StringBuilder();
        
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            
            if (Character.isLetter(c)) {
                char decryptedChar = (char) (c - key);
                
                if ((Character.isLowerCase(c) && decryptedChar < 'a') || (Character.isUpperCase(c) && decryptedChar < 'A')) {
                    decryptedChar = (char) (c + (26 - key));
                }
                
                decrypted.append(decryptedChar);
            } else {
                decrypted.append(c);
            }
        }
        
        return decrypted.toString();
    }
}

