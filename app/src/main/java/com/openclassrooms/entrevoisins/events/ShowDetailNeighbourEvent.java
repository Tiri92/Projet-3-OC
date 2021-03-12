package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class ShowDetailNeighbourEvent {
   public Neighbour neighbour;

    public ShowDetailNeighbourEvent(Neighbour neighbour){
        this.neighbour = neighbour;
    }
}
