package box;

import Sweets.Sweet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.*;
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
    Map<String, Sweet> presentBox = new HashMap<>();
    /**Метод добавления сладости к текущему подарку*/
    public void addSweet(Sweet s){
        if(notExpensive.test(s)){
            Sweet temp = new Sweet();
            temp = presentBox.get(s.getName());
            if (temp == null) {
                presentBox.put(s.getName(),s);
            }else{
                presentBox.get(s.getName()).setWeight(presentBox.get(s.getName()).getWeight() + s.getWeight());
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
            temp = presentBox.get(s.getName());
            if (temp == null) {
                presentBox.put(s.getName(),s);
            }else{
                presentBox.get(s.getName()).setWeight(presentBox.get(s.getName()).getWeight() + s.getWeight());
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
        for(Sweet s: presentBox.values()){
            str += String.format("%s %.3f кг %s \n",s.getName(),s.getWeight(),s.getUnicMod());
        }
        str += String.format("%.3f кг - общий вес, %.2f золотых - общая стоимость подарка.\n",
                getAllweight(), getAllworth());
        return (str);
    }
    /**Метод удаления из подарка сладости с именем @name вес которой составляет @w */
    public void dellSweet(String name, double w){
        if(presentBox.containsKey(name)){
            if(presentBox.get(name).getWeight() >= w){
                if(presentBox.get(name).getWeight() == w){
                    presentBox.remove(name);
                }else{
                    presentBox.get(name).setWeight(presentBox.get(name).getWeight() - w);
                }
                allweight -= w;
                allworth -= presentBox.get(name).getWorth();
            }else{
                System.out.println("невозможно удалить больше, поэтому удалиться имеющееся кол-во");
                allweight -= presentBox.get(name).getWeight();
                allworth -= presentBox.get(name).getWorth();
                presentBox.remove(name);
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
        return presentBox.size();
    }
    /**Перевод полной стоимости(золото) в другую валюту из списка @Currency*/
    public double ExchangeToCurrency(Currency currency){
        Function<Double,Double> form;
        switch (currency){
            case Silver:
                form = d -> d * 84.5;
                return BigDecimal.valueOf(form.apply(allworth))
                        .setScale(2, RoundingMode.HALF_EVEN)
                        .doubleValue();
            case PoundSterling:
                form = d -> d * 1260;
                return BigDecimal.valueOf(form.apply(allworth))
                        .setScale(2, RoundingMode.HALF_EVEN)
                        .doubleValue();
            case Platinum:
                form = d -> d * 1.53;
                return BigDecimal.valueOf(form.apply(allworth))
                        .setScale(2, RoundingMode.HALF_EVEN)
                        .doubleValue();
            default:
                throw new IllegalArgumentException("Неверный тип валюты");
        }

    }
    /**Произвольный перевод полной стоимости(золото) в валюту при помоощи лямбд*/
    public double Ex(Function<Double,Double> form){
        return BigDecimal.valueOf(form.apply(allworth)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }

    /**Вывод всех сладостей, длина имени которых не превышает @maxLengthOfSweetsName*/
    public String namesViaStreamAPI(int maxLengthOfSweetsName){
        String output = "";
        for(String name:presentBox.keySet()
                .stream()
                .filter(str -> str.length()<=maxLengthOfSweetsName)
                .collect(Collectors.toList())){
            output += name + " ";
        }
        return output;
    }
    public int portionQuantityOfSweet(Sweet sweet, double portion){
        double result = 0;
        try{
            result = presentBox.values()
                    .stream()
                    .filter(sweet1 -> sweet.getName() == sweet1.getName())
                    .map(Sweet::getWeight)
                    .map(w->Math.ceil(w/portion))
                    .collect(Collectors.toList()).get(0);
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return (int)result;
    }



}
