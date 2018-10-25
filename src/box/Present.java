package box;

import Sweets.Sweet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;


public class Present {
    /**Предикат определяющий добавлять сладость по её ценности или нет.*/
    private Predicate<Sweet> notExpensive = (sweet) -> sweet.getWorth() <= 30;
    /**Метод определяющий новую политику добавления сладостей*/
    public void setPolicity(Predicate<Sweet> predicate) {
        this.notExpensive = predicate;
    }
    /**Метод добавляющий новые условия к существующей политике*/
    public void addPolicity(Predicate<Sweet> predicate){
        this.notExpensive = this.notExpensive.and(predicate);
    }

    /**Полная стоимость подарка*/
    private double allworth = 0;
    /**Вес подарка*/
    private double allweight = 0;
    /**Создание списка сладостей содержащихся в подарке с ключём по имени и значением в виде сладости*/
    Map<String, Sweet> Sw = new HashMap<>();
    /**Метод добавления сладости к текущему подарку*/
    public void addSweet(Sweet s){
        if(notExpensive.test(s)){
            Sweet temp = new Sweet();
            temp = Sw.get(s.getName());
            if (temp == null) {
                Sw.put(s.getName(),s);
            }else{
                Sw.get(s.getName()).setWeight(Sw.get(s.getName()).getWeight() + s.getWeight());
            }
            allweight += s.getWeight();
            allworth += s.getWorth();
        }else{
            System.out.println(String.format("Сладость %s не добавлена т.к. слишком дорогая \n",s.getName()));
        }
    }
//  Костыль для работы @SweetsFabricWithPolicity
    public void addSweetsForFabricWithPolicity(Sweet s){
        if(s != null){
            Sweet temp = new Sweet();
            temp = Sw.get(s.getName());
            if (temp == null) {
                Sw.put(s.getName(),s);
            }else{
                Sw.get(s.getName()).setWeight(Sw.get(s.getName()).getWeight() + s.getWeight());
            }
            allweight += s.getWeight();
            allworth += s.getWorth();
        }else{
            System.out.println("Сладость не удовлетворяет условиям политики \n");
        }
    }

    /**Метод получения информации о содержимом, весе подарка и стоимости подарка*/
    public String info(){
        String str = "";
        for(Sweet s: Sw.values()){
            str += String.format("%s %.3f кг %s \n",s.getName(),s.getWeight(),s.getUnicMod());
        }
        str += String.format("%.3f кг - общий вес, %.2f золотых - общая стоимость подарка.\n",
                getAllweight(), getAllworth());
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

    public int getSize(){
        return Sw.size();
    }

    public double ExchangeToCurrency(Currency currency){
        Function<Double,Double> form;
        switch (currency){
            case Silver:
                form = d -> d * 84.5;
                return form.apply(allworth);
            case Dollar:
                form = d -> d * 1260;
                return form.apply(allworth);
            case Platinum:
                form = d -> d * 1.53;
                return form.apply(allworth);
            default:
                throw new IllegalArgumentException("Неверный тип валюты");
        }

    }

    public double Ex(Function<Double,Double> form){
        return form.apply(allworth);
    }
    /*ICostCalculator iCostCalculator = allwo -> {
        return Math.abs(allwo/100);
    };*/

}
