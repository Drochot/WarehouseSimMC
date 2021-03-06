package com.nhlstenden.amazonsimulatie.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/*
 * Deze class is een versie van het model van de simulatie. In dit geval is het
 * de 3D wereld die we willen modelleren (magazijn). De zogenaamde domain-logic,
 * de logica die van toepassing is op het domein dat de applicatie modelleerd, staat
 * in het model. Dit betekent dus de logica die het magazijn simuleert.
 */
public class World implements Model {
    /*
     * De wereld bestaat uit objecten, vandaar de naam worldObjects. Dit is een lijst
     * van alle objecten in de 3D wereld. Deze objecten zijn in deze voorbeeldcode alleen
     * nog robots. Er zijn ook nog meer andere objecten die ook in de wereld voor kunnen komen.
     * Deze objecten moeten uiteindelijk ook in de lijst passen (overerving). Daarom is dit
     * een lijst van Object3D onderdelen. Deze kunnen in principe alles zijn. (Robots, vrachrtwagens, etc)
     */
    private List<Object3D> worldObjects;

    /*
     * Dit onderdeel is nodig om veranderingen in het model te kunnen doorgeven aan de controller.
     * Het systeem werkt al as-is, dus dit hoeft niet aangepast te worden.
     */
    PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    /*
     * De wereld maakt een lege lijst voor worldObjects aan. Daarin wordt nu één robot gestopt.
     * Deze methode moet uitgebreid worden zodat alle objecten van de 3D wereld hier worden gemaakt.
     */
    public World() {
        this.worldObjects = new ArrayList<>();
        //this.worldObjects.add(new Object(id,xpos,ypos,zpos)
        this.worldObjects.add(new DeliveryCart(1, 7.5, 2, 11.5));
        this.worldObjects.add(new SortingCart(2, 3.5, 2, -4.5));
        this.worldObjects.add(new ShippingCart(3, -3.5, 2, -3.5));
        this.worldObjects.add(new FetchingCart(4, -3.5, 2, -4.5));


        this.worldObjects.add(new Spotlight(1, 5.5, 9, -7.5));
        this.worldObjects.add(new Spotlight(2, -5.5, 9, -7.5));
        this.worldObjects.add(new Spotlight(3, 5.5, 9, -13.5));
        this.worldObjects.add(new Spotlight(4, -5.5, 9, -13.5));

        this.worldObjects.add(new Diamond(1, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(2, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(3, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(4, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(5, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(6, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(7, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(8, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(9, 8.5, 3, 11.5));
        this.worldObjects.add(new Diamond(10, 8.5, 3, 11.5));

        this.worldObjects.add(new Emerald(11, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(12, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(13, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(14, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(15, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(16, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(17, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(18, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(19, 8.5, 3, 11.5));
        this.worldObjects.add(new Emerald(20, 8.5, 3, 11.5));

        this.worldObjects.add(new Gold(21, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(22, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(23, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(24, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(25, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(26, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(27, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(28, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(29, 8.5, 3, 11.5));
        this.worldObjects.add(new Gold(30, 8.5, 3, 11.5));


    }

    /*
     * Deze methode wordt gebruikt om de wereld te updaten. Wanneer deze methode wordt aangeroepen,
     * wordt op elk object in de wereld de methode update aangeroepen. Wanneer deze true teruggeeft
     * betekent dit dat het onderdeel daadwerkelijk geupdate is (er is iets veranderd, zoals een positie).
     * Als dit zo is moet dit worden geupdate, en wordt er via het pcs systeem een notificatie gestuurd
     * naar de controller die luisterd. Wanneer de updatemethode van het onderdeel false teruggeeft,
     * is het onderdeel niet veranderd en hoeft er dus ook geen signaal naar de controller verstuurd
     * te worden.
     */
    @Override
    public void update() {
        for (Object3D object : this.worldObjects) {
            if(object instanceof Updatable) {
                if (((Updatable)object).update()) {
                    pcs.firePropertyChange(Model.UPDATE_COMMAND, null, new ProxyObject3D(object));
                }
            }
        }
    }

    /*
     * Standaardfunctionaliteit. Hoeft niet gewijzigd te worden.
     */
    @Override
    public void addObserver(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    /*
     * Deze methode geeft een lijst terug van alle objecten in de wereld. De lijst is echter wel
     * van ProxyObject3D objecten, voor de veiligheid. Zo kan de informatie wel worden gedeeld, maar
     * kan er niks aangepast worden.
     */
    @Override
    public List<Object3D> getWorldObjectsAsList() {
        ArrayList<Object3D> returnList = new ArrayList<>();

        for(Object3D object : this.worldObjects) {
            returnList.add(new ProxyObject3D(object));
        }

        return returnList;
    }
}