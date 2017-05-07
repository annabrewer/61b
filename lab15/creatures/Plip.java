package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug
 */
public class Plip extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    /** creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = (int) (255 * e / 2);
        b = 76;
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    public Color color() {
        //g = 63;
        return color(r, g, b);
    }

    /** Do nothing with C, Plips are pacifists. */
    public void attack(Creature c) {
    }

    /** Plips should lose 0.15 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.15;
        g -= 14.4;
    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    public void stay() {
        energy += 0.2;
        g += 19.2;
        if (energy > 2) {
            energy = 2;
        }
        if (g > 255) {
            g = 255;
        }
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    public Plip replicate() {
        energy = (int) (energy / 2);
        return new Plip(energy);
    }

    /** Plips take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        //System.out.print("hi");
        HashMap<Direction, String> names = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            Direction d = Direction.values()[i];
            names.put(d, neighbors.get(d).name());
        }
        if (!names.containsValue("empty")){
            return new Action(Action.ActionType.STAY);
        }
        else if (energy >= 1) {
            int num = (int) (Math.random() * 4);
            Direction d = Direction.values()[num];
            //System.out.print(num);
            while (!names.get(d).equals("empty")) {
                num = (int) (Math.random() * 4);
                d = Direction.values()[num];
            }
            System.out.println(num);
            System.out.println(d);
            System.out.println(names);
            return new Action(Action.ActionType.REPLICATE, d);
        }
        else if (names.containsValue("clorus")) {
            System.out.println("hi");
            int num = (int) (Math.random() * 4);
            Direction d = Direction.values()[num];
            while (!names.get(d).equals("empty")) {
                num = (int) (Math.random() * 4);
                d = Direction.values()[num];
            }
            System.out.println(d);
            System.out.println(names);
            return new Action(Action.ActionType.MOVE, d);
        }
        /*int num = (int) Math.random() * 4;
        Direction d = Direction.values()[num];
        return new Action(Action.ActionType.REPLICATE, d);*/
        return new Action(Action.ActionType.STAY);
    }

}
