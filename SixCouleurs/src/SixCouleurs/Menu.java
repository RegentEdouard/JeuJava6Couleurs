package SixCouleurs;

public class Menu {
	public String[] text;
	public double[][] position;
	
	
	public String[] getText() {
		return text;
	}
	public void setText(String[] text) {
		this.text = text;
	}
	public double[][] getPosition() {
		return position;
	}
	public void setPosition(double[][] position) {
		this.position = position;
	}
	
	
	public Menu(String[] text, double[][] position){
		this.text = text;
		this.position = position;
	}
}
