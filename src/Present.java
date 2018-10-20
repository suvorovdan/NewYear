import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Present {
    double allworth = 0;
    double allweight = 0;
    Map<String, Sweet> Sw = new HashMap<>();

    public void addSweet(Sweet s){
        Sweet temp = new Sweet();
        temp = Sw.get(s.getName());
        if (temp == null) {
            Sw.put(s.getName(),s);
        }else{
            Sw.get(s.getName()).setWeight(Sw.get(s.getName()).getWeight() + s.getWeight());
        }
        allweight += s.getWeight();
        allworth += s.getWorth();
    }
    public String info(){
        String str = "";
        for(Sweet s: Sw.values()){
            str += " " + s.getName() + " " + s.getWeight() + " " + s.getUnicMod() + "\n";
        }
        str += getAllweight() + " - общий вес, " + getAllworth() + " - общая стоимость подарка.";
        return (str);
    }

    public void dellSweet(String name, double w){
        if(Sw.containsKey(name)){
            if(Sw.get(name).getWeight() >= w){
                if(Sw.get(name).getWeight() == w){
                    Sw.remove(name);
                }else{
                    Sw.get(name).setWeight(Sw.get(name).getWeight() - w);
                }
                allweight -= w;
                allworth -= Sw.get(name).getWorth();
            }else{
                System.out.println("невозможно удалить больше, поэтому удалиться имеющееся кол-во");
                allweight -= Sw.get(name).getWeight();
                allworth -= Sw.get(name).getWorth();
                Sw.remove(name);
            }
        }else{
            System.out.println("не найдена подобная сладость");
        }
    }

    public double getAllweight() {
        return allweight;
    }

    public double getAllworth() {
        return allworth;
    }
}
