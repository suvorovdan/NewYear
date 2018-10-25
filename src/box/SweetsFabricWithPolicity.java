package box;

import Sweets.*;

import java.util.function.Predicate;

public class SweetsFabricWithPolicity implements ISweetTypeFabric {
    private Predicate<Sweet> notHeavy = (sweet) -> sweet.getWeight() < 0.2;

    public void setPolicity2(Predicate<Sweet> predicate) {
        this.notHeavy = predicate;
    }

    double weight;
    @Override
    public Sweet create(NameOfSweets nameOfSweet) {
        switch (nameOfSweet){
            case bombi:
                weight = 1.2;
                return notHeavy.test(new KaramelnieBombi(weight)) ? new KaramelnieBombi(weight) : null;
            case konfeti:
                weight = 0.1;
                return notHeavy.test(new IkotnieKonfeti(weight)) ? new IkotnieKonfeti(weight) : null;
            case jaba:
                weight = 0.22;
                return notHeavy.test(new MyatnayaJaba(weight)) ? new MyatnayaJaba(weight) : null;
            case bobi:
                weight = 0.15;
                return notHeavy.test(new BobiBertiBotts(weight)) ? new BobiBertiBotts(weight) : null;
            default:
                throw new IllegalArgumentException("Введён неверный тип сладости");
        }
    }
    public SweetsFabricWithPolicity(Predicate<Sweet> predicate){
        setPolicity2(predicate);
    }
    public SweetsFabricWithPolicity(){
        setPolicity2((sweet) -> sweet.getWeight() < 0.2);
    }
}
