package Entity;
import Main.ID;

public class GameObject {
    protected String name;
    protected int HP;
    protected int MP;
    protected int damage;
    protected int defence;
    protected ID id;
    protected int hitChance;
    private String taunt;
    public boolean isDead = false;
    


    public GameObject(String name, ID id) {
	this.name = name;
	this.id = id;
    }
    
    public String getName() {
	return this.name;
    }
    
    public int attack() {
	return this.damage;
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
