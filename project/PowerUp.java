package project;
public class PowerUp extends SolidObject {

	public int type;

	public Polygon3D polygons[];

	public int theta;

	public Polygon3D shadow;

	public Vector displacement;

	public PowerUp(double x, double y, double z, int type) {
		this.type = type;

		start = new Vector(x, y, z);

		xDirection = new Vector(0.65, 0, 0);
		yDirection = new Vector(0, 0.65, 0);
		zDirection = new Vector(0, 0, 0.65);

		modelType = 5;
		makeBoundary(0.01, 0.025, 0.01);

		boundary2D = new Rectangle2D(x - 0.01, z + 0.01, 0.02, 0.02);

		findCenter();

		lifeSpan = 1;

		makeBody();

		displacement = new Vector(0, 0, 0);
	}

	public void makeBody() {
		polygons = new Polygon3D[10];
		Vector v[];

		int textureIndexA = 0, textureIndexB = 0;
		if (type == 1) {
			textureIndexA = 33;
			textureIndexB = 34;
		}

		if (type == 2) {
			textureIndexA = 31;
			textureIndexB = 32;
		}

		if (type == 3) {
			textureIndexA = 38;
			textureIndexB = 39;
		}

		if (type == 4) {
			textureIndexA = 48;
			textureIndexB = 49;
		}

		v = new Vector[] { put(-0.07, 0, 0.05), put(0.07, 0, 0.05),
				put(0.07, 0, -0.05), put(-0.07, 0, -0.05) };
		polygons[0] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexB], 1, 1, 6);

		v = new Vector[] { put(-0.04, 0, 0.04), put(0.04, 0, 0.04),
				put(0.04, 0, -0.04), put(-0.04, 0, -0.04) };
		polygons[1] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexA], 1, 1, 6);

		v = new Vector[] { put(-0.07, 0, -0.05), put(0.07, 0, -0.05),
				put(0.07, -0.01, -0.06), put(-0.07, -0.01, -0.06) };
		polygons[2] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexB], 1, 1, 6);

		v = new Vector[] { put(-0.07, -0.01, 0.06), put(0.07, -0.01, 0.06),
				put(0.07, 0, 0.05), put(-0.07, 0, 0.05) };
		polygons[3] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexB], 1, 1, 6);

		v = new Vector[] { put(-0.07, -0.01, -0.06), put(0.07, -0.01, -0.06),
				put(0.07, -0.1, -0.06), put(-0.07, -0.1, -0.06) };
		polygons[4] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexB], 1, 1, 6);

		v = new Vector[] { put(-0.07, -0.1, 0.06), put(0.07, -0.1, 0.06),
				put(0.07, -0.01, 0.06), put(-0.07, -0.01, 0.06) };
		polygons[5] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexB], 1, 1, 6);

		v = new Vector[] { put(-0.07, 0, 0.05), put(-0.07, 0, -0.05),
				put(-0.07, -0.01, -0.06), put(-0.07, -0.1, -0.06),
				put(-0.07, -0.11, -0.05), put(-0.07, -0.11, 0.05),
				put(-0.07, -0.1, 0.06), put(-0.07, -0.01, 0.06) };
		polygons[6] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexB], 1, 1, 6);

		v = new Vector[] { put(0.07, -0.01, 0.06), put(0.07, -0.1, 0.06),
				put(0.07, -0.11, 0.05), put(0.07, -0.11, -0.05),
				put(0.07, -0.1, -0.06), put(0.07, -0.01, -0.06),
				put(0.07, 0, -0.05), put(0.07, 0, 0.05) };
		polygons[7] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexB], 1, 1, 6);

		v = new Vector[] { put(-0.07, -0.01, 0.04), put(-0.07, -0.01, -0.04),
				put(-0.07, -0.09, -0.04), put(-0.07, -0.09, 0.04) };
		polygons[8] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexA], 1, 1, 6);

		v = new Vector[] { put(0.07, -0.09, 0.04), put(0.07, -0.09, -0.04),
				put(0.07, -0.01, -0.04), put(0.07, -0.01, 0.04) };
		polygons[9] = new Polygon3D(v, v[0], v[1], v[3],
				Main.textures[textureIndexA], 1, 1, 6);

		double temp = start.y;
		start.y = -1;

		zDirection.scale(0.8);
		xDirection.scale(0.9);
		xDirection.rotate_XZ(90);
		zDirection.rotate_XZ(90);
		start.add(-0.05, 0, -0.05);
		v = new Vector[] { put(-0.17, 0, 0.17), put(0.17, 0, 0.17),
				put(0.17, 0, -0.17), put(-0.17, 0, -0.17) };
		shadow = new Polygon3D(v, v[0], v[1], v[3], Main.textures[14], 1, 1, 2);
		start.y = temp;
		start.add(0.05, 0, 0.05);
	}

	public void update() {
		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.getPosition());
		tempCentre.rotate_XZ(Camera.getXZ_angle());
		tempCentre.rotate_YZ(Camera.getYZ_angle());
		tempCentre.updateLocation();

		if (tempCentre.z < 0.5 || tempCentre.screenY < -30
				|| tempCentre.screenX < -400 || tempCentre.screenX > 800) {
			visible = false;
		} else {
			visible = true;

			ModelDrawList.register(this);

			theta += 9;
			theta = theta % 360;
			double height = 0.006 * GameData.sin[theta];

			for (int i = 0; i < polygons.length; i++) {
				polygons[i].origin.subtract(start);
				polygons[i].origin.rotate_XZ(3);
				polygons[i].origin.add(start);
				polygons[i].origin.y += height;

				polygons[i].rightEnd.subtract(start);
				polygons[i].rightEnd.rotate_XZ(3);
				polygons[i].rightEnd.add(start);
				polygons[i].rightEnd.y += height;

				polygons[i].bottomEnd.subtract(start);
				polygons[i].bottomEnd.rotate_XZ(3);
				polygons[i].bottomEnd.add(start);
				polygons[i].bottomEnd.y += height;

				for (int j = 0; j < polygons[i].getVertex3D().length; j++) {

					polygons[i].getVertex3D()[j].subtract(start);
					polygons[i].getVertex3D()[j].rotate_XZ(3);
					polygons[i].getVertex3D()[j].add(start);
					polygons[i].getVertex3D()[j].y += height;
				}

				polygons[i].findRealNormal();
				polygons[i].findDiffuse();

				polygons[i].update();
			}

			for (int i = 0; i < 5; i++)
				boundary[i].update();

			displacement.set(-0.003 * (GameData.sin[theta]), 0, -0.003
					* (GameData.sin[theta]));

			shadow.getRealCentre().add(displacement);

			shadow.origin.subtract(shadow.getRealCentre());
			shadow.origin.rotate_XZ(3);
			shadow.origin.add(shadow.getRealCentre());
			shadow.origin.add(displacement);

			shadow.rightEnd.subtract(shadow.getRealCentre());
			shadow.rightEnd.rotate_XZ(3);
			shadow.rightEnd.add(shadow.getRealCentre());
			shadow.rightEnd.add(displacement);

			shadow.bottomEnd.subtract(shadow.getRealCentre());
			shadow.bottomEnd.rotate_XZ(3);
			shadow.bottomEnd.add(shadow.getRealCentre());
			shadow.bottomEnd.add(displacement);

			for (int i = 0; i < 4; i++) {
				shadow.getVertex3D()[i].subtract(shadow.getRealCentre());
				shadow.getVertex3D()[i].rotate_XZ(3);
				shadow.getVertex3D()[i].add(shadow.getRealCentre());
				shadow.getVertex3D()[i].add(displacement);

			}

			shadow.update();
			if (shadow.visible) {
				Rasterizer.rasterize(shadow);
			}
		}
	}

	public Rectangle2D getBoundary2D() {

		return boundary2D;
	}

	public void draw() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].draw();
		}
	}

}