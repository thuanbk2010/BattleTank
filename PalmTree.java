public class PalmTree extends SolidObject {

	private Polygon3D[] polygons;

	public Polygon3D shadow;

	public int angle;

	public PalmTree(double x, double y, double z, int angle) {
		this.angle = angle;

		start = new Vector(x, y, z);

		iDirection = new Vector(0.7 + 0.3 * 1.1, 0, 0);
		jDirection = new Vector(0, 0.8 + 0.3, 0);
		kDirection = new Vector(0, 0, 0.7 + 0.3 * 1.1);

		modelType = 7;
		makeBoundary(0.1, 0.25, 0.1);

		boundary2D = new Rectangle2D(x - 0.005, z + 0.005, 0.01, 0.01);
		ObstacleMap.registerObstacle1(this, (int) (x * 4)
				+ (129 - (int) (z * 4)) * 80);

		iDirection.rotate_XZ(angle);
		kDirection.rotate_XZ(angle);

		findCentre();

		makePolygons();
	}

	public Rectangle2D getBoundary2D() {
		return boundary2D;
	}

	private void makePolygons() {
		Vector[] v;
		double x = start.x;
		double y = start.y;
		double z = start.z;

		start.add(0, -0.25, 0);
		polygons = new Polygon3D[38];

		v = new Vector[] { put(-0.001, 0.1, -0.01), put(0.016, 0.1, -0.01),
				put(0.01, 0, -0.01), put(-0.014, 0, -0.01) };
		polygons[0] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		v = new Vector[] { put(-0.001, 0.1, 0.01), put(-0.001, 0.1, -0.01),
				put(-0.014, 0, -0.01), put(-0.014, 0, 0.014) };
		polygons[1] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		v = new Vector[] { put(0.016, 0.1, 0.01), put(-0.001, 0.1, 0.01),
				put(-0.014, 0, 0.014), put(0.01, 0, 0.014) };
		polygons[2] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		v = new Vector[] { put(0.016, 0.1, -0.01), put(0.016, 0.1, 0.01),
				put(0.01, 0, 0.014), put(0.01, 0, -0.01) };
		polygons[3] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		v = new Vector[] { put(0.002, 0.3, -0.008), put(0.013, 0.3, -0.008),
				put(0.016, 0.1, -0.01), put(-0.001, 0.1, -0.01) };
		polygons[4] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		v = new Vector[] { put(0.002, 0.3, 0.006), put(0.002, 0.3, -0.008),
				put(-0.001, 0.1, -0.01), put(-0.001, 0.1, 0.01) };
		polygons[5] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		v = new Vector[] { put(0.013, 0.3, 0.006), put(0.002, 0.3, 0.006),
				put(-0.001, 0.1, 0.01), put(0.016, 0.1, 0.01) };
		polygons[6] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		v = new Vector[] { put(0.013, 0.3, -0.008), put(0.013, 0.3, 0.006),
				put(0.016, 0.1, 0.01), put(0.016, 0.1, -0.01) };
		polygons[7] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[5], 0.1,
				0.5, 6);

		start.add(0.005, 0, 0);
		v = new Vector[] { put(0.015, 0.3, 0.01), put(0, 0.3, 0),
				put(0, 0.34, 0.05), put(0.015, 0.32, 0.05) };
		polygons[8] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.3, 0), put(-0.015, 0.3, 0.01),
				put(-0.015, 0.32, 0.05), put(0, 0.34, 0.05) };
		polygons[9] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(0, 0.33, 0.09),
				put(0.015, 0.31, 0.09), put(0.015, 0.32, 0.05) };
		polygons[10] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(-0.015, 0.32, 0.05),
				put(-0.015, 0.31, 0.09), put(0, 0.33, 0.09) };
		polygons[11] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.33, 0.09), put(-0.015, 0.31, 0.09),
				put(0, 0.29, 0.12) };
		polygons[12] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0.015, 0.31, 0.09), put(0, 0.33, 0.09),
				put(0, 0.29, 0.12) };
		polygons[13] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		iDirection.rotate_XZ(360 / 5);
		kDirection.rotate_XZ(360 / 5);

		v = new Vector[] { put(0.015, 0.3, 0.01), put(0, 0.3, 0),
				put(0, 0.34, 0.05), put(0.015, 0.32, 0.05) };
		polygons[14] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.3, 0), put(-0.015, 0.3, 0.01),
				put(-0.015, 0.32, 0.05), put(0, 0.34, 0.05) };
		polygons[15] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(0, 0.33, 0.09),
				put(0.015, 0.31, 0.09), put(0.015, 0.32, 0.05) };
		polygons[16] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(-0.015, 0.32, 0.05),
				put(-0.015, 0.31, 0.09), put(0, 0.33, 0.09) };
		polygons[17] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.33, 0.09), put(-0.015, 0.31, 0.09),
				put(0, 0.29, 0.12) };
		polygons[18] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0.015, 0.31, 0.09), put(0, 0.33, 0.09),
				put(0, 0.29, 0.12) };
		polygons[19] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		iDirection.rotate_XZ(360 / 5);
		kDirection.rotate_XZ(360 / 5);

		v = new Vector[] { put(0.015, 0.3, 0.01), put(0, 0.3, 0),
				put(0, 0.34, 0.05), put(0.015, 0.32, 0.05) };
		polygons[20] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.3, 0), put(-0.015, 0.3, 0.01),
				put(-0.015, 0.32, 0.05), put(0, 0.34, 0.05) };
		polygons[21] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(0, 0.33, 0.09),
				put(0.015, 0.31, 0.09), put(0.015, 0.32, 0.05) };
		polygons[22] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(-0.015, 0.32, 0.05),
				put(-0.015, 0.31, 0.09), put(0, 0.33, 0.09) };
		polygons[23] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.33, 0.09), put(-0.015, 0.31, 0.09),
				put(0, 0.29, 0.12) };
		polygons[24] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0.015, 0.31, 0.09), put(0, 0.33, 0.09),
				put(0, 0.29, 0.12) };
		polygons[25] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		iDirection.rotate_XZ(360 / 5);
		kDirection.rotate_XZ(360 / 5);

		v = new Vector[] { put(0.015, 0.3, 0.01), put(0, 0.3, 0),
				put(0, 0.34, 0.05), put(0.015, 0.32, 0.05) };
		polygons[26] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.3, 0), put(-0.015, 0.3, 0.01),
				put(-0.015, 0.32, 0.05), put(0, 0.34, 0.05) };
		polygons[27] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(0, 0.33, 0.09),
				put(0.015, 0.31, 0.09), put(0.015, 0.32, 0.05) };
		polygons[28] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(-0.015, 0.32, 0.05),
				put(-0.015, 0.31, 0.09), put(0, 0.33, 0.09) };
		polygons[29] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.33, 0.09), put(-0.015, 0.31, 0.09),
				put(0, 0.29, 0.12) };
		polygons[30] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0.015, 0.31, 0.09), put(0, 0.33, 0.09),
				put(0, 0.29, 0.12) };
		polygons[31] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		iDirection.rotate_XZ(360 / 5);
		kDirection.rotate_XZ(360 / 5);

		v = new Vector[] { put(0.015, 0.3, 0.01), put(0, 0.3, 0),
				put(0, 0.34, 0.05), put(0.015, 0.32, 0.05) };
		polygons[32] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.3, 0), put(-0.015, 0.3, 0.01),
				put(-0.015, 0.32, 0.05), put(0, 0.34, 0.05) };
		polygons[33] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(0, 0.33, 0.09),
				put(0.015, 0.31, 0.09), put(0.015, 0.32, 0.05) };
		polygons[34] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.34, 0.05), put(-0.015, 0.32, 0.05),
				put(-0.015, 0.31, 0.09), put(0, 0.33, 0.09) };
		polygons[35] = new Polygon3D(v, v[0], v[1], v[3], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0, 0.33, 0.09), put(-0.015, 0.31, 0.09),
				put(0, 0.29, 0.12) };
		polygons[36] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		v = new Vector[] { put(0.015, 0.31, 0.09), put(0, 0.33, 0.09),
				put(0, 0.29, 0.12) };
		polygons[37] = new Polygon3D(v, v[0], v[1], v[2], Main.textures[9], 1,
				1, 6);

		iDirection = new Vector(0.4 + 0.3 * 0.4, 0, 0);
		jDirection = new Vector(0, 0.5, 0);
		kDirection = new Vector(0, 0, 0.7 + 0.3 * 0.7);

		start.add(0.03, 0, 0);

		v = new Vector[] { put(-0.5, 0, 0.4), put(0.4, 0, 0.4),
				put(0.4, 0, -0.5), put(-0.5, 0, -0.5) };
		shadow = new Polygon3D(v, v[0], v[1], v[3], Main.textures[10], 1, 1, 2);

		start.set(x, y, z);
		iDirection = new Vector(0.7 + 0.3 * 1.1, 0, 0);
		jDirection = new Vector(0, 0.8 + 0.3, 0);
		kDirection = new Vector(0, 0, 0.7 + 0.3 * 1.1);

		iDirection.rotate_XZ(angle);
		kDirection.rotate_XZ(angle);
	}

	public void update() {

		tempCentre.set(centre);
		tempCentre.y = 0.25;
		tempCentre.subtract(Camera.absolutePosition);
		if (tempCentre.getLength() > 5.5) {
			polygons = null;
			visible = false;
			return;
		}

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(Camera.position);
		tempCentre.rotate_XZ(Camera.XZ_angle);
		tempCentre.rotate_YZ(Camera.YZ_angle);
		tempCentre.updateLocation();

		if (tempCentre.z < 0.9 || tempCentre.screenY < -10
				|| (tempCentre.screenX < -60 && tempCentre.z > 3)
				|| (tempCentre.screenX > 700 && tempCentre.z > 3)) {

			visible = false;
			return;
		}
		visible = true;

		ModelDrawList.register(this);

		if (polygons == null) {
			makePolygons();

		}

		for (int i = 0; i < polygons.length; i++) {
			polygons[i].update();
		}

		if (shadow != null) {
			shadow.update();
			if (shadow.visible) {
				Rasterizer.rasterize(shadow);
			}
		}
	}

	public void draw() {
		for (int i = 0; i < polygons.length; i++) {
			polygons[i].draw();
		}
	}

}