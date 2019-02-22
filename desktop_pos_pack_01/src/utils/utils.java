package utils;

import controlador.FXMLArticuloControlador;
import controlador.FXMLUsuarioControlador;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

public class utils {

    /**
     * Localización de archivo para configuración
     */
    protected static final String ARCHIVO_CONFIGURACION = "propiedades.sistema.properties";

    /**
     * Este método cuestiona la existencía del archivo configuración
     *
     * @return regresa false si no existe de lo contrario un true
     */
    public static Boolean existePropiedad() {
        File f = new File(utils.ARCHIVO_CONFIGURACION);
        return f.exists();
    }

    /**
     * Este método obtiene las propiedades del archivo
     *
     * @param llave
     * @return regresa el dato obtenido por medio de la llave
     */
    public static String obtenerPropiedad(String llave) {
        InputStream input = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File(utils.ARCHIVO_CONFIGURACION)));
            if (prop.containsKey(llave)) {
                return prop.getProperty(llave);
            } else {
                return "";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }

    /**
     * Este método elimina las propiedades del archivo
     *
     * @param llave
     */
    public static void eliminarPropiedad(String llave) {
        InputStream input = null;
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(new File(utils.ARCHIVO_CONFIGURACION)));
            if (prop.containsKey(llave)) {
                prop.remove(llave);
            } else {

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Este método crea el archivo de propiedad
     *
     * @return verdadero en caso de ser creado en caso contrario falso
     */
    public static Boolean crearArchivo() {
        try {
            File f = new File(utils.ARCHIVO_CONFIGURACION);
            if (!f.exists()) {
                f.createNewFile();
                return true;
            } else {
                return true;
            }
        } catch (IOException ex) {
            Logger.getLogger(utils.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Este método agregar propiedades al archivo
     *
     * @param llave
     * @param valor
     */
    public static void agregarPropiedad(String llave, String valor) {
        Properties prop = new Properties();
        OutputStream output = null;
        InputStream input = null;
        try {
            File f = new File(utils.ARCHIVO_CONFIGURACION);
            if (!f.exists()) {
                f.createNewFile();
            }
            input = new FileInputStream(f);
            prop.load(input);
            prop.setProperty(llave, valor);
            output = new FileOutputStream(f);
            prop.store(output, null);
            output.flush();
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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
            File archivo = new File(urlImagen);
            BufferedImage originalImage = ImageIO.read(archivo);
            ByteArrayOutputStream arrayByte = new ByteArrayOutputStream();
            ImageIO.write(originalImage, "JPEG, PNG", arrayByte);
            return arrayByte.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(FXMLUsuarioControlador.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static javafx.scene.image.Image obtenerImagen(byte[] arrayByte) {
        return new javafx.scene.image.Image(new ByteArrayInputStream(arrayByte));

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

    protected static String convertToHex(byte[] data) {
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

    /**
     * Este método limita la cantidad de caracteres ingresados en el TextField
     *
     * @param textField objeto TextField
     * @param tamañoMaximo tamaño máximo de caracteres a ingresar
     */
    public void agregarLimiteCaracteres(TextField textField, int tamañoMaximo) {
        textField.textProperty().addListener((ObservableValue<? extends String> ov, String oldValue, String newValue) -> {
            if (textField.getText().length() > tamañoMaximo) {
                textField.setText(textField.getText().substring(0, tamañoMaximo));
            }
        });
    }

    /**
     * Este método limitá el uso de caracteres no números ingresados
     *
     * @param textField objeto TextField
     */
    public static void validarNumeros(TextField textField) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    /**
     * Este método limitá el uso de caracteres no números con decimal ingresados
     *
     * @param textField objeto TextField
     */
    public static void validarNumerosDecimal(TextField textField) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(oldValue);
            }
        });
    }

    /**
     * Este método limitá el uso de caracteres no números con decimal ingresados
     *
     * @return Regresa estructura fecha a tipo y fecha
     */
    public static Timestamp obtenerFechaTimeStamp() {
        Date date = new Date();
        java.sql.Timestamp timeStamDate = new Timestamp(date.getTime());
        return timeStamDate;
    }

    /**
     * Este método muestra la imagen en su diferente formato (JPG y PNG)
     * agregando la dirección de la misma.
     *
     * @param textField
     * @param imageView
     */
    public static void obtenerImagenConDireccion(TextField textField, ImageView imageView) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            textField.setText(file.toString());
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(FXMLArticuloControlador.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * Este método obtiene la dirección de la imagen en los diferentes formatos
     * (JPG y PNG).
     *
     * @return Regresa la dirección de la imagen seleccionada
     */
    public static String urlImagen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        return file.toString();
    }

    /**
     * Este método obtiene la imagen estandar desiganda por el sistema
     *
     * @param imageView
     */
    public static void obtenerImagenEstandar(ImageView imageView) {
        File file = new File("/imagen/imagen.jpg");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }

    /**
     * Este método crea un predictor de texto con la entidad, el campo  
    *
     * @param entidad
     * @param txtField
     */
  

    /**
     * Este método valida modulo por licencia
     *
     * @return Valida la estructura de credencíal y licencia existente
     */
    public static Boolean obtenerAcceso() {
        return true;

    }

    public static ArrayList<Object> accesoPorModulo() {

        return new ArrayList<>();
    }

    public static Integer esSeleccionado(CheckBox checkBox) {
        if (checkBox.isSelected()) {
            return 1;
        } else {
            return 0;
        }
    }

}
