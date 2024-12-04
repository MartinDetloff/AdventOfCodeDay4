import java.io.*;
import java.util.ArrayList;

public class XmasSearch {

    private BufferedReader br;
    private ArrayList<ArrayList<Character>> listOfLists = new ArrayList<ArrayList<Character>>();
    private int xmasCounter = 0;
    public XmasSearch(BufferedReader br){
        this.br = br;
    }

    /**
     * Method to define the right direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] right(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord + 1, yCord};
        return newCord;
    }

    /**
     * Method to define the left direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] left(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord - 1, yCord};
        return newCord;
    }

    /**
     * Method to define the up direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] up(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord, yCord - 1};
        return newCord;
    }

    /**
     * Method to define the upright direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] upRight(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord + 1, yCord - 1};
        return newCord;
    }

    /**
     * Method to define the upleft direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] upLeft(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord - 1, yCord - 1};
        return newCord;
    }

    /**
     * Method to define the down direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] down(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord, yCord + 1};
        return newCord;
    }

    /**
     * Method to define the downRight direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] downRight(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord + 1, yCord + 1};
        return newCord;
    }

    /**
     * Method to define the downLeft direction
     * @param pos The current pos
     * @return the new pos
     */
    private int[] downLeft(int[] pos){
        int xCord = pos[0];
        int yCord = pos[1];

        int[] newCord = {xCord - 1, yCord + 1};
        return newCord;
    }

    /**
     * Method to set up the Arraylist initially
     * @throws IOException new errors
     */
    private void setUpArrayList() throws IOException {
        String st;
        while ((st = br.readLine()) != null){
//            System.out.println(st);
            // define a new array list
            ArrayList<Character> characterArrayList = new ArrayList<>();
            // loop through all the characters in the current line
            for (Character character : st.toCharArray()){
                System.out.println("Adding the " + character);
                characterArrayList.add(character); // add them to the array list
            }
            listOfLists.add(characterArrayList);
        }
    }

    /**
     * Method to check all the directions
     */
    private int checkAllDirections(){
        int rowLength = listOfLists.size();

        for (int row = 0; row < rowLength; row ++){

            int colLength = listOfLists.get(row).size();
            for (int col = 0; col < colLength; col ++){



                if (listOfLists.get(row).get(col) == 'X'){
//                    System.out.println("Here");
                    int[] currentPos = {row, col};
                    // make sure we can
                    if (row < rowLength - 1){

                       if (wordSearch("XMAS", currentPos, "right")){
                           xmasCounter++;

                       }
                    }

                    // make sure we can
                    if (row > 0){
                        // look left
                        if (wordSearch("XMAS", currentPos, "left")){
                            xmasCounter++;
                        }
                    }

                    // make sure we can
                    if (col > 0){
                        // look up
                        if (wordSearch("XMAS", currentPos, "up")){
                            xmasCounter++;
                        }
                    }

                    // make sure we can
                    if (col > 0 && row < rowLength - 1){
                        // look upRight
                        if (wordSearch("XMAS", currentPos, "upright")){
                            xmasCounter++;
                        }
                    }

                    // make sure we can
                    if (col > 0 && row > 0){
                        // look upleft
                        if (wordSearch("XMAS", currentPos, "upleft")){
                            xmasCounter++;
                        }
                    }

                    // make sure we can
                    if (col < colLength - 1){
                        // look down
                        if (wordSearch("XMAS", currentPos, "down")){
                            xmasCounter++;
                        }
                    }

                    // make sure we can
                    if (col < colLength - 1 && row < rowLength - 1){
                        // look downright
                        if (wordSearch("XMAS", currentPos, "downright")){
                            xmasCounter++;
                        }
                    }

                    // make sure we can
                    if (col < colLength - 1 && row > 0){
                        // look downleft
                        if (wordSearch("XMAS", currentPos, "downleft")){
                            xmasCounter++;
                        }
                    }
                }
            }
        }
        return xmasCounter;
    }




    /**
     * Method to search for the word in the given arraylist
     * @param word the word we are looking for
     */
    private boolean wordSearch(String word, int[] currentPos, String direction){
        StringBuilder sb = new StringBuilder();
        int wordIndex = 0;
        int[] currentPosT = currentPos;
        // look right
        while (!sb.toString().equals("XMAS")){
            int row = currentPosT[0];
            int col = currentPosT[1];


            if (wordIndex > 3){
                return false;
            }
            // keep updating the new pos to the direction
            if (listOfLists.get(row).get(col) != word.charAt(wordIndex)){
                return false;
            }
            else {
                sb.append(listOfLists.get(row).get(col));
            }

            // if we can't go anymore in a direction, and our word is not xmas, we stop and return false
            if (!boundsChecker(direction, currentPosT, listOfLists.size(),listOfLists.get(row).size()) && !sb.toString().equals("XMAS")){
                return false;
            }

            currentPosT = newPositionHandler(currentPosT, direction);
            wordIndex++;
        }
//        System.out.println();
        return true;
    }

    /**
     * Method to handle updating the current pos
     * @param currentPos the current postion
     * @param direction the direction
     * @return the new position
     */
    private int[] newPositionHandler(int[] currentPos, String direction){
        int[] newPos = new int[2];
        switch (direction){
            case "right":
                newPos = right(currentPos);
                break;
            case "left":
                newPos = left(currentPos);
                break;
            case "up":
                newPos = up(currentPos);
                break;
            case "down":
                newPos = down(currentPos);
                break;
            case "upright":
                newPos = upRight(currentPos);
                break;
            case "upleft":
                newPos = upLeft(currentPos);
                break;
            case "downleft":
                newPos = downLeft(currentPos);
                break;
            case "downright":
                newPos = downRight(currentPos);
                break;
        }
        return newPos;
    }

    /**
     * Method to check if the bounds are okay
     * @param direction the direction we are going
     * @param currentPos the current position
     * @param rowLength the row length
     * @param colLength the col length
     * @return true if bounds are fine
     */
    private boolean boundsChecker(String direction, int[] currentPos, int rowLength, int colLength){
        boolean boundsAreOkay = false;
        int row = currentPos[0];
        int col = currentPos[1];

        switch (direction){
            case "right":
                if (row < rowLength - 1){
                    boundsAreOkay = true;
                }
                break;

            case "left":
                if (row > 0){
                    boundsAreOkay = true;
                }
                break;

            case "up":
                if (col > 0){
                    boundsAreOkay = true;
                }
            case "upright":
                if (col > 0 && row < rowLength - 1){
                    boundsAreOkay = true;
                }
                break;

            case "upleft":
                if (col > 0 && row > 0){
                    boundsAreOkay = true;
                }
                break;

            case "down":
                if (col < colLength - 1){
                    boundsAreOkay = true;
                }
                break;

            case "downright":
                if (col < colLength - 1 && row < rowLength - 1){
                    boundsAreOkay = true;
                }
                break;

            case "downleft":
                if (col < colLength - 1 && row > 0){
                    boundsAreOkay = true;
                }
                break;
        }
//        System.out.println(boundsAreOkay);
        return boundsAreOkay;
    }










    public static void main(String[] args) throws IOException {


        File file = new File("C:\\Users\\19365\\OneDrive\\Documents\\GitHub\\AdventOfCodeDay4\\src\\input.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        XmasSearch xmasSearch = new XmasSearch(br);

        xmasSearch.setUpArrayList();

        int total = xmasSearch.checkAllDirections();

        System.out.println("This is the total number of occurrences: " + total);

    }
}
