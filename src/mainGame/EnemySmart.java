package mainGame;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * A type of enemy in the game
 * 
 * @author Brandon Loehle 5/30/16
 *
 */

public class EnemySmart extends GameObject {

	private Handler handler;
	private GameObject player;
	private int speed;
	private Image img;

	public EnemySmart(double x, double y, int speed, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.speed = speed;
		try {
			img = ImageIO.read(new File("src/images/spaceship3.png"));
		} catch (Exception e){
			e.printStackTrace();
		}
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}

	}

	public void tick() {
		this.x += velX;
		this.y += velY;
		////////////////////////////// pythagorean theorem
		////////////////////////////// below//////////////////////////////////////////////////
		double diffX = this.x - player.getX() - 16;
		double diffY = this.y - player.getY() - 16;
		double distance = Math.sqrt(((this.x - player.getX()) * (this.x - player.getX()))
				+ ((this.y - player.getY()) * (this.y - player.getY())));
		////////////////////////////// pythagorean theorem
		////////////////////////////// above//////////////////////////////////////////////////
		velX = ((this.speed / distance) * diffX); // numerator affects speed of enemy
		velY = ((this.speed / distance) * diffY);// numerator affects speed of enemy

		// if (this.y <= 0 || this.y >= Game.HEIGHT - 40) velY *= -1;
		// if (this.x <= 0 || this.x >= Game.WIDTH - 16) velX *= -1;

		//handler.addObject(new Trail(x, y, ID.Trail, Color.green, 16, 16, 0.025, this.handler));

	}

	public void render(Graphics g) {
		Graphics2D a = (Graphics2D) g;
		a.drawImage(img, (int) x, (int) y, 150, 75, null);

	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) this.x, (int) this.y, 16, 16);
	}

}
