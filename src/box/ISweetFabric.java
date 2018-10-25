package box;

import Sweets.Sweet;

public interface ISweetFabric<sweet extends Sweet> {
    sweet create(double weight);
}
