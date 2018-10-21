public class Main {

    public static void main(String[] args) {
        Present pr = new Present();
//        в данной реализации задачи мы можем добавлять определённый вес сладостей, как будто мы в магазине и собираем
//        подарок для ребёнка
        Sweet m = new MyatnayaJaba(0.1);
        Sweet kb = new KaramelnieBombi(1.2);
        Sweet ik = new IkotnieKonfeti(0.3);
        Sweet bob = new BobiBertiBotts(0.5);

//      добавление 100 грамм мятных жаб
        pr.addSweet(m);
        System.out.println(pr.info());

//      добавление 200 грамм мятных жаб, 1 килограмма бобов берти ботт, 300 грамм икотных конфет и
//      2.4 кг карамельных бомб
        pr.addSweet(m);
        pr.addSweet(kb);
        pr.addSweet(m);
        pr.addSweet(ik);
        pr.addSweet(bob);
        pr.addSweet(kb);
        pr.addSweet(bob);
        System.out.println(pr.info());

//      удаление 500 грамм карамельных бомб
        pr.dellSweet("Карамельные бомбы", 0.5);
        System.out.println(pr.info());

//      удаление 500 грамм икотных конфет из подарка, хотя их там 300 грамм
        pr.dellSweet("Икотные конфеты", 0.5);
        System.out.println(pr.info());

//      попытка повторного удаления икотных конфет
        pr.dellSweet("Икотные конфеты", 0.5);
        System.out.println(pr.info());

    }
}
