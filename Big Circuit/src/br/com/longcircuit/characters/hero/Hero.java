package br.com.longcircuit.characters.hero;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import br.com.longcircuit.characters.Player;
import br.com.luvia.animation.Animation;
import br.com.luvia.animation.skeletal.Bone;
import br.com.luvia.animation.skeletal.Joint;
import br.com.luvia.loader.mesh.MeshLoader;

public class Hero extends Player{
	
	private String action = "STAND";

	private double speed = 0.05;
	private double targetX = 0;
	private double targetZ = 0;
		
	private Animation animation;

	// Chest is the Pivot Joint
	private Joint chestJoint;

	// Spine
	private Bone neck;
	private Bone upperSpine;
	private Bone lowerSpine;

	// Left Arm
	private Bone leftShoulder;
	private Bone leftArm;
	private Bone leftForeArm;
	private Bone leftHand;

	// Right Arm
	private Bone rightShoulder;
	private Bone rightArm;
	private Bone rightForeArm;
	private Bone rightHand;

	// Left Leg
	private Bone leftHip;
	private Bone leftThigh;
	private Bone leftLeg;
	private Bone leftFoot;

	// Right Leg
	private Bone rightHip;
	private Bone rightThigh;
	private Bone rightLeg;
	private Bone rightFoot;

	public Hero(double x, double y, double z){
		super(x,y,z);

		name = "Hero";
		
		model = MeshLoader.getInstance().loadModel("mech/lower.obj");

		model.setColor(new Color(0xbb, 0xbb, 0xee));
		model.setX(x);
		model.setY(y);
		model.setZ(z);
		targetX = model.getX();
		targetZ = model.getZ();
		
		//model.setY(-1);		

		model.setDrawTexture(false);
	
		initBones();		
		
		assignVectors();
		
		ajustAnimation();
	}
	
