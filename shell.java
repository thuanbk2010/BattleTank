public class shell extends SolidObject {
	private polygon3D[] polygons;

	private vector direction;

	private boolean isHostile;

	private int type;

	public shell(double x, double y, double z, int angle, boolean isHostile,
			int type) {
		start = new vector(x, y, z);
		this.type = type;

		iDirection = new vector(0.8, 0, 0);
		jDirection = new vector(0, 0.8, 0);
		kDirection = new vector(0, 0, 1);

		this.isHostile = isHostile;

		modelType = 4;
		makeBoundary(0.01, 0.025, 0.01);

		boundary2D = new rectangle2D(x - 0.005, z + 0.005, 0.01, 0.01);

		iDirection.rotate_XZ(angle);
		kDirection.rotate_XZ(angle);

		direction = new vector(0, 0, 0.13);
		lifeSpan = 14;

		direction.rotate_XZ(angle);

		findCentre();

		makePolygons();
	}

	public void makePolygons() {
		vector[] v;
		int diffuse;
		texture t;
		polygons = new polygon3D[16];

		if (type == 1) {
			t = main.textures[52];
			diffuse = 50;
			kDirection.scale(1.3);

		} else {
			t = main.textures[16];
			diffuse = 60;
		}

		v = new vector[] { put(-0.002, 0.05, 0.02), put(0.002, 0.05, 0.02),
				put(0.005, 0.05, -0.02), put(-0.005, 0.05, -0.02) };
		polygons[0] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.002, 0.05, 0.02), put(-0.005, 0.05, -0.02),
				put(-0.005, 0.04, -0.02), put(-0.002, 0.04, 0.02) };
		polygons[1] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(0.002, 0.04, 0.02), put(0.005, 0.04, -0.02),
				put(0.005, 0.05, -0.02), put(0.001, 0.05, 0.02) };
		polygons[2] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.002, 0.04, 0.02), put(0.002, 0.04, 0.02),
				put(0.002, 0.05, 0.02), put(-0.002, 0.05, 0.02) };
		polygons[3] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		for (int i = 0; i < 4; i++) {

			polygons[i].diffuse_I = 63;

		}

		kDirection.scale(-0.08);
		start.add(kDirection);
		kDirection.scale(-(double) 1 / 0.08);

		iDirection.scale(0.85);
		jDirection.scale(0.85);

		v = new vector[] { put(-0.005, 0.05, 0.06), put(0.005, 0.05, 0.06),
				put(0.005, 0.05, -0.06), put(-0.005, 0.05, -0.06) };
		polygons[4] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.005, 0.05, 0.06), put(-0.005, 0.05, -0.06),
				put(-0.005, 0.04, -0.06), put(-0.005, 0.04, 0.06) };
		polygons[5] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(0.005, 0.04, 0.06), put(0.005, 0.04, -0.06),
				put(0.005, 0.05, -0.06), put(0.005, 0.05, 0.06) };
		polygons[6] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.005, 0.04, 0.06), put(0.005, 0.04, 0.06),
				put(0.005, 0.05, 0.06), put(-0.005, 0.05, 0.06) };
		polygons[7] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		for (int i = 4; i < 8; i++) {
			polygons[i].alpha = 192;
			polygons[i].diffuse_I = diffuse;
		}

		kDirection.scale(1.4);

		kDirection.scale(-0.11);
		start.add(kDirection);
		kDirection.scale(-(double) 1 / 0.11);

		iDirection.scale(0.8);
		jDirection.scale(0.8);

		v = new vector[] { put(-0.005, 0.05, 0.06), put(0.005, 0.05, 0.06),
				put(0.005, 0.05, -0.06), put(-0.005, 0.05, -0.06) };
		polygons[8] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.005, 0.05, 0.06), put(-0.005, 0.05, -0.06),
				put(-0.005, 0.04, -0.06), put(-0.005, 0.04, 0.06) };
		polygons[9] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(0.005, 0.04, 0.06), put(0.005, 0.04, -0.06),
				put(0.005, 0.05, -0.06), put(0.005, 0.05, 0.06) };
		polygons[10] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.005, 0.04, 0.06), put(0.005, 0.04, 0.06),
				put(0.005, 0.05, 0.06), put(-0.005, 0.05, 0.06) };
		polygons[11] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		for (int i = 8; i < 12; i++) {
			polygons[i].alpha = 208;
			polygons[i].diffuse_I = diffuse;
		}

		kDirection.scale(-0.11);
		start.add(kDirection);
		kDirection.scale(-(double) 1 / 0.11);

		iDirection.scale(0.75);
		jDirection.scale(0.75);

		v = new vector[] { put(-0.005, 0.05, 0.06), put(0.005, 0.05, 0.06),
				put(0.005, 0.05, -0.06), put(-0.005, 0.05, -0.06) };
		polygons[12] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.005, 0.05, 0.06), put(-0.005, 0.05, -0.06),
				put(-0.005, 0.04, -0.06), put(-0.005, 0.04, 0.06) };
		polygons[13] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(0.005, 0.04, 0.06), put(0.005, 0.04, -0.06),
				put(0.005, 0.05, -0.06), put(0.005, 0.05, 0.06) };
		polygons[14] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		v = new vector[] { put(-0.005, 0.04, 0.06), put(0.005, 0.04, 0.06),
				put(0.005, 0.05, 0.06), put(-0.005, 0.05, 0.06) };
		polygons[15] = new polygon3D(v, v[0], v[1], v[3], t, 1, 1, 6);

		for (int i = 12; i < 16; i++) {
			polygons[i].alpha = 224;
			polygons[i].diffuse_I = diffuse;
		}
	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void update() {
		visible = true;

		lifeSpan--;

		boundary2D.update(direction);

		int position = (int) (boundary2D.xPos * 4)
				+ (129 - (int) (boundary2D.yPos * 4)) * 80;
		if (obstacleMap.projectileCollideObstacle2(this, position, isHostile)) {
			lifeSpan = -1;
			direction.scale(0.5);
			centre.add(direction);
			explosion e = new explosion(centre.x, centre.y, centre.z, 1);
			e.type = this.type;
			if (type == 1)
				e.damage = 10;
			projectiles.register(e);

			return;
		}

		modelDrawList.register(this);

		centre.add(direction);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++)
				boundary[i].vertex3D[j].add(direction);
			boundary[i].update();
		}

		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(camera.position);
		tempCentre.rotate_XZ(camera.XZ_angle);
		tempCentre.rotate_YZ(camera.YZ_angle);

		for (int j = 0; j < polygons.length; j++) {
			for (int i = 0; i < polygons[j].vertex3D.length; i++) {

				polygons[j].vertex3D[i].add(direction);

			}
		}

		for (int i = 0; i < polygons.length; i++) {
			polygons[i].update();
		}

		if (lifeSpan < 0) {
			explosion e = new explosion(centre.x, centre.y, centre.z, 1);
			e.type = this.type;
			if (type == 1)
				e.damage = 10;
			projectiles.register(e);

		}
	}

	public void draw() {
		for (int i = 0; i < polygons.length; i++) {
			if (lifeSpan > 10 && i == 4)
				break;
			polygons[i].draw();
		}
	}
}
