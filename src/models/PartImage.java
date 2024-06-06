package models;
import java.awt.Image;

public class PartImage {
	private Image imageIcon;
	private int type;

	private int iconId;

	private double dx;

	private double dy;

	private double divDx = 4;
	private double divDy = 4;

	public void setImageIcon(Image imageIcon) {
		this.imageIcon = imageIcon;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public String toString() {
		return "PartInfo(imageIcon=" + getImageIcon() + ", iconId=" + getIconId() + ", dx=" + getDx() + ", dy="
				+ getDy() + ")";
	}

	public String exportData() {

		return "[" + this.iconId + "," + (int) (this.getDx() / divDx) + "," + (int) (this.getDy() / divDy) + "]";
	}

	public Image getImageIcon() {
		return this.imageIcon;
	}

	public int getIconId() {
		return this.iconId;
	}

	public double getDx() {
		return this.dx;
	}

	public double getDy() {
		return this.dy;
	}

	public PartImage(int iconId, Image img, double dx, double dy, int type) {
		this.imageIcon = img;
		this.iconId = iconId;
		this.dx = dx;
		this.dy = dy;
		this.type = type;
	}

	public void setIconId(Image img, int id) {
		try {
			this.iconId = id;
			this.imageIcon = img;
		} catch (Exception exception) {
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
