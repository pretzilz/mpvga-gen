package PolygonGenerator;
import java.util.*;
public class Polygon {
    public ArrayList<Vertex> Vertices;  //this needs to be sorted by x coordinate
    public int numVertices;

    public static Vertex tmp;
    public Polygon(int numVertices) {
        /*this.numVertices = numVertices;
        this.Vertices = new ArrayList<Vertex>();
        for (int i = 0; i < numVertices; i++) {
            this.Vertices.add(new Vertex(true));
        }
        
        Collections.sort(this.Vertices, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return v1.compareTo(v2);    //compares based on x coordinate
            }
        });*/

        //testing things
        this.Vertices = new ArrayList<Vertex>();
        //thing found on page 282 of generate 1
        //vert 1
        this.Vertices.add(new Vertex(1, 10));
        //vert 2
        this.Vertices.add(new Vertex(3, 6));
        //vert 3
        this.Vertices.add(new Vertex(4, 1));
        //vert 4
        this.Vertices.add(new Vertex(6, 9));
        //vert 5
        this.Vertices.add(new Vertex(9, 11));
        tmp = new Vertex(false);
        for (int vertexIndex = 1; vertexIndex < this.Vertices.size(); vertexIndex++) {  
            //System.out.println(tmp.getSibling().toString());
            Make_top(vertexIndex - 1, vertexIndex);
            Vertices.get(vertexIndex).setUpperChild(tmp.getSibling());
            //tmp = new Vertex(false);
        }
        
        //should return coordinates for 1, (1, 10)
        //all siblings and upper children are currently null
        //System.out.println(Vertices.get(4).getUpperChild().toString());
    }

    public Vertex getVertex(int index) {
        return this.Vertices.get(index);
    }


    //VISIBLITY SET HELPERS ==================================
    //assuming this starts with 2, as 1 was already computed
    //will be called with i-1, i, and temp

    public void Make_top(int j, int k) {
        while (Vertices.get(j).getUpperChild() != null && isAbove(Vertices.get(k), Vertices.get(j).getUpperChild(), Vertices.get(j))) {
            //System.out.println("J: " + j + "K: " + k);
            Make_top(Vertices.indexOf(Vertices.get(j).getUpperChild()), k);
            Vertices.get(j).setUpperChild(Vertices.get(j).getUpperChild().getSibling());
        }
        
        tmp.setSibling(Vertices.get(j));    //if tmp is reset every iteration, setting the sibling for the last iteration doesn't matter. what needs to happen here?
        tmp = Vertices.get(j);
        //System.out.println(tmp.toString());
    }
    
    /*
    public Make_Vb(int i) {
        //t = tmp;
        //Make_bot(i - 1, i, )
        //this.Vertices.get(i).setLowerChild(tmp.getSibling());
    }
    */

    //This determines whether or not a vertex k is above the line (j1, j2)
    public boolean isAbove(Vertex k, Vertex j1, Vertex j2) {
        double slope = ((j2.getY() - j1.getY())/(j2.getX() - j1.getX()));   //get slope
        double yInt = -((slope * j1.getX()) - j1.getY());   //find y intercept
        double yCoordOfKOnLine = (slope * k.getX()) + yInt;    //get the y coordinate of k on line
        double yDiff = yCoordOfKOnLine - k.getY(); //get difference between actual y coordinate of k and the one on the line
        //if it's greater than 0, it's below the line. if it's equal, then it's on the line.
        return yDiff < 0; 

    }

    public boolean isBelow(Vertex k, Vertex j1, Vertex j2) {
        double slope = ((j2.getY() - j1.getY())/(j2.getX() - j1.getX()));   //get slope
        double yInt = -((slope * j1.getX()) - j1.getY());   //find y intercept
        double yCoordOfKOnLine = (slope * k.getX()) + yInt;    //get the y coordinate of k on line
        double yDiff = yCoordOfKOnLine - k.getY(); //get difference between actual y coordinate of k and the one on the line
        //if it's less than 0, it's above the line. if it's equal, then it's on the line.
        return yDiff > 0; 

    }



}