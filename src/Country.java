
public class Country {
	protected String name;
	protected int population;
	
	//Constructors
	public Country() {
		
	}
	public Country(String name, int population) {
		super();
		this.name = name;
		this.population = population;
	}
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	
	//String override
	@Override
	public String toString() {
		return "Country [name=" + name + ", population=" + population + "]";
	}
}
