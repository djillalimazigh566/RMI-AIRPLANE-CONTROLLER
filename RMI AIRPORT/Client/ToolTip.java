package Client;



import java.rmi.RemoteException;

import Common.AvionInterface;


class ToolTip{



/*
static public double Distance(Avion a, Station b) {
        x2 = b.coordX;
        y2 = b.coordY;
        x1 = a.flight.positionX;
        y1 = a.flight.positionXY;
        return Math.sqrt(sqr(y2 - y1) + sqr(x2 - x1));
    }
/*
static public Station FindNearest(Avion a)
    {       
            float threshold = 55,5 //Nik bark
            ArrayList<Station> stations = TourDeControle.AnnuaireStations;

            for (int i=0; i<=stations.size(); i++){
                if Distance(a, stations.get(i)<= threshold)
                {
                    return stations.get(i);
                }
            }
            return null;

    }

static public void CrashDetect()
    {
        ArrayList<Avion> avions = TourDeControle.AnnuaireAvions;

        for (int i=0; i<=avions.size(); i++){
            if(avions.get(i).state == state.Active)
            {
                Avion Plane = avions.get(i);
            }
            
            for(int j = 0; j<=avions.size(); j++)
            if(avions.get(i).state == state.Active)
            {
                Avion Plane2 = avions.get(j);
            }
                if (Plane.flight.positionX == Plane2.flight.positionX)
                    {
                        if (Plane.flight.positionY == Plane2.flight.positionY)
                            {
                                HandleCrash(Plane, Plane2);
                            }
                    }
            }
    }

static public void HandleCrash(Avion a, Avion b)
    {
        a.changeState("Broken");
        b.changeState("Broken");
    }

static public void AvoidCrash()
    {
        ArrayList<Avion> avions = TourDeControle.AnnuaireAvions;
        int Threshold = 0;

        for (int i=0; i<=avions.size(); i++){
            Avion Plane = avions.get(i);

            for(int j = 0; j<=avions.size(); j++)
                Avion Plane2 = avions.get(j);

                if (Math.abs(Plane.flight.positionX - Plane2.flight.positionX) < threshold )
                    {
                        if (Math.abs(Plane.flight.positionY - Plane2.flight.positionY) < threshold)
                            {
                                DeviatePlane(Plane);
                            }
                    }
            }
    }

static public void DeviatePlane(Avion a)
    {

    }*/
}