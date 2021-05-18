package roboter;

import robocode.AdvancedRobot;
import robocode.Event;
import java.awt.event.KeyEvent;
import robocode.ScannedRobotEvent;

/**
 * Ein Interface welches Grundstrukturen für Roboterbefehle der RobCo Anwendung
 * vorgibt. Beispiel Nutzung in {@link Wechselrobot}
 * 
 * @author marce
 */
public interface Strategy {
	/**
	 * Hier wird der Roboter von dem die Signale kommen und der gesteuert wird, registriert.
	 * @param r
	 */
	void identify(AdvancedRobot r);

	/**
	 * Hauptmethode des Roboters
	 */
	void move();

	/**
	 * Ein generisches Event wird hier erkannt, um welches es sich genau handelt muss
	 * im weiteren Verlauf ermittelt werden.
	 * @param e
	 */
	void reactEvent(Event e);

	/**
	 * Eine Tasteneingabe wird hier erkannt, um welche es sich genau handelt muss
	 * im weiteren Verlauf ermittelt werden.
	 * @param e
	 */
	void reactKey(KeyEvent e);

	/**
	 * Ein Loslassen einer Taste wird hier erkannt, um welche es sich genau handelt muss
	 * im weiteren Verlauf ermittelt werden.
	 * @param e
	 */
	void reactKeyReleased(KeyEvent e);

	/**
	 * Der Feuer-Befehl wird hier uebergeben.
	 * @param e
	 */
	void fire(ScannedRobotEvent e);

	/**
	 * Ein Befehl zum reseten des Roboters wird hier uebergeben.
	 */
	void reset();
}
