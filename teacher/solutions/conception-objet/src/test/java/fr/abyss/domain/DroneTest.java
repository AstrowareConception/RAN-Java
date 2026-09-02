package fr.abyss.domain;

import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.DroneStatus;
import fr.abyss.domain.model.equipments.CargoModule;
import fr.abyss.domain.model.equipments.Camera;
import fr.abyss.domain.model.equipments.Sonar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires portant sur la classe Drone, ses invariants et ses équipements modulaires.
 */
class DroneTest {

    @Test
    @DisplayName("Invariant : rejet à la construction si la batterie dépasse 100% ou est négative")
    void shouldRejectInvalidBatteryInitialization() {
        assertThrows(IllegalArgumentException.class, () ->
                new Drone(1, "Faulty", 150, 500, Set.of(), 10.0));
        assertThrows(IllegalArgumentException.class, () ->
                new Drone(1, "Faulty", -10, 500, Set.of(), 10.0));
    }

    @Test
    @DisplayName("R6 : L'exécution d'une mission décrémente la batterie et bascule en MAINTENANCE si < 15%")
    void shouldSwitchToMaintenanceWhenBatteryDropsBelowReserve() {
        Drone titan = new Drone(4, "Titan", 30, 2000, Set.of(Capability.RECOVERY), 50.0);
        titan.startMission(1);

        titan.executeMission(1, 20); // Batterie restante = 10% (< 15%)

        assertEquals(10, titan.getBattery());
        assertEquals(DroneStatus.MAINTENANCE, titan.getStatus());
    }

    @Test
    @DisplayName("R7 : La recharge plafonne à 100% et remet en service un drone en maintenance dès 15%")
    void shouldRechargeAndRestoreToAvailable() {
        Drone drone = new Drone(1, "Nautilus", 10, 500, Set.of(Capability.OBSERVATION), 5.0);
        drone.startMission(1);
        drone.executeMission(1, 0);

        drone.recharge(50); // Devient 60% (>= 15%)
        assertEquals(60, drone.getBattery());
        assertEquals(DroneStatus.AVAILABLE, drone.getStatus());

        drone.recharge(100); // Plafonne à 100%
        assertEquals(100, drone.getBattery());
    }

    @Test
    @DisplayName("R8 : L'ajout d'équipement confère une nouvelle capacité active")
    void shouldAcquireCapabilityViaEquipment() {
        Drone titan = new Drone(4, "Titan", 90, 2000, Set.of(Capability.RECOVERY), 50.0);
        assertFalse(titan.hasCapability(Capability.OBSERVATION));

        titan.installEquipment(new Camera());
        assertTrue(titan.hasCapability(Capability.OBSERVATION));
        assertTrue(titan.hasCapability(Capability.RECOVERY));
    }

    @Test
    @DisplayName("R8 : Les équipements avec surcoût augmentent la consommation énergétique")
    void shouldAccountForEquipmentEnergyOverhead() {
        Drone drone = new Drone(1, "Nautilus", 45, 500, Set.of(), 5.0);
        drone.installEquipment(new Sonar()); // +5% surcoût

        // Mission coût 25% + Sonar 5% + Réserve 15% = 45% -> OK
        assertTrue(drone.hasEnoughBatteryFor(25));

        // Mission coût 26% + Sonar 5% + Réserve 15% = 46% > 45% -> ÉCHEC
        assertFalse(drone.hasEnoughBatteryFor(26));
    }

    @Test
    @DisplayName("R8 : Un module cargo augmente réellement la capacité d'emport")
    void shouldIncreasePayloadCapacityWithCargoModule() {
        Drone drone = new Drone(1, "Nautilus", 80, 500, Set.of(), 5.0);

        drone.installEquipment(new CargoModule());

        assertEquals(25.0, drone.getTotalPayloadCapacityKg());
        assertTrue(drone.canCarry(20.0));
    }
}
