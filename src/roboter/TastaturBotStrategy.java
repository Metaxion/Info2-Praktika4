package roboter;

import java.awt.Color;
import java.awt.event.KeyEvent;

import robocode.AdvancedRobot;
import robocode.Event;
import robocode.ScannedRobotEvent;

/**
 *Ein Bot fuer das Strategy Interface der RoboCode Anwendung <br>
 * Vorgehensweise des Roboters: <br>
 * Der Bot ist steuerbar, Eingaben: - W, faehrt gerade aus - S, faehrt
 * rueckwaerts - A, dreht sich nach links - D, dreht sich nach rechts - SPACE,
 * schiesst. <br>
 * Nutzbar durch die Implementierung der Strategie {@link Strategy}.
 * 
 * @author Marcel Thomas Krups
 */
public class TastaturBotStrategy implements Strategy {

	int moveDirection;
	int turnDirection;
	AdvancedRobot bossBot;

	/**
	 * Hauptmethode (Funktionalität siehe Klassenbeschreibung)
	 */
	@Override
	public void move() {
		bossBot.setAllColors(Color.yellow);
		bossBot.setAhead(10 * moveDirection);
		bossBot.setTurnRight(45 * turnDirection);
		bossBot.execute();
	}

	/**
	 * Erwartet die Eingaben des Nutzers (siehe Klassenbeschreibung) Erkennt den
	 * Beginn des Befehls, durch das Druecken einer Taste.
	 * 
	 * @param e KeyEvent
	 */
	@Override
	public void reactKey(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_H: // h
		case KeyEvent.VK_A:
			turnDirection = -1;
			break;
		case KeyEvent.VK_J: // j
		case KeyEvent.VK_D:
			turnDirection = 1;
			break;
		case KeyEvent.VK_K: // k
		case KeyEvent.VK_W:
			moveDirection = 1;
			break;
		case KeyEvent.VK_L: // l
		case KeyEvent.VK_S:
			moveDirection = -1;
			break;
		case KeyEvent.VK_SPACE:
			bossBot.fire(1);
			break;
		}
	}

	/**
	 * Erkennt die Beendigung des Befehls, durch das Loslassen der Taste.
	 * 
	 * @param e KeyEvent
	 */
	@Override
	public void reactKeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_H:
		case KeyEvent.VK_J:
		case KeyEvent.VK_A:
		case KeyEvent.VK_D:
			turnDirection = 0;
			break;
		case KeyEvent.VK_K:
		case KeyEvent.VK_L:
		case KeyEvent.VK_W:
		case KeyEvent.VK_S:
			moveDirection = 0;
			break;
		case KeyEvent.VK_SPACE:
			bossBot.fire(1);
			break;
		}
	}

	@Override
	public void identify(AdvancedRobot r) {
		bossBot = r;
	}

	@Override
	public void reactEvent(Event e) {
	}

	@Override
	public void fire(ScannedRobotEvent e) {
	}

	@Override
	public void reset() {
		bossBot.setAllColors(Color.yellow);
		moveDirection = 0;
	}

}
