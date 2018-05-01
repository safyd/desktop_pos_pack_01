package utils;

import controlador.FXMLUsuarioControlador;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javax.imageio.ImageIO;

public class utils {

    /**
     * Este método crea mensaje para javaFX
     *
     * @param mensajeHed
     * @param mensajeDia
     * @param tipo
     */
    public static void mensaje(String mensajeHed, String mensajeDia, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setHeaderText(mensajeHed);
        alert.getDialogPane().setExpandableContent(new ScrollPane(new TextArea(mensajeDia)));
        alert.showAndWait();

    }

    /**
     * Este método convierte cualquier imagen en arreglo byte
     *
     * @param urlImagen
     * @return Solo regresa el arreglo de byte tipos JPEG, PNG,
     */
    public static byte[] convertirImagen(String urlImagen) {
        try {
            File fnew = new File(urlImagen);
            BufferedImage originalImage = ImageIO.read(fnew);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "JPEG, PNG", baos);
            return baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /**
     * Este método encripta cadenas
     *
     * @param texto
     * @return Una cadena encriptada MD5 norma iso-8859-1
     */
    public static String Encriptar(String texto) {
        try {
            MessageDigest md;
            md = MessageDigest.getInstance("MD5");
            byte[] md5hash;
            md.update(texto.getBytes("iso-8859-1"), 0, texto.length());
            md5hash = md.digest();
            return convertToHex(md5hash);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }
}
