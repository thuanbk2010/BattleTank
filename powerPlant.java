public class powerPlant extends SolidObject {

	private polygon3D[] polygons;

	public polygon3D shadow;

	public vector tempVector = new vector(0, 0, 0);
	public vector tempVector0 = new vector(0, 0, 0);
	public vector tempVector1 = new vector(0, 0, 0);
	public vector tempVector3 = new vector(0, 0, 0);

	public vector[] smokeCentres;

	public vector[] smokeCentresCamera;

	public vector smokeBottom;
	public vector smokeBottomCamera;

	public int countDownToDeath;

	public int position1, position2, position3, position4;

	public explosion[] explosions;

	public powerPlant(double x, double y, double z) {
		start = new vector(x, y, z);
		iDirection = new vector(1.1, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1.1);

		iDirection.rotate_XZ(255);
		kDirection.rotate_XZ(255);

		modelType = 3;
		makeBoundary(0.25, 0.5, 0.25);

		boundary2D = new rectangle2D(x - 0.2, z + 0.2, 0.4, 0.4);
		position1 = (int) ((x - 0.125) * 4) + (129 - (int) ((z + 0.125) * 4))
				* 80;
		position2 = (int) ((x + 0.125) * 4) + (129 - (int) ((z + 0.125) * 4))
				* 80;
		position3 = (int) ((x - 0.125) * 4) + (129 - (int) ((z - 0.125) * 4))
				* 80;
		position4 = (int) ((x + 0.125) * 4) + (129 - (int) ((z - 0.125) * 4))
				* 80;
		obstacleMap.registerObstacle2(this, position1);
		obstacleMap.registerObstacle2(this, position2);
		obstacleMap.registerObstacle2(this, position3);
		obstacleMap.registerObstacle2(this, position4);

		HP = 500;

		findCentre();
		centre.set(start);

		explosions = new explosion[11];

		makePolygons();

		iDirection = new vector(1.1, 0, 0);
		jDirection = new vector(0, 1, 0);
		kDirection = new vector(0, 0, 1.1);

		smokeCentres = new vector[10];
		smokeCentresCamera = new vector[10];
		for (int i = 0; i < 10; i++) {
			smokeCentres[i] = put(-0.065, 0.4 + i * 0.035, 0.08);
			smokeCentresCamera[i] = new vector(1, 1, 1);
		}

		smokeBottom = put(-0.065, 0.4, 0.08);
		smokeBottomCamera = new vector(0, 0, 0);

		lifeSpan = 1;

	}

	public void makePolygons() {
		polygons = new polygon3D[36 + 36 + 2 + 32 + 32 + 32];
		vector[] v;

		int index = 0;

		double theta = Math.PI / 16;

		double r = 0.12;

		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * Math.cos((i + 1) * theta), 0.18,
							r * Math.sin((i + 1) * theta)),
					put(r * Math.cos(i * theta), 0.18, r * Math.sin(i * theta)),
					put(r * Math.cos(i * theta), 0, r * Math.sin(i * theta)),
					put(r * Math.cos((i + 1) * theta), 0,
							r * Math.sin((i + 1) * theta))

			};

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[53], 0.25, 1, 6);
		}

		index += 18;

		v = new vector[] {
				put(0.12 * Math.cos(0 * theta), 0.18,
						0.12 * Math.sin(0 * theta)),
				put(0.17 * Math.cos(0 * theta), 0.16,
						0.17 * Math.sin(0 * theta)),
				put(0.2 * Math.cos(0 * theta), 0.13, 0.2 * Math.sin(0 * theta)),
				put(0.2 * Math.cos(0 * theta), 0, 0.2 * Math.sin(0 * theta)),
				put(0.12 * Math.cos(0 * theta), 0, 0.12 * Math.sin(0 * theta)), };
		polygons[index] = new polygon3D(v, v[0], put(0.2 * Math.cos(0 * theta),
				0.18, 0.2 * Math.sin(0 * theta)), v[4], main.textures[53], 0.5,
				1, 6);

		index += 1;

		v = new vector[] {
				put(0.12 * Math.cos(18 * theta), 0, 0.12 * Math.sin(18 * theta)),
				put(0.2 * Math.cos(18 * theta), 0, 0.2 * Math.sin(18 * theta)),
				put(0.2 * Math.cos(18 * theta), 0.13,
						0.2 * Math.sin(18 * theta)),
				put(0.17 * Math.cos(18 * theta), 0.16,
						0.17 * Math.sin(18 * theta)),
				put(0.12 * Math.cos(18 * theta), 0.18,
						0.12 * Math.sin(18 * theta)) };
		polygons[index] = new polygon3D(v, put(0.2 * Math.cos(18 * theta),
				0.18, 0.2 * Math.sin(18 * theta)), v[4], v[1],
				main.textures[53], 0.5, 1, 6);
		index += 1;

		double delta = Math.PI / 8;
		start.add(-0.05, 0, 0);
		r = 0.085;
		double r2 = 0.06;
		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r2 * Math.cos(i * delta), 0.18,
							r2 * Math.sin(i * delta)),
					put(r2 * Math.cos((i + 1) * delta), 0.18,
							r2 * Math.sin((i + 1) * delta)),
					put(r * Math.cos((i + 1) * delta), 0,
							r * Math.sin((i + 1) * delta)),
					put(r * Math.cos(i * delta), 0, r * Math.sin(i * delta)) };

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[41], 0.25, 1, 6);
		}
		start.add(0.05, 0, -0);
		index += 16;

		r = 0.2;

		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), 0.13, r * Math.sin(i * theta)),
					put(r * Math.cos((i + 1) * theta), 0.13,
							r * Math.sin((i + 1) * theta)),
					put(r * Math.cos((i + 1) * theta), 0,
							r * Math.sin((i + 1) * theta)),
					put(r * Math.cos(i * theta), 0, r * Math.sin(i * theta)) };

			tempVector.set(v[1]);
			tempVector.subtract(v[0]);

			tempVector.scale(1);
			tempVector.scale(i % 2);

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[53], 0.5, 1, 6);
		}

		index += 18;

		r2 = 0.17;

		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r2 * Math.cos(i * theta), 0.16,
							r2 * Math.sin(i * theta)),
					put(r2 * Math.cos((i + 1) * theta), 0.16,
							r2 * Math.sin((i + 1) * theta)),
					put(r * Math.cos((i + 1) * theta), 0.13,
							r * Math.sin((i + 1) * theta)),
					put(r * Math.cos(i * theta), 0.13, r * Math.sin(i * theta)) };

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[55], 0.25, 1, 6);
		}

		index += 18;

		r = 0.12;

		for (int i = 0; i < 18; i++) {
			v = new vector[] {
					put(r * Math.cos(i * theta), 0.18, r * Math.sin(i * theta)),
					put(r * Math.cos((i + 1) * theta), 0.18,
							r * Math.sin((i + 1) * theta)),
					put(r2 * Math.cos((i + 1) * theta), 0.16,
							r2 * Math.sin((i + 1) * theta)),
					put(r2 * Math.cos(i * theta), 0.16,
							r2 * Math.sin(i * theta)) };

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[54], 0.25, 0.4, 6);
		}

		index += 18;

		start.add(-0.05, 0, 0);
		r = 0.05;
		r2 = 0.05;
		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r * Math.cos(i * delta), 0.18, r * Math.sin(i * delta)),
					put(r * Math.cos((i + 1) * delta), 0.18,
							r * Math.sin((i + 1) * delta)),
					put(r2 * Math.cos((i + 1) * delta), 0.38,
							r2 * Math.sin((i + 1) * delta)),
					put(r2 * Math.cos(i * delta), 0.38,
							r2 * Math.sin(i * delta))

			};

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[41], 0.25, 1, 6);
		}
		start.add(0.05, 0, -0);
		index += 16;

		start.add(-0.05, 0, 0);
		r = 0.06;
		r2 = 0.058;
		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r2 * Math.cos(i * delta), 0.26,
							r2 * Math.sin(i * delta)),
					put(r2 * Math.cos((i + 1) * delta), 0.26,
							r2 * Math.sin((i + 1) * delta)),
					put(r * Math.cos((i + 1) * delta), 0.18,
							r * Math.sin((i + 1) * delta)),
					put(r * Math.cos(i * delta), 0.18, r * Math.sin(i * delta)) };

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[41], 0.25, 0.25, 6);
		}
		start.add(0.05, 0, -0);
		index += 16;

		start.add(-0.05, 0, 0);
		r = 0.058;
		r2 = 0.059;
		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r2 * Math.cos(i * delta), 0.32,
							r2 * Math.sin(i * delta)),
					put(r2 * Math.cos((i + 1) * delta), 0.32,
							r2 * Math.sin((i + 1) * delta)),
					put(r * Math.cos((i + 1) * delta), 0.26,
							r * Math.sin((i + 1) * delta)),
					put(r * Math.cos(i * delta), 0.26, r * Math.sin(i * delta)) };

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[41], 0.25, 0.25, 6);
		}
		start.add(0.05, 0, -0);
		index += 16;

		start.add(-0.05, 0, 0);
		r = 0.059;
		r2 = 0.06;
		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r2 * Math.cos(i * delta), 0.38,
							r2 * Math.sin(i * delta)),
					put(r2 * Math.cos((i + 1) * delta), 0.38,
							r2 * Math.sin((i + 1) * delta)),
					put(r * Math.cos((i + 1) * delta), 0.32,
							r * Math.sin((i + 1) * delta)),
					put(r * Math.cos(i * delta), 0.32, r * Math.sin(i * delta)) };

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[41], 0.25, 0.25, 6);
		}
		start.add(0.05, 0, -0);
		index += 16;

		start.add(-0.05, 0, 0);
		r = 0.05;
		r2 = 0.06;
		for (int i = 0; i < 16; i++) {
			v = new vector[] {
					put(r * Math.cos(i * delta), 0.38, r * Math.sin(i * delta)),
					put(r * Math.cos((i + 1) * delta), 0.38,
							r * Math.sin((i + 1) * delta)),
					put(r2 * Math.cos((i + 1) * delta), 0.38,
							r2 * Math.sin((i + 1) * delta)),
					put(r2 * Math.cos(i * delta), 0.38,
							r2 * Math.sin(i * delta)) };

			if (i % 4 != 1) {
				tempVector.set(v[1]);
				tempVector.subtract(v[0]);

				tempVector.scale(0.25);
				tempVector.scale(i % 4);
			}

			tempVector0.set(v[0]);
			tempVector0.add(tempVector);
			tempVector1.set(v[1]);
			tempVector1.add(tempVector);
			tempVector3.set(v[3]);
			tempVector3.add(tempVector);

			polygons[i + index] = new polygon3D(v, tempVector0, tempVector1,
					tempVector3, main.textures[41], 0.25, 0.25, 6);
		}
		start.add(0.05, 0, -0);
		index += 16;

		for (int i = 90; i < 170; i++) {
			polygons[i].diffuse_coefficient = 0.75;
			polygons[i].findDiffuse();
		}

		iDirection.rotate_XZ(105);
		kDirection.rotate_XZ(105);
		iDirection.scale(1.1);
		kDirection.scale(0.85);

		start.add(0.02, 0, -0.085);

		v = new vector[] { put(-0.5, 0, 0.4), put(0.4, 0, 0.4),
				put(0.4, 0, -0.5), put(-0.5, 0, -0.5) };
		shadow = new polygon3D(v, v[0], v[1], v[3], main.textures[56], 1, 1, 2);

	}

	public rectangle2D getBoundary2D() {
		return boundary2D;
	}

	public void update() {
		tempCentre.set(centre);
		tempCentre.y = -1;
		tempCentre.subtract(camera.position);
		tempCentre.rotate_XZ(camera.XZ_angle);
		tempCentre.rotate_YZ(camera.YZ_angle);
		tempCentre.updateLocation();

		if (tempCentre.z < 0.5 || tempCentre.screenY < -30
				|| tempCentre.screenX < -400 || tempCentre.screenX > 800) {
			visible = false;
			return;
		}
		visible = true;
		modelDrawList.register(this);

		for (int i = 0; i < 5; i++)
			boundary[i].update();

		if (HP <= 0) {
			countDownToDeath++;

			if (countDownToDeath == 10) {
				explosions[0] = new explosion(centre.x, centre.y, centre.z, 1.7);
				explosions[0].explicitDrawing = true;
			}

			if (countDownToDeath == 13) {
				explosions[1] = new explosion(centre.x + 0.2, centre.y,
						centre.z, 1.7);
				explosions[1].explicitDrawing = true;
			}

			if (countDownToDeath == 16) {
				explosions[2] = new explosion(centre.x + 0.1, centre.y - 0.1,
						centre.z + 0.1, 1.7);
				explosions[2].explicitDrawing = true;
			}

			if (countDownToDeath == 16) {
				explosions[3] = new explosion(centre.x, centre.y - 0.2,
						centre.z, 1.7);
				explosions[3].explicitDrawing = true;
			}

			if (countDownToDeath == 19) {
				explosions[4] = new explosion(centre.x - 0.1, centre.y,
						centre.z + 0.15, 1.7);
				explosions[4].explicitDrawing = true;
			}

			if (countDownToDeath == 19) {
				explosions[4] = new explosion(centre.x, centre.y + 0.1,
						centre.z + 0.15, 1.7);
				explosions[4].explicitDrawing = true;
			}

			if (countDownToDeath == 19) {
				explosions[5] = new explosion(centre.x - 0.05, centre.y + 0.05,
						centre.z, 1.7);
				explosions[5].explicitDrawing = true;
			}

			if (countDownToDeath == 19) {
				explosions[6] = new explosion(centre.x + 0.01, centre.y + 0.05,
						centre.z - 0.01, 1.7);
				explosions[6].explicitDrawing = true;
			}

			if (countDownToDeath == 22) {
				explosions[7] = new explosion(centre.x + 0.01, centre.y + 0.15,
						centre.z - 0.01, 1.7);
				explosions[7].explicitDrawing = true;
			}

			if (countDownToDeath == 22) {
				explosions[8] = new explosion(centre.x + 0.15, centre.y + 0.1,
						centre.z + 0.1, 1.7);
				explosions[8].explicitDrawing = true;
			}

			if (countDownToDeath == 22) {
				explosions[9] = new explosion(centre.x - 0.15, centre.y - 0.03,
						centre.z + 0.1, 1.7);
				explosions[9].explicitDrawing = true;
			}

			if (countDownToDeath == 25) {
				explosions[10] = new explosion(centre.x, centre.y + 0.05,
						centre.z, 3.5);
				explosions[10].explicitDrawing = true;
			}

			for (int i = 0; i < explosions.length; i++) {
				if (explosions[i] != null) {

					if (explosions[i].lifeSpan < 0) {
						explosions[i] = null;

						continue;
					}
					explosions[i].update();
				}
			}

			if (countDownToDeath == 30) {
				obstacleMap.removeObstacle2(position1);
				obstacleMap.removeObstacle2(position2);
				obstacleMap.removeObstacle2(position3);
				obstacleMap.removeObstacle2(position4);

				powerUps.register(new powerUp(centre.x, -0.875, centre.z + 0.1,
						4));
				powerUps.register(new powerUp(centre.x - 0.09, -0.875,
						centre.z - 0.05, 4));
				powerUps.register(new powerUp(centre.x + 0.09, -0.875,
						centre.z - 0.05, 4));
			}

			if (countDownToDeath > 35) {
				lifeSpan = 0;

			}

		}

		for (int i = 0; i < polygons.length; i++) {
			polygons[i].update();
		}

		for (int i = 0; i < smokeCentres.length; i++) {
			smokeCentres[i].y += 0.005;
			smokeCentres[i].x -= (0.002 * Math.random() - 0.001);
			smokeCentres[i].z -= (0.002 * Math.random() - 0.001);

			if (smokeCentres[i].y >= -0.275) {
				if (HP > 250)
					smokeCentres[i].set(smokeBottom);
				else if (HP < 250 && HP > 0) {
					if (gameData.getRandom() > 96)
						smokeCentres[i].set(smokeBottom);
					else
						smokeCentres[i].set(0, 0, 1500);
				} else {
					smokeCentres[i].set(0, 0, 1500);
				}
			}

			smokeCentresCamera[i].set(smokeCentres[i]);
			smokeCentresCamera[i].subtract(camera.position);
			smokeCentresCamera[i].rotate_XZ(camera.XZ_angle);
			smokeCentresCamera[i].rotate_YZ(camera.YZ_angle);
			smokeCentresCamera[i].updateLocation();
		}

		smokeBottomCamera.set(smokeBottom);
		if (HP < 250)
			smokeBottomCamera.set(0, 0, 1500);
		smokeBottomCamera.subtract(camera.position);
		smokeBottomCamera.rotate_XZ(camera.XZ_angle);
		smokeBottomCamera.rotate_YZ(camera.YZ_angle);
		smokeBottomCamera.updateLocation();

		if (shadow != null && countDownToDeath < 30) {
			shadow.update();
			if (shadow.visible) {
				rasterizer.rasterize(shadow);
			}
		}

	}

	public void draw() {
		if (visible) {

			if (countDownToDeath < 30)
				for (int i = 0; i < polygons.length; i++) {
					polygons[i].draw();
				}

			if (HP < 0) {
				for (int i = 0; i < explosions.length; i++) {
					if (explosions[i] != null) {
						explosions[i].draw();

					}
				}
			}

			double ratio = 1;
			int alpha = 255;

			ratio = 0.6 * ((smokeBottom.y + 0.9) / 0.3) / smokeBottomCamera.z;
			rasterizer.renderSmokSprite(main.textures[57].smoke, ratio,
					smokeBottomCamera.screenX, smokeBottomCamera.screenY, 64,
					64, 50);

			for (int i = 0; i < smokeCentres.length; i++) {

				ratio = 0.6 * ((smokeCentres[i].y + 0.9) / 0.3)
						/ smokeCentresCamera[i].z;

				alpha = (int) ((smokeCentres[i].y + 0.75) * 3 * 190);
				if (alpha > 255)
					alpha = 255;
				rasterizer.renderSmokSprite(main.textures[57].smoke, ratio,
						smokeCentresCamera[i].screenX,
						smokeCentresCamera[i].screenY, 64, 64, alpha);
			}

		}
	}

	public void damage(int damagePoint) {

		HP -= damagePoint;

	}
}
