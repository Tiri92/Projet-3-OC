package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
}

    /**
     * We test the method getNeighbourById and we see if she send us the correct neighbour with his id
     */
    @Test
    public void getNeighbourByIdWithSuccess() {
        Neighbour expectedNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.get(4);
        Neighbour neighbourTest = service.getNeighourById(expectedNeighbour.getId()); // ou 5
        //assertEquals(expectedNeighbour, neighbourTest);
        assertEquals(expectedNeighbour.getId(), neighbourTest.getId() );
    }

    /**
     * We test the method addNeighbour by comparing the size of the original list with the list in which
     * we have added the neighbour and this added neighbour is built here with the test values
     */
     @Test
    public void addNeighbourWithSuccess() {
        int numberOfNeighbour = DummyNeighbourGenerator.DUMMY_NEIGHBOURS.size();
        Neighbour jNeighbour = new Neighbour(13, "John Baby", "https//", "Boulogne-Billancourt", "0689798871", "J'aime nanana", "www.facebookblablabla" );
        service.addNeighbour(jNeighbour);
        int newNumberOfNeighbour = service.getNeighbours().size();
        assertEquals(numberOfNeighbour +1, newNumberOfNeighbour);
    }
    }