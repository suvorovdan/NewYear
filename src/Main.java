import Sweets.*;
import box.ISweetFabric;
import box.*;

public class Main {

    public static void main(String[] args) {
        Present present = new Present();
//        Пункт 1
        /**Создание новой сладости через функциональный интерфейс*/
        ISweetFabric<KaramelnieBombi> karamelnieBombiFabric = new ISweetFabric<KaramelnieBombi>() {
            @Override
            public KaramelnieBombi create(double weight) {
                return new KaramelnieBombi(weight);
            }
        };
        KaramelnieBombi karamelnieBombi = karamelnieBombiFabric.create(2.3);

        ISweetFabric<IkotnieKonfeti> ikotnieKonfetiISweetFabric = new ISweetFabric<IkotnieKonfeti>() {
            @Override
            public IkotnieKonfeti create(double weight) {
                return new IkotnieKonfeti(weight);
            }
        };
        IkotnieKonfeti ikotnieKonfeti = ikotnieKonfetiISweetFabric.create(0.15);
        present.addSweet(karamelnieBombi);
        present.addSweet(ikotnieKonfeti);
        System.out.println(present.info());

        /**Создание новой сладости через функциональный интерфейс и лямбда выражения*/
        ISweetFabric<MyatnayaJaba> mjFactory = MyatnayaJaba::new;
        MyatnayaJaba myatnayaJaba = mjFactory.create(1.2);
        ISweetFabric<BobiBertiBotts> bobiBertiBottsSweetFabric = BobiBertiBotts::new;
        BobiBertiBotts bobiBertiBotts = bobiBertiBottsSweetFabric.create(0.3);
        present.addSweet(myatnayaJaba);
        present.addSweet(bobiBertiBotts);
        System.out.println(present.info());

        /**Создание новых сладостуй через класс фабрики со стандартными весами для каждой сладости*/
        for(NameOfSweets sweets: NameOfSweets.values()){
            present.addSweet(new SweetsFabric().create(sweets));
        }
        System.out.println(present.info());

//        Пункт 2
        /**Попытка добавить сладость общая стоимость которой (вес*цена) больше Policity(30)*/
        present.addSweet(karamelnieBombiFabric.create(4));
        System.out.println(present.info());
        present.setPolicity(sweet -> sweet.getWorth() < 50);
        System.out.println("поменяли политику \n");
        present.addSweet(karamelnieBombiFabric.create(4));
        System.out.println(present.info());
        /**Добавление сладостей через новый класс @SweetsFabricWithPolicity с политикой веса не больше чем 200 грамм
         *  правда пришлось сделать специальную функцию добавления сладостей из-за особенности реализации(возможно
         *  появление значения null), новая политика задаётся при создании нового экземпляра класса через лямбда
         *  выражение*/
        for (NameOfSweets sweets : NameOfSweets.values()){
            present.addSweetsForFabricWithPolicity(new SweetsFabricWithPolicity().create(sweets));
        }
        System.out.println(present.info());
        for (NameOfSweets sweets : NameOfSweets.values()){
            present.addSweetsForFabricWithPolicity(new SweetsFabricWithPolicity((sweet) -> sweet.getWeight() > 0.2)
                    .create(sweets));
        }
        System.out.println(present.info());

//        Пункт 3
        for (Currency currency:Currency.values()){
            System.out.println(String.format("Стоимость подарка в %s составляет %.2d \n",
                    currency, present.ExchangeToCurrency(currency)));
        }
        System.out.println(present.Ex(gold->gold*1000));

    }
}
