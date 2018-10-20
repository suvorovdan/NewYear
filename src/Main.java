public class Main {

    public static void main(String[] args) {
        Present pr = new Present();
        Sweet m = new MyatnayaJaba(0.1);
        Sweet kb = new KaramelnieBombi(1.2);
        Sweet ik = new IkotnieKonfeti(0.3);

        pr.addSweet(m);
        System.out.println(pr.info());
        System.out.println();

        pr.addSweet(m);
        pr.addSweet(kb);
        pr.addSweet(m);
        pr.addSweet(ik);
        pr.addSweet(kb);
        System.out.println(pr.info());
        System.out.println();

        pr.dellSweet("Карамельные бомбы", 0.5);
        System.out.println(pr.info());
	// write your code here
    }
}
