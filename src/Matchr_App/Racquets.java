package Matchr_App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

// container class for serializing recommendable objects
@XmlRootElement(name = "racquets")
@XmlAccessorType(XmlAccessType.FIELD)
public class Racquets {

    @XmlElement(name="racquet")  // name of each object
    private List<Racquet> racquets;

    public Racquets() {
    }

    public Racquets(List<Racquet> racquets) {
        this.racquets = racquets;
    }

    public List<Racquet> getRacquets() {
        return racquets;
    }

}
