package project;
import java.awt.*;

public class Helix extends SolidObject {

	public Vector particles[];

	public Vector directions[];

	public int colors[];

	public int ALPHA = 0xFF000000;

	public Vector temp1 = new Vector(0, 0, 0);
	public Vector temp2 = new Vector(0, 0, 0);

	static final double LENGHT = 0.1;

	static final double HEIGHT = 0.25;

	static final double WIDTH = 0.1;

	public Helix(Vector centre, int angle) {

		start = centre.myClone();
		this.centre = centre;
		angle += 360;
		angle %= 360;

		xDirection = new Vector(1, 0, 0);
		yDirection = new Vector(0, 1, 0);
		zDirection = new Vector(0, 0, 1);

		modelType = 4;
		makeBoundary(LENGHT, HEIGHT, WIDTH);

		boundary2D = new Rectangle2D(start.x - 0.01, start.z + 0.01, 0.02, 0.02);

		particles = new Vector[20];
		directions = new Vector[20];
		colors = new int[20];
		int zAxisRotation = 0;

		temp1.set(centre);
		temp2.set(zDirection);
		temp2.rotate_XZ(angle);
		temp2.scale(0.05);
		temp1.subtract(temp2);
		temp2.scale(0.1);
		for (int i = 0; i < particles.length; i++) {
			directions[i] = xDirection.myClone();
			directions[i].rotate_XY(zAxisRotation);
			directions[i].rotate_XZ(angle);
			directions[i].scale(0.02);
			particles[i] = temp1.myClone();
			particles[i].add(directions[i]);
			directions[i].scale(0.02);
			colors[i] = new Color(
					(int) (58 - 20 * GameData.sin[zAxisRotation]),
					(int) (130 - 40 * GameData.sin[zAxisRotation]),
					(int) (165 - 40 * GameData.sin[zAxisRotation])).getRGB();
			zAxisRotation += 18;
			temp1.add(temp2);

		}

		lifeSpan = 40;
	}

	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void update() {
		visible = true;

		lifeSpan--;

		if (lifeSpan == 0) {
			lifeSpan = -1;
		} else {

			ModelDrawList.register(this);

			for (int i = 0; i < 5; i++)
				boundary[i].update();

			for (int i = 0; i < particles.length; i++)
				particles[i].add(directions[i]);

			tempCentre.set(centre);
			tempCentre.y = -1;
			tempCentre.subtract(Camera.getPosition());
			tempCentre.rotate_XZ(Camera.getXZ_angle());
			tempCentre.rotate_YZ(Camera.getYZ_angle());
		}
	}

	public void draw() {
		int position = 0;
		int color = 0;
		int r = 0;
		int b = 0;
		int g = 0;
		int alpha = 0;

		double size = 1 / tempCentre.z;

		int spriteIndex = 0;
		if (size < 0.3) {
			spriteIndex = 0;
		} else if (size < 0.4 && size >= 0.3) {
			spriteIndex = 1;
		} else if (size < 0.5 && size >= 0.4) {
			spriteIndex = 2;
		} else if (size < 0.6 && size >= 0.5) {
			spriteIndex = 3;
		} else if (size < 0.7 && size >= 0.6) {
			spriteIndex = 4;
		} else if (size < 0.8 && size >= 0.7) {
			spriteIndex = 5;
		} else if (size >= 0.8) {
			spriteIndex = 6;
		} else {
			//Does nothing.
		}

		for (int i = 19; i >= 0; i--) {
			temp1.set(particles[i]);
			temp1.subtract(Camera.getPosition());
			temp1.rotate_XZ(Camera.getXZ_angle());
			temp1.rotate_YZ(Camera.getYZ_angle());
			temp1.updateLocation();

			if (temp1.screenX >= 2 && temp1.screenX < 638 && temp1.screenY >= 0
					&& temp1.screenY < 480) {
				int centre = temp1.screenX + temp1.screenY * 640;

				if (lifeSpan > 30) {
					alpha = 55;
				}
				else {
					alpha = 200;
					alpha = alpha - alpha * lifeSpan / 30 + 55;
				}

				for (int j = 0; j < GameData.size[spriteIndex].length; j++) {
					position = centre + GameData.size[spriteIndex][j];
					if (position >= 0 && position < 307200) {
						int bkgrd = Main.screen[position];

						color = colors[i];
						r = (alpha
								* (((bkgrd >> 16) & 255) - ((color >> 16) & 255)) >> 8)
								+ ((color >> 16) & 255);
						g = (alpha
								* (((bkgrd >> 8) & 255) - ((color >> 8) & 255)) >> 8)
								+ ((color >> 8) & 255);
						b = (alpha * ((bkgrd & 255) - (color & 255)) >> 8)
								+ (color & 255);

						Main.screen[position] = ALPHA | (r << 16) | (g << 8)
								| b;

					} else {
						//Does nothing.
					}
				}

			}
		}
	}
}