/*
 * 文件名：Face.java
 * 版权：<版权>
 * 描述：<描述>
 * 创建人：xiaoying
 * 创建时间：2013-5-10
 * 修改人：xiaoying
 * 修改时间：2013-5-10
 * 版本：v1.0
 */
package com.xiaoying.faceplusplus.api.entity;
/**
 * 功能：人脸实体类
 * @author xiaoying
 */
public class Face {
	// face array 被检测出的人脸的列表
	private String face_id; //被检测出的每一张人脸都在Face++系统中的标识符
	private Position position;
	private Attribute attribute;// attribute object 包含一系列人脸的属性分析结果 gender object 包含性别分析结果，value的值为Male/Female, confidence表示置信度
	private String tag;
	
	public String getFace_id() {
		return face_id;
	}

	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "Face [face_id=" + face_id + ", position=" + position
				+ ", attribute=" + attribute + ", tag=" + tag + "]";
	}

	/**
	 * 位置信息
	 * @author xiaoying
	 */
	public static class Position {
		private float width; // 0~100之间的实数，表示检出的脸的宽度在图片中百分比
		private float height; // 0~100之间的实数，表示检出的脸的高度在图片中百分比
		private PointF center; // 检出的人脸框的中心点坐标, x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
		private PointF eye_left; // 相应人脸的左眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
		private PointF eye_right; // 相应人脸的右眼坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
		private PointF mouth_left; // 相应人脸的左侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
		private PointF mouth_right; // 相应人脸的右侧嘴角坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比(0~100之间的实数)
		private PointF nose; // 相应人脸的鼻尖坐标，x & y 坐标分别表示在图片中的宽度和高度的百分比 (0~100之间的实数)
		public float getWidth() {
			return width;
		}
		public void setWidth(float width) {
			this.width = width;
		}
		public float getHeight() {
			return height;
		}
		public void setHeight(float height) {
			this.height = height;
		}
		public PointF getCenter() {
			return center;
		}
		public void setCenter(PointF center) {
			this.center = center;
		}
		public PointF getEye_left() {
			return eye_left;
		}
		public void setEye_left(PointF eye_left) {
			this.eye_left = eye_left;
		}
		public PointF getEye_right() {
			return eye_right;
		}
		public void setEye_right(PointF eye_right) {
			this.eye_right = eye_right;
		}
		public PointF getMouth_left() {
			return mouth_left;
		}
		public void setMouth_left(PointF mouth_left) {
			this.mouth_left = mouth_left;
		}
		public PointF getMouth_right() {
			return mouth_right;
		}
		public void setMouth_right(PointF mouth_right) {
			this.mouth_right = mouth_right;
		}
		public PointF getNose() {
			return nose;
		}
		public void setNose(PointF nose) {
			this.nose = nose;
		}
		@Override
		public String toString() {
			return "Position [width=" + width + ", height=" + height
					+ ", center=" + center + ", eye_left=" + eye_left
					+ ", eye_right=" + eye_right + ", mouth_left=" + mouth_left
					+ ", mouth_right=" + mouth_right + ", nose=" + nose + "]";
		}
		
	}

	/**
	 * 人脸属性分析
	 * @author xiaoying
	 */
	public static class Attribute {
		// attribute object 包含一系列人脸的属性分析结果 gender object 包含性别分析结果，value的值为Male/Female, confidence表示置信度
		private Gender gender;
		// age object 包含年龄分析结果，value的值为一个非负整数表示估计的年龄, range表示估计年龄的正负区间
		private Age age;
		// race object 包含人种分析结果，value的值为Asian/White/Black, confidence表示置信度
		private Race race;
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public Age getAge() {
			return age;
		}
		public void setAge(Age age) {
			this.age = age;
		}
		public Race getRace() {
			return race;
		}
		public void setRace(Race race) {
			this.race = race;
		}
		@Override
		public String toString() {
			return "Attribute [gender=" + gender + ", age=" + age + ", race="
					+ race + "]";
		}
		
	}

	/**
	 * 性别
	 * @author xiaoying
	 */
	public static class Gender {
		// gender object 包含性别分析结果，value的值为Male/Female, confidence表示置信度
		private String value; //值为Male/Female
		private float confidence; //置信度
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public float getConfidence() {
			return confidence;
		}
		public void setConfidence(float confidence) {
			this.confidence = confidence;
		}
		@Override
		public String toString() {
			return "Gender [value=" + value + ", confidence=" + confidence
					+ "]";
		}

	}

	/**
	 * 年龄
	 * @author xiaoying
	 */
	public static class Age {
		// age object 包含年龄分析结果，value的值为一个非负整数表示估计的年龄, range表示估计年龄的正负区间
		private int value;	//一个非负整数表示估计的年龄
		private int range;	//估计年龄的正负区间
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getRange() {
			return range;
		}
		public void setRange(int range) {
			this.range = range;
		}
		@Override
		public String toString() {
			return "Age [value=" + value + ", range=" + range + "]";
		}
		
	}

	/**
	 * 种族
	 * @author xiaoying
	 */
	public static class Race {
		// race object 包含人种分析结果，value的值为Asian/White/Black, confidence表示置信度
		private String value; //值为Asian/White/Black
		private float confidence; //置信度
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public float getConfidence() {
			return confidence;
		}
		public void setConfidence(float confidence) {
			this.confidence = confidence;
		}
		@Override
		public String toString() {
			return "Race [value=" + value + ", confidence=" + confidence + "]";
		}
		
	}
}
