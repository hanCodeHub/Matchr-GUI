package Matchr_App;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

// container class for serializing Racquet objects
@XmlRootElement(name = "racquets")
@XmlAccessorType(XmlAccessType.FIELD)
public class Racquets {

    // each Racquet object in XML
    @XmlElement(name="racquet")
    private List<Racquet> racquets;

    // default constructor used for reading objects
    public Racquets() {}

    // custom constructor used for saving objects
    public Racquets(List<Racquet> racquets) {
        this.racquets = racquets;
    }

    // getter/setter

    public List<Racquet> getRacquets() {
        return racquets;
    }
    public void setRacquets(List<Racquet> racquets) {
        this.racquets = racquets;
    }
}
