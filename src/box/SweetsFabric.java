package box;

import Sweets.*;

public class SweetsFabric implements ISweetTypeFabric {
    private double weight;

    @Override
    public Sweet create(NameOfSweets nameOfSweet) {
        switch (nameOfSweet){
            case bombi:
                weight = 1.2;
                return new KaramelnieBombi(weight);
            case konfeti:
                weight = 0.1;
                return new IkotnieKonfeti(weight);
            case jaba:
                weight = 0.22;
                return new MyatnayaJaba(weight);
            case bobi:
                weight = 0.15;
                return new BobiBertiBotts(weight);
            default:
                throw new IllegalArgumentException("Введён неверный тип сладости");
        }
    }
}

