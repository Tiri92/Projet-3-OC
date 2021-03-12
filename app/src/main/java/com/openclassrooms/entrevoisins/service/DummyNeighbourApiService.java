package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * We recover the id of the selected neighbour "neighbour.getId()" and we compare his Id with Id of the list of neighbour
     * "neighbourId" for send the correct neighbour if both id are the same
     */
    @Override
    public Neighbour getNeighourById(long neighbourId) {
        List<Neighbour> neighbourList = DI.getNeighbourApiService().getNeighbours();
        for (Neighbour neighbour : neighbourList) {
            if (neighbour.getId() == neighbourId) {
                return neighbour;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void addNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }
}
