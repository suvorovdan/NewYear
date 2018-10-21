import java.util.HashMap;
import java.util.Map;


public class Present {
    /**Полная стоимость подарка*/
    private double allworth = 0;
    /**Вес подарка*/
    private double allweight = 0;
    /**Создание списка сладостей содержащихся в подарке с ключём по имени и значением в виде сладости*/
    Map<String, Sweet> Sw = new HashMap<>();
    /**Метод добавления сладости к текущему подарку*/
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
    /**Метод получения информации о содержимом, весе подарка и стоимости подарка*/
    public String info(){
        String str = "";
        for(Sweet s: Sw.values()){
            str += " " + s.getName() + " " + s.getWeight() + " кг " + s.getUnicMod() + "\n";
        }
        str += String.format("%.3f кг - общий вес, %.2f - общая стоимость подарка.\n", getAllweight(), getAllworth());
        return (str);
    }
    /**Метод удаления из подарка сладости с именем @name вес которой составляет @w */
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
            System.out.println("не найдена данная сладость");
        }
    }

    public double getAllweight() {
        return allweight;
    }

    public double getAllworth() {
        return allworth;
    }
}