	private void initBones(){

		// Dummy Pivot
		chestJoint = new Joint(0.06, 1.95, -0.01);

		// Neck Bone
		upperSpine = new Bone(0.23);
		upperSpine.setName("Upper Spine");
		chestJoint.addBone(upperSpine);
		upperSpine.rotateZOver(90);
		upperSpine.rotateXOver(355);

		// Head Bone
		neck = new Bone(0.15);
		neck.setName("Neck");
		upperSpine.getEnd().addBone(neck);
		neck.rotateZOver(90);
		neck.rotateXOver(12);

		// LeftShoulder bone
		leftShoulder = new Bone(0.22);
		leftShoulder.setName("Left Shoulder");
		chestJoint.addBone(leftShoulder);
		leftShoulder.rotateZOver(30);

		// LeftArm Bone
		leftArm = new Bone(0.430);
		leftArm.setName("Left Arm");
		leftShoulder.getEnd().addBone(leftArm);
		leftArm.rotateZOver(340);
		leftArm.rotateYOver(10);

		// LeftForeArm
		leftForeArm = new Bone(0.3);
		leftForeArm.setName("Left ForeArm");
		leftArm.getEnd().addBone(leftForeArm);
		leftForeArm.rotateZOver(345);
		leftForeArm.rotateYOver(347);

		// LeftHandBone
		leftHand = new Bone(0.2);
		leftHand.setName("Left Hand");
		leftForeArm.getEnd().addBone(leftHand);
		leftHand.rotateZOver(345);
		leftHand.rotateYOver(345);

		rightShoulder = new Bone(0.22);
		rightShoulder.setName("Right Shoulder");
		chestJoint.addBone(rightShoulder);
		rightShoulder.rotateZOver(150);

		rightArm = new Bone(0.430);
		rightArm.setName("Right Arm");
		rightShoulder.getEnd().addBone(rightArm);
		rightArm.rotateZOver(199);
		rightArm.rotateYOver(349);

		rightForeArm = new Bone(0.3);
		rightForeArm.setName("Right ForeArm");
		rightArm.getEnd().addBone(rightForeArm);
		rightForeArm.rotateZOver(196);
		rightForeArm.rotateYOver(14);

		rightHand = new Bone(0.2);
		rightHand.setName("Right Hand");
		rightForeArm.getEnd().addBone(rightHand);
		rightHand.rotateZOver(194);
		rightHand.rotateYOver(15);

		// Lower Spine Bone - Connects chest to hip
		lowerSpine = new Bone(0.68);
		lowerSpine.setName("Lower Spine");
		chestJoint.addBone(lowerSpine);
		lowerSpine.rotateZOver(270);
		lowerSpine.rotateYOver(10);

		leftHip = new Bone(0.20);
		leftHip.setName("Left Hip");
		lowerSpine.getEnd().addBone(leftHip);
		leftHip.rotateZOver(325);

		leftThigh = new Bone(0.51);
		leftThigh.setName("Left Thigh");
		leftHip.getEnd().addBone(leftThigh);
		leftThigh.rotateZOver(275);
		leftThigh.rotateYOver(10);
		leftThigh.rotateXOver(359);

		leftLeg = new Bone(0.44);
		leftLeg.setName("Left Leg");
		leftThigh.getEnd().addBone(leftLeg);
		leftLeg.rotateZOver(270);
		leftLeg.rotateYOver(355);
		leftLeg.rotateXOver(5);

		leftFoot = new Bone(0.3);
		leftFoot.setName("Left Foot");
		leftLeg.getEnd().addBone(leftFoot);
		leftFoot.rotateZOver(355);
		leftFoot.rotateYOver(272);
		leftFoot.rotateXOver(6);

		// Right Leg
		rightHip = new Bone(0.20);
		rightHip.setName("Right Hip");
		lowerSpine.getEnd().addBone(rightHip);
		rightHip.rotateZOver(215);

		rightThigh = new Bone(0.51);
		rightThigh.setName("Right Thigh");
		rightHip.getEnd().addBone(rightThigh);
		rightThigh.rotateZOver(265);
		rightThigh.rotateYOver(10);
		rightThigh.rotateXOver(359);

		rightLeg = new Bone(0.44);
		rightLeg.setName("Right Leg");
		rightThigh.getEnd().addBone(rightLeg);
		rightLeg.rotateZOver(270);
		rightLeg.rotateYOver(355);
		rightLeg.rotateXOver(5);

		rightFoot = new Bone(0.3);
		rightFoot.setName("Right Foot");
		rightLeg.getEnd().addBone(rightFoot);
		rightFoot.rotateZOver(355);
		rightFoot.rotateYOver(272);
		rightFoot.rotateXOver(6);
		
	}
	
	private void assignVectors() {

		Integer[] upperSpineSelection = { 137, 136, 139, 138, 339, 141, 140,
				143, 142, 129, 351, 128, 350, 131, 349, 130, 348, 133, 347,
				132, 346, 135, 345, 134, 344, 356, 357, 358, 359, 352, 353,
				354, 355, 123 };
		upperSpine.addVectors(arrayToList(upperSpineSelection));

		Integer[] lowerSpineSelection = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
				27, 26, 319, 318, 338, 337, 336, 326, 220, 327, 221, 324, 222,
				325, 223, 322, 216, 323, 217, 320, 218, 321, 219, 334, 335,
				332, 333, 330, 331, 328, 329, 102, 103, 110, 111, 108, 109,
				106, 227, 107, 226, 104, 225, 105, 224, 119, 118, 117, 116,
				115, 114, 113, 112, 242, 122, 243, 121, 120 };
		lowerSpine.addVectors(arrayToList(lowerSpineSelection));

		// Left Arm
		Integer[] leftArmSelection = { 343, 342, 341, 340, 387, 386, 385, 384,
				391, 390, 389, 388, 373, 372, 375, 374, 369, 368, 371, 370,
				381, 380, 383, 382, 377, 376, 379, 378, 364, 365, 366, 367,
				360, 361, 362, 363 };
		leftArm.addVectors(arrayToList(leftArmSelection));

		Integer[] leftForeArmSelection = { 410, 411, 408, 409, 414, 415, 412,
				413, 402, 403, 400, 401, 406, 407, 404, 405, 395, 394, 393,
				392, 399, 398, 397, 396 };
		leftForeArm.addVectors(arrayToList(leftForeArmSelection));

