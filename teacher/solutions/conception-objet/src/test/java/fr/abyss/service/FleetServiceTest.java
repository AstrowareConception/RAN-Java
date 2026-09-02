package fr.abyss.service;

import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.DroneStatus;
import fr.abyss.domain.model.Mission;
import fr.abyss.domain.model.MissionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires sur les opérations de sélection et de filtrage de FleetService.
 */
class FleetServiceTest {

    private FleetService fleetService;

    @BeforeEach
    void setUp() {
        MissionAssignmentService assignmentService = new MissionAssignmentService();
        fleetService = new FleetService(assignmentService);
    }

    @Test
    @DisplayName("Sélection automatique : exclut les drones incompatibles")
    void shouldFindOnlyCompatibleDrones() {
        // Mission 4 : Explorer Hécate (1500m, 35% énergie, OBSERVATION)
        // Nautilus : max 500m -> NON
        // Argos : max 1200m -> NON
        // Hephaistos : max 800m, REPAIR -> NON
        // Titan : 2000m mais RECOVERY -> NON (manque OBSERVATION)
        // Proteus : 1000m -> NON
        // -> Aucun drone de la flotte standard ne peut atteindre 1500m en observation.
        Mission hecate = fleetService.findMissionById(4).orElseThrow();
        List<Drone> compatible = fleetService.findCompatibleDrones(hecate);
        assertTrue(compatible.isEmpty());
    }

    @Test
    @DisplayName("Sélection automatique : identifie les drones compatibles pour Aurora")
    void shouldFindCompatibleDronesForAurora() {
        // Mission 1 : Aurora (350m, 20% énergie, OBSERVATION)
        // Nautilus (500m, 80%, Obs) -> OUI
        // Argos (1200m, 65%, Obs) -> OUI
        // Proteus (1000m, 100%, Obs) -> OUI
        Mission aurora = fleetService.findMissionById(1).orElseThrow();
        List<Drone> compatible = fleetService.findCompatibleDrones(aurora);

        assertEquals(3, compatible.size());
        assertTrue(compatible.stream().anyMatch(d -> d.getName().equals("Nautilus")));
        assertTrue(compatible.stream().anyMatch(d -> d.getName().equals("Argos")));
        assertTrue(compatible.stream().anyMatch(d -> d.getName().equals("Proteus")));
    }

    @Test
    @DisplayName("Meilleur drone : sélectionne le drone avec le niveau maximal de batterie")
    void shouldSelectBestDroneWithHighestBattery() {
        Mission aurora = fleetService.findMissionById(1).orElseThrow();
        Optional<Drone> best = fleetService.findBestDroneFor(aurora);

        assertTrue(best.isPresent());
        assertEquals("Proteus", best.get().getName(), "Proteus a 100% de batterie contre 80% pour Nautilus et 65% pour Argos");
    }

    @Test
    @DisplayName("Exécution : refuse un drone et une mission affectés à des partenaires différents sans modifier leur état")
    void shouldRejectExecutionForMismatchedDroneAndMission() {
        assertTrue(fleetService.assignMission(1, 1));
        assertTrue(fleetService.assignMission(2, 6));

        assertFalse(fleetService.executeMission(1, 6));

        assertEquals(DroneStatus.ON_MISSION, fleetService.findDroneById(1).orElseThrow().getStatus());
        assertEquals(MissionStatus.ASSIGNED, fleetService.findMissionById(6).orElseThrow().getStatus());
    }
}
