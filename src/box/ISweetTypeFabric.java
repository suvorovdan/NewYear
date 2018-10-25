package box;

import Sweets.NameOfSweets;
import Sweets.Sweet;

public interface ISweetTypeFabric<sweet extends Sweet> {
    sweet create(NameOfSweets nameOfSweets);
}
