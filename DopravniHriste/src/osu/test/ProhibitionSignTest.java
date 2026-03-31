package osu.test; // nebo jen 'package osu;', podle toho, kde ten soubor přesně máš

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

// Importy tvých tříd, pokud je test v jiné složce než zbytek kódu
import osu.Action;
import osu.Car;
import osu.Direction;
import osu.DirectionSign;
import osu.FineManagement;
import osu.Location;
import osu.ProhibitionSign;
import osu.SpeedLimit;
import osu.Ticket;

public class ProhibitionSignTest {

    // 1. Test: Auto překračuje rychlost tam, kde NENÍ omezující značka (Specifický požadavek ze zadání)
    @Test
    public void testPrekroceniRychlostiBezOmezeni() {
        Location lokace = new Location("Okreska bez značek");
        // Schválně do lokace NEPŘIDÁME značku SpeedLimit, jen třeba směrovou
        lokace.addSign(new DirectionSign(Direction.STRAIGHT));

        // Auto jede rychle (např. 150 km/h)
        Car auto = new Car("1A1", Action.TURNING, 150, Direction.STRAIGHT);
        FineManagement system = new FineManagement();

        List<Ticket> pokuty = system.checkViolations(auto, lokace);

        // Očekáváme, že nedostane žádnou pokutu (seznam je prázdný)
        assertTrue(pokuty.isEmpty(), "Chyba: Auto dostalo pokutu za rychlost na místě, kde není značka!");
    }

    // 2. Test: Auto překračuje rychlost tam, kde JE omezující značka
    @Test
    public void testPrekroceniRychlostiSeZnackou() {
        Location lokace = new Location("Město");
        lokace.addSign(new SpeedLimit(50)); // Značka omezující rychlost na 50

        Car auto = new Car("2B2", Action.TURNING, 80, Direction.STRAIGHT); // Jede 80
        FineManagement system = new FineManagement();

        List<Ticket> pokuty = system.checkViolations(auto, lokace);

        // Očekáváme přesně 1 pokutu
        assertEquals(1, pokuty.size(), "Auto mělo dostat právě jednu pokutu za rychlost.");
    }

    // 3. Test: Auto provádí zakázanou akci (např. parkuje, kde nesmí)
    @Test
    public void testZakazanaAkceParkovani() {
        Location lokace = new Location("Pěší zóna");
        lokace.addSign(new ProhibitionSign(Action.PARKING)); // Zákaz parkování

        Car auto = new Car("3C3", Action.PARKING, 0, Direction.STRAIGHT); // Auto parkuje
        FineManagement system = new FineManagement();

        List<Ticket> pokuty = system.checkViolations(auto, lokace);

        assertEquals(1, pokuty.size(), "Auto mělo dostat pokutu za zakázané parkování.");
    }

    // 4. Test: Vzorný řidič (vše dodržuje)
    @Test
    public void testVzornYRidic() {
        Location lokace = new Location("Běžná křižovatka");
        lokace.addSign(new SpeedLimit(50));
        lokace.addSign(new DirectionSign(Direction.RIGHT)); // Přikázaný směr vpravo

        // Auto jede 40 km/h a odbočuje vpravo (vše legální)
        Car auto = new Car("4D4", Action.TURNING, 40, Direction.RIGHT);
        FineManagement system = new FineManagement();

        List<Ticket> pokuty = system.checkViolations(auto, lokace);

        assertTrue(pokuty.isEmpty(), "Vzorný řidič by neměl dostat žádnou pokutu.");
    }
}