		Integer[] leftHandSelection = { 425, 424, 427, 426, 429, 428, 431, 430,
				417, 416, 419, 418, 421, 420, 423, 422 };
		leftHand.addVectors(arrayToList(leftHandSelection));

		// Right Arm
		Integer[] rightArmSelection = { 171, 170, 169, 168, 175, 174, 173, 172,
				163, 162, 161, 160, 167, 166, 165, 164, 152, 153, 154, 155,
				156, 157, 158, 159, 127, 144, 126, 145, 125, 146, 124, 147,
				148, 149, 150, 151 };
		rightArm.addVectors(arrayToList(rightArmSelection));

		Integer[] rightForeArmSelection = { 197, 196, 199, 198, 193, 192, 195,
				194, 186, 187, 184, 185, 190, 191, 188, 189, 178, 179, 176,
				177, 182, 183, 180, 181 };
		rightForeArm.addVectors(arrayToList(rightForeArmSelection));

		Integer[] rightHandSelection = { 205, 204, 207, 206, 201, 200, 203,
				202, 212, 213, 214, 215, 208, 209, 210, 211 };
		rightHand.addVectors(arrayToList(rightHandSelection));

		// Left Leg
		Integer[] leftLegSelection = { 287, 286, 258, 254, 259, 255, 252,
				256, 253, 257, 262, 250, 263, 251, 260, 248, 261, 249, 266,
				246, 267, 247, 264, 265, 268, 269 };
		leftLeg.addVectors(arrayToList(leftLegSelection));

		Integer[] leftThighSelection = { 275, 274, 273, 272, 279, 278, 277, 276,
				283, 282, 281, 280, 285, 284, 270, 271, 239, 238, 237, 236,
				235, 234, 233, 232, 231, 230, 229, 228, 244, 245, 240, 241 };
		leftThigh.addVectors(arrayToList(leftThighSelection));

		Integer[] leftFootSelection = { 305, 304, 307, 306, 309, 308, 311, 310,
				313, 312, 315, 314, 317, 316, 288, 289, 290, 291, 292, 293,
				294, 295, 296, 297, 298, 299, 300, 301, 302, 303 };
		leftFoot.addVectors(arrayToList(leftFootSelection));

		// Right Leg
		Integer[] rightThighSelection = { 68, 69, 64, 65, 66, 67, 12, 13, 14,
				15, 17, 16, 19, 18, 21, 20, 23, 22, 25, 24, 29, 28, 55, 54, 59,
				58, 57, 56, 63, 62, 61, 60 };
		rightThigh.addVectors(arrayToList(rightThighSelection));

		Integer[] rightLegSelection = { 34, 35, 32, 33, 38, 39, 36, 37, 42, 43,
				40, 41, 46, 47, 44, 45, 51, 50, 49, 48, 53, 52, 31, 30, 71, 70 };
		rightLeg.addVectors(arrayToList(rightLegSelection));

