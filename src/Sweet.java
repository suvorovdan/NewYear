public class Sweet {
    String name;
    double weight = 0;
    double price = 0;
    String unicMod;

    Sweet(String name,double cost,String unicMod){
        this.name = name;
        this.price = cost;
        this.unicMod = unicMod;
    }

    public Sweet() {
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public double getWorth() {
        return price * weight;
    }

    public String getUnicMod() {
        return unicMod;
    }
}
