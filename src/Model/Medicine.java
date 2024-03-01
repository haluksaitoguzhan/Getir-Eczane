package Model;

public class Medicine {
	private int id;
	private String name;
	private float cost;
	private int stock;

	public Medicine() {}

	public Medicine(int id, String name, float cost, int stock) {
		this.id = id;
		this.name = name;
		this.cost = cost;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
