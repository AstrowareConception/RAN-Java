package fr.abyss.service;

import fr.abyss.domain.model.Capability;
import fr.abyss.domain.model.Drone;
import fr.abyss.domain.model.Mission;
import fr.abyss.domain.model.MissionPriority;
import fr.abyss.domain.model.PlanAssignment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests unitaires sur l'heuristique de planification multi-missions.
 */
class MissionPlannerServiceTest {

    private MissionPlannerService plannerService;

    @BeforeEach
    void setUp() {
        MissionAssignmentService assignmentService = new MissionAssignmentService();
        plannerService = new MissionPlannerService(assignmentService);
    }

    @Test
    @DisplayName("Planification : traite en priorité les missions critiques et alloue des drones sans conflit")
    void shouldPrioritizeCriticalMissionsAndAvoidDroneConflicts() {
        Drone hephaistos = new Drone(3, "Hephaistos", 90, 800, Set.of(Capability.REPAIR), 10.0);
        Drone nautilus = new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0);
        List<Drone> fleet = List.of(hephaistos, nautilus);

        Mission normalMission = new Mission(1, "Obs simple", 300, 20, Capability.OBSERVATION, 0.0, MissionPriority.NORMAL);
        Mission criticalMission = new Mission(3, "Réparation urgente", 600, 35, Capability.REPAIR, 0.0, MissionPriority.CRITICAL);
        List<Mission> missions = List.of(normalMission, criticalMission);

        List<PlanAssignment> plan = plannerService.planMissions(fleet, missions);

        assertEquals(2, plan.size());

        // La première mission planifiée doit être la critique
        assertEquals(criticalMission.getId(), plan.get(0).mission().getId());
        assertTrue(plan.get(0).isSuccessful());
        assertEquals("Hephaistos", plan.get(0).assignedDrone().getName());

        // La seconde doit être la normale
        assertEquals(normalMission.getId(), plan.get(1).mission().getId());
        assertTrue(plan.get(1).isSuccessful());
        assertEquals("Nautilus", plan.get(1).assignedDrone().getName());
    }

    @Test
    @DisplayName("Planification : signale les missions non affectées si aucun drone compatible n'est libre")
    void shouldReportUnassignedMissionsWhenNoDroneAvailable() {
        Drone nautilus = new Drone(1, "Nautilus", 80, 500, Set.of(Capability.OBSERVATION), 5.0);
        List<Drone> fleet = List.of(nautilus);

        Mission obs1 = new Mission(1, "Obs 1", 300, 20, Capability.OBSERVATION, 0.0, MissionPriority.HIGH);
        Mission obs2 = new Mission(2, "Obs 2", 350, 20, Capability.OBSERVATION, 0.0, MissionPriority.NORMAL);

        List<PlanAssignment> plan = plannerService.planMissions(fleet, List.of(obs1, obs2));

        assertEquals(2, plan.size());
        assertTrue(plan.get(0).isSuccessful(), "La mission prioritaire 1 est affectée");
        assertEquals("Nautilus", plan.get(0).assignedDrone().getName());

        assertFalse(plan.get(1).isSuccessful(), "La mission 2 ne peut être affectée (drone déjà alloué)");
    }
}
