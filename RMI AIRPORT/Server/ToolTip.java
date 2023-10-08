package Server;

import java.util.ArrayList;


class ToolTip{
static public double Distance(Avion a, Station b) {
       int x2 = b.coordX;
        int y2 = b.coordY;
        int x1 = a.flight.positionX;
        int y1 = a.flight.positionY;
        return Math.sqrt (Math.pow((y2 - y1),2) + Math.pow((x2 - x1),2));
    }

static public Station FindNearest(Avion a)
    {       
            float threshold = 55 ;
            ArrayList<Station> stations = TourDeControle.AnnuaireStations;
            Station nearest = null;
            for (int i=0; i<=stations.size(); i++){
                if (Distance(a, stations.get(i))<= threshold)
                {
                    nearest = stations.get(i);
                }
            }
            if ((nearest == null) && (a.reservoir < 100)) {
                a.flight.changeState(2);
            }
            return nearest;
    }

static public void CrashDetect()
    {
        ArrayList<Avion> avions = TourDeControle.AnnuaireAvions;

        for (int i=0; i<=avions.size(); i++){
            if(avions.get(i).etat == State.Active)
            {
                Avion Plane = avions.get(i);

            for(int j = 0; j<=avions.size(); j++)
                if (i!=j){

                    if(avions.get(j).etat == State.Active)
                    {
                        Avion Plane2 = avions.get(j);
                    
                        if (Plane.flight.positionX == Plane2.flight.positionX)
                            {
                                if (Plane.flight.positionY == Plane2.flight.positionY)
                                    {
                                        Plane.flight.changeState(2);
                                        Plane2.flight.changeState(2);
                                    }
                            }
                    }
        }
    }
    }
}

static public void AvoidCrash()
    {
        ArrayList<Avion> avions = TourDeControle.AnnuaireAvions;
        int Threshold = 0;
        for (int i=0; i<=avions.size(); i++){
            if(avions.get(i).etat == State.Active){
            Avion Plane = avions.get(i);

            for(int j = 0; j<=avions.size(); j++)
            if (i!=j){

                if(avions.get(j).etat == State.Active)
                {
                Avion Plane2 = avions.get(j);

                if (Math.abs(Plane.flight.positionX - Plane2.flight.positionX) < Threshold )
                    {
                        if (Math.abs(Plane.flight.positionY - Plane2.flight.positionY) < Threshold)
                            {
                                DeviatePlane(Plane);
                            }
                    }
                }
            }
        }

        }
    }
static public void DeviatePlane(Avion a)
    {

    }
}