
public class GameObject {
    protected String name;
    protected int HP;
    protected int MP;
    protected int attack;
    protected int defence;
    protected ID id;
    private String taunt;
    
    public GameObject(String name, ID id) {
	this.name = name;
	this.id = id;
    }
    
    public String getName() {
	return this.name;
    }
    
    public int attack() {
	return this.attack;
    }
    
    public int defend() {
	return this.defence;
    }
    
    public int getHP() {
	return this.HP;
    }
    
    public int getMP() {
	return this.MP;
    }
    
    public String taunt() {
	return this.taunt;
    }

    public void setTaunt(String taunt) {
	this.taunt = taunt;
    }
}