		Integer[] rightFootSelection = { 100, 101, 98, 99, 96, 97, 76, 77, 78,
				79, 72, 73, 74, 75, 85, 84, 87, 86, 81, 80, 83, 82, 93, 92, 95,
				94, 89, 88, 91, 90 };
		rightFoot.addVectors(arrayToList(rightFootSelection));

	}
	
	private List<Vector3f> arrayToList(Integer[] selection) {

		List<Vector3f> vectors = new ArrayList<Vector3f>();

		for (int i = 0; i < selection.length; i++) {
			vectors.add(model.getVertexes().get(selection[i]));
		}

		return vectors;
	}
	
	private void ajustAnimation() {

		animation = new Animation(11);
		
		// Define Initial Position

		animation.addBone(upperSpine);
		animation.addBone(neck);
		animation.addBone(lowerSpine);

		animation.addBone(leftShoulder);
		animation.addBone(leftArm);
		animation.addBone(leftForeArm);
		animation.addBone(leftHand);

		animation.addBone(rightShoulder);
		animation.addBone(rightArm);
		animation.addBone(rightForeArm);
		animation.addBone(rightHand);

		animation.addBone(leftHip);
		animation.addBone(leftThigh);
		animation.addBone(leftLeg);
		animation.addBone(leftFoot);

		animation.addBone(rightHip);
		animation.addBone(rightThigh);
		animation.addBone(rightLeg);
		animation.addBone(rightFoot);

		int frame = 0;
		// Model

		// Initial Position
		frame = 1;

		animation.getBoneFrame(upperSpine, frame).setX(6);
		animation.getBoneFrame(lowerSpine, frame).setX(8);
		
		
		animation.getBoneFrame(rightArm, frame).setX(10);
		animation.getBoneFrame(rightArm, frame).setZ(232);
		
		animation.getBoneFrame(rightForeArm, frame).setX(350);
		animation.getBoneFrame(rightForeArm, frame).setY(75);
		animation.getBoneFrame(rightForeArm, frame).setZ(245);

		animation.getBoneFrame(leftArm, frame).setX(20);
		animation.getBoneFrame(leftArm, frame).setZ(300);
		
		animation.getBoneFrame(leftForeArm, frame).setX(340);
		animation.getBoneFrame(leftForeArm, frame).setY(297);
		
		animation.getBoneFrame(leftThigh, frame).setX(320);
		animation.getBoneFrame(leftLeg, frame).setX(116);
		animation.getBoneFrame(leftFoot, frame).setX(50);
		
		animation.getBoneFrame(rightThigh, frame).setX(347);
		animation.getBoneFrame(rightLeg, frame).setX(19);
		animation.getBoneFrame(rightFoot, frame).setX(1.5);

		frame = 2;
		
		animation.getBoneFrame(upperSpine, frame).setX(10);
		animation.getBoneFrame(lowerSpine, frame).setX(9);
		
		//animation.getBoneFrame(rightArm, frame).setX(0);
		animation.getBoneFrame(rightArm, frame).setY(355);
		animation.getBoneFrame(rightArm, frame).setZ(232);
		
		animation.getBoneFrame(rightForeArm, frame).setX(1160);
		animation.getBoneFrame(rightForeArm, frame).setY(105);
		animation.getBoneFrame(rightForeArm, frame).setZ(320);

		
		animation.getBoneFrame(leftArm, frame).setY(70);
		animation.getBoneFrame(leftArm, frame).setZ(340);		
		
		animation.getBoneFrame(leftForeArm, frame).setX(270);
		animation.getBoneFrame(leftForeArm, frame).setY(297);
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(310);
		animation.getBoneFrame(leftLeg, frame).setX(107);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		frame = 3;
		
		animation.getBoneFrame(upperSpine, frame).setX(15);
		animation.getBoneFrame(lowerSpine, frame).setX(12);
		
		animation.getBoneFrame(rightArm, frame).setX(310);
		animation.getBoneFrame(rightArm, frame).setY(355);
		animation.getBoneFrame(rightArm, frame).setZ(232);
		
		animation.getBoneFrame(rightForeArm, frame).setX(1160);
		animation.getBoneFrame(rightForeArm, frame).setY(105);
		animation.getBoneFrame(rightForeArm, frame).setZ(320);

		
		animation.getBoneFrame(leftArm, frame).setX(54);
		animation.getBoneFrame(leftArm, frame).setY(70);
		animation.getBoneFrame(leftArm, frame).setZ(340);		
		
		animation.getBoneFrame(leftForeArm, frame).setX(355);
		animation.getBoneFrame(leftForeArm, frame).setY(297);
		animation.getBoneFrame(leftForeArm, frame).setZ(290);
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(290);
		animation.getBoneFrame(leftLeg, frame).setX(27);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(22);
		animation.getBoneFrame(rightLeg, frame).setX(26);
		animation.getBoneFrame(rightFoot, frame).setX(53);
		
		frame = 4;
		
		animation.getBoneFrame(upperSpine, frame).setX(14);
		animation.getBoneFrame(lowerSpine, frame).setX(11);
		
		animation.getBoneFrame(rightArm, frame).setX(280);
		animation.getBoneFrame(rightArm, frame).setY(355);
		animation.getBoneFrame(rightArm, frame).setZ(232);
		
		animation.getBoneFrame(rightForeArm, frame).setX(1160);
		animation.getBoneFrame(rightForeArm, frame).setY(105);
		animation.getBoneFrame(rightForeArm, frame).setZ(320);

		
		animation.getBoneFrame(leftArm, frame).setX(84);
		animation.getBoneFrame(leftArm, frame).setY(70);
		animation.getBoneFrame(leftArm, frame).setZ(340);		
		
		animation.getBoneFrame(leftForeArm, frame).setX(355);
		animation.getBoneFrame(leftForeArm, frame).setY(297);
		animation.getBoneFrame(leftForeArm, frame).setZ(290);
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(304);
		animation.getBoneFrame(leftLeg, frame).setX(40);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(52);
		animation.getBoneFrame(rightLeg, frame).setX(66);
		animation.getBoneFrame(rightFoot, frame).setX(23);
		
		frame = 5;
		
		animation.getBoneFrame(upperSpine, frame).setX(12);
		animation.getBoneFrame(lowerSpine, frame).setX(10);
		
		animation.getBoneFrame(leftArm, frame).setX(68);
		animation.getBoneFrame(leftArm, frame).setY(70);
		animation.getBoneFrame(leftArm, frame).setZ(340);		
		
		animation.getBoneFrame(leftForeArm, frame).setX(355);
		animation.getBoneFrame(leftForeArm, frame).setY(297);
		animation.getBoneFrame(leftForeArm, frame).setZ(290);
		
		animation.getBoneFrame(rightArm, frame).setX(270);
		animation.getBoneFrame(rightArm, frame).setY(355);
		animation.getBoneFrame(rightArm, frame).setZ(252);
		
		animation.getBoneFrame(rightForeArm, frame).setX(1160);
		animation.getBoneFrame(rightForeArm, frame).setY(105);
		animation.getBoneFrame(rightForeArm, frame).setZ(320);
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(330);
		animation.getBoneFrame(leftLeg, frame).setX(20);
		animation.getBoneFrame(leftFoot, frame).setX(356);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(12);
		animation.getBoneFrame(rightLeg, frame).setX(80);
		animation.getBoneFrame(rightFoot, frame).setX(23);
		
		frame = 6;
		
		animation.getBoneFrame(upperSpine, frame).setX(12);
		animation.getBoneFrame(lowerSpine, frame).setX(10);

		animation.getBoneFrame(leftArm, frame).setX(15);
		animation.getBoneFrame(leftArm, frame).setY(70);
		animation.getBoneFrame(leftArm, frame).setZ(340);

		animation.getBoneFrame(leftForeArm, frame).setX(323);
		animation.getBoneFrame(leftForeArm, frame).setY(317);
		animation.getBoneFrame(leftForeArm, frame).setZ(290);
		
		animation.getBoneFrame(rightArm, frame).setX(300);
		animation.getBoneFrame(rightArm, frame).setY(345);
		animation.getBoneFrame(rightArm, frame).setZ(252);
		
		animation.getBoneFrame(rightForeArm, frame).setX(1160);
		animation.getBoneFrame(rightForeArm, frame).setY(105);
		animation.getBoneFrame(rightForeArm, frame).setZ(320);		
		
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(344);
		animation.getBoneFrame(leftLeg, frame).setX(13);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(352);
		animation.getBoneFrame(rightLeg, frame).setX(90);
		animation.getBoneFrame(rightFoot, frame).setX(23);
		
		frame = 7;
		
		animation.getBoneFrame(upperSpine, frame).setX(12);
		animation.getBoneFrame(lowerSpine, frame).setX(10);

		animation.getBoneFrame(leftArm, frame).setZ(294);

		animation.getBoneFrame(leftForeArm, frame).setX(302);
		animation.getBoneFrame(leftForeArm, frame).setY(317);
		animation.getBoneFrame(leftForeArm, frame).setZ(290);
		
		animation.getBoneFrame(rightArm, frame).setY(305);
		animation.getBoneFrame(rightArm, frame).setZ(222);
		
		animation.getBoneFrame(rightForeArm, frame).setX(1160);
		animation.getBoneFrame(rightForeArm, frame).setY(105);
		animation.getBoneFrame(rightForeArm, frame).setZ(320);
		
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(14);
		animation.getBoneFrame(leftLeg, frame).setX(13);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(302);
		animation.getBoneFrame(rightLeg, frame).setX(110);
		animation.getBoneFrame(rightFoot, frame).setX(23);
		
		frame = 8;
		
		animation.getBoneFrame(upperSpine, frame).setX(13);
		animation.getBoneFrame(lowerSpine, frame).setX(11);

		animation.getBoneFrame(leftArm, frame).setX(270);
		animation.getBoneFrame(leftArm, frame).setZ(284);

		animation.getBoneFrame(leftForeArm, frame).setX(245);
		animation.getBoneFrame(leftForeArm, frame).setY(257);
		//animation.getBoneFrame(leftForeArm, frame).setZ(290);
		
		animation.getBoneFrame(rightArm, frame).setX(100);
		animation.getBoneFrame(rightArm, frame).setY(295);
		animation.getBoneFrame(rightArm, frame).setZ(222);
		
		animation.getBoneFrame(rightForeArm, frame).setX(290);
		//animation.getBoneFrame(rightForeArm, frame).setY(105);
		//animation.getBoneFrame(rightForeArm, frame).setZ(320);
		
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(34);
		animation.getBoneFrame(leftLeg, frame).setX(13);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(272);
		animation.getBoneFrame(rightLeg, frame).setX(50);
		animation.getBoneFrame(rightFoot, frame).setX(0);
		
		frame = 9;
		
		animation.getBoneFrame(upperSpine, frame).setX(14);
		animation.getBoneFrame(lowerSpine, frame).setX(19);

		animation.getBoneFrame(leftArm, frame).setX(250);
		animation.getBoneFrame(leftArm, frame).setZ(284);

		animation.getBoneFrame(leftForeArm, frame).setX(245);
		animation.getBoneFrame(leftForeArm, frame).setY(257);
		
		animation.getBoneFrame(rightArm, frame).setX(120);
		animation.getBoneFrame(rightArm, frame).setY(295);
		animation.getBoneFrame(rightArm, frame).setZ(222);
		
		animation.getBoneFrame(rightForeArm, frame).setX(290);
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(24);
		animation.getBoneFrame(leftLeg, frame).setX(103);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(300);
		animation.getBoneFrame(rightLeg, frame).setX(10);
		animation.getBoneFrame(rightFoot, frame).setX(0);
		
		frame = 10;
		
		animation.getBoneFrame(upperSpine, frame).setX(12);
		animation.getBoneFrame(lowerSpine, frame).setX(14);

		animation.getBoneFrame(leftArm, frame).setX(260);
		animation.getBoneFrame(leftArm, frame).setZ(284);

		animation.getBoneFrame(leftForeArm, frame).setX(245);
		animation.getBoneFrame(leftForeArm, frame).setY(257);
		
		animation.getBoneFrame(rightArm, frame).setX(110);
		animation.getBoneFrame(rightArm, frame).setY(295);
		animation.getBoneFrame(rightArm, frame).setZ(222);
		
		animation.getBoneFrame(rightForeArm, frame).setX(290);
		
		//Left Leg
		animation.getBoneFrame(leftThigh, frame).setX(354);
		animation.getBoneFrame(leftLeg, frame).setX(103);
		animation.getBoneFrame(leftFoot, frame).setX(0);
		
		//RightLeg
		animation.getBoneFrame(rightThigh, frame).setX(300);
		animation.getBoneFrame(rightLeg, frame).setX(35);
		animation.getBoneFrame(rightFoot, frame).setX(10);

	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public void walk(){
				
		double theta = Math.toRadians(model.getAngleY());
		
		model.setOffsetZ(speed * Math.cos(theta));
		model.setOffsetX(speed * Math.sin(theta));
		
	}
	
	public void setTarget(double x, double z) {
		targetX = x;
		targetZ = z;
	}

	public double getTargetX() {
		return targetX;
	}

	public void setTargetX(double targetX) {
		this.targetX = targetX;
	}

	public double getTargetZ() {
		return targetZ;
	}

	public void setTargetZ(double targetZ) {
		this.targetZ = targetZ;
	}
	
	public double getSpeed(){
		return speed;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}		
			
}