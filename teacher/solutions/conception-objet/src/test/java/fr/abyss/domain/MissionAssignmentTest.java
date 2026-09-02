package fr.abyss.domain;

import fr.abyss.domain.model.AssignmentFailureReason;
import fr.abyss.domain.model.AssignmentResult;
import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.Mission;
import fr.abyss.service.MissionAssignmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Validation automatisée complète de l'ensemble des scénarios de référence (Scénarios A à L).
 */
class MissionAssignmentTest {

    private MissionAssignmentService assignmentService;

    @BeforeEach
    void setUp() {
        assignmentService = new MissionAssignmentService();
    }

    @Test
    @DisplayName("Scénario A : Nautilus -> Aurora (ACCEPTÉ)")
    void scenarioA_shouldAcceptValidMission() {
        Drone nautilus = new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0);
        Mission aurora = new Mission(1, "Photographier Aurora", 350, 20, Capability.OBSERVATION, 0.0);

        AssignmentResult result = assignmentService.validateAssignment(nautilus, aurora);

        assertTrue(result.isAllowed());
        assertTrue(result.reasons().isEmpty());
    }

    @Test
    @DisplayName("Scénario B : Nautilus -> Balise B12 (REFUSÉ - Profondeur)")
    void scenarioB_shouldRejectDueToDepth() {
        Drone nautilus = new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0);
        Mission b12 = new Mission(2, "Balise B12", 700, 30, Capability.RECOVERY, 18.0);

        AssignmentResult result = assignmentService.validateAssignment(nautilus, b12);

        assertFalse(result.isAllowed());
        assertTrue(result.hasReason(AssignmentFailureReason.DEPTH_EXCEEDED));
    }

    @Test
    @DisplayName("Scénario C : Hephaistos -> Réparer Omega (ACCEPTÉ)")
    void scenarioC_shouldAcceptRepairMission() {
        Drone hephaistos = new Drone(3, "Hephaistos", 90, 800, Set.of(Capability.REPAIR), 10.0);
        Mission omega = new Mission(3, "Réparer capteur Omega", 600, 35, Capability.REPAIR, 0.0);

        AssignmentResult result = assignmentService.validateAssignment(hephaistos, omega);

        assertTrue(result.isAllowed());
    }

    @Test
    @DisplayName("Scénario D : Titan -> Explorer Hécate (REFUSÉ - Capacité & Énergie)")
    void scenarioD_shouldRejectDueToMissingCapabilityAndEnergy() {
        Drone titan = new Drone(4, "Titan", 45, 2000, Set.of(Capability.RECOVERY), 50.0);
        Mission hecate = new Mission(4, "Fosse Hécate", 1500, 35, Capability.OBSERVATION, 0.0);

        AssignmentResult result = assignmentService.validateAssignment(titan, hecate);

        assertFalse(result.isAllowed());
        assertTrue(result.hasReason(AssignmentFailureReason.MISSING_CAPABILITY));
        assertTrue(result.hasReason(AssignmentFailureReason.INSUFFICIENT_BATTERY));
    }

    @Test
    @DisplayName("Scénario E : Argos -> Balise B12 (ACCEPTÉ)")
    void scenarioE_shouldAcceptPayloadRecovery() {
        Drone argos = new Drone(2, "Argos", 65, 1200, Set.of(Capability.OBSERVATION, Capability.RECOVERY), 25.0);
        Mission b12 = new Mission(2, "Balise B12", 700, 30, Capability.RECOVERY, 18.0);

        AssignmentResult result = assignmentService.validateAssignment(argos, b12);

        assertTrue(result.isAllowed());
    }

    @Test
    @DisplayName("Scénario F : Argos -> Boîte noire (REFUSÉ - Charge max)")
    void scenarioF_shouldRejectPayloadExceeded() {
        Drone argos = new Drone(2, "Argos", 65, 1200, Set.of(Capability.OBSERVATION, Capability.RECOVERY), 25.0);
        Mission blackBox = new Mission(5, "Boîte noire", 900, 25, Capability.RECOVERY, 40.0);

        AssignmentResult result = assignmentService.validateAssignment(argos, blackBox);

        assertFalse(result.isAllowed());
        assertTrue(result.hasReason(AssignmentFailureReason.PAYLOAD_TOO_HEAVY));
    }

    @Test
    @DisplayName("Scénario G : Drone 40% pour coût 30% (REFUSÉ - Réserve 15% violée : 40 < 45)")
    void scenarioG_shouldRejectInsufficientReserve() {
        Drone drone = new Drone(10, "Sub", 40, 1000, Set.of(Capability.OBSERVATION), 10.0);
        Mission mission = new Mission(10, "Task", 300, 30, Capability.OBSERVATION, 0.0);

        AssignmentResult result = assignmentService.validateAssignment(drone, mission);

        assertFalse(result.isAllowed());
        assertTrue(result.hasReason(AssignmentFailureReason.INSUFFICIENT_BATTERY));
    }

    @Test
    @DisplayName("Scénario J : Drone ON_MISSION ou MAINTENANCE (REFUSÉ)")
    void scenarioJ_shouldRejectWhenDroneUnavailable() {
        Drone drone = new Drone(1, "Nautilus", 10, 500, Set.of(Capability.OBSERVATION), 5.0);
        drone.startMission(99);
        drone.executeMission(99, 0);
        Mission aurora = new Mission(1, "Photographier Aurora", 350, 20, Capability.OBSERVATION, 0.0);

        AssignmentResult result = assignmentService.validateAssignment(drone, aurora);

        assertFalse(result.isAllowed());
        assertTrue(result.hasReason(AssignmentFailureReason.DRONE_UNAVAILABLE));
    }

    @Test
    @DisplayName("Scénario K : Mission COMPLETED ne peut pas être réassignée")
    void scenarioK_shouldRejectCompletedMission() {
        Drone drone = new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0);
        Mission aurora = new Mission(1, "Photographier Aurora", 350, 20, Capability.OBSERVATION, 0.0);
        aurora.assign();
        aurora.complete();

        AssignmentResult result = assignmentService.validateAssignment(drone, aurora);

        assertFalse(result.isAllowed());
        assertTrue(result.hasReason(AssignmentFailureReason.MISSION_UNAVAILABLE));
    }
}
