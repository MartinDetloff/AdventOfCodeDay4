import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class X_masSearch {

    private BufferedReader br;
    private ArrayList<ArrayList<Character>> listOfLists = new ArrayList<ArrayList<Character>>();
    private int xmasCounter = 0;
    public X_masSearch(BufferedReader br){
        this.br = br;
    }

    /**
     * Method to define the right direction (move right in the same row)
     * @param pos The current position
     * @return The new position
     */
    private int[] right(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row, col + 1}; // Column increases
    }

    /**
     * Method to define the left direction (move left in the same row)
     * @param pos The current position
     * @return The new position
     */
    private int[] left(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row, col - 1}; // Column decreases
    }

    /**
     * Method to define the up direction (move up in the same column)
     * @param pos The current position
     * @return The new position
     */
    private int[] up(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row - 1, col}; // Row decreases
    }

    /**
     * Method to define the upright direction (move diagonally up and right)
     * @param pos The current position
     * @return The new position
     */
    private int[] upRight(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row - 1, col + 1}; // Row decreases, column increases
    }

    /**
     * Method to define the upleft direction (move diagonally up and left)
     * @param pos The current position
     * @return The new position
     */
    private int[] upLeft(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row - 1, col - 1}; // Row decreases, column decreases
    }

    /**
     * Method to define the down direction (move down in the same column)
     * @param pos The current position
     * @return The new position
     */
    private int[] down(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row + 1, col}; // Row increases
    }

    /**
     * Method to define the downright direction (move diagonally down and right)
     * @param pos The current position
     * @return The new position
     */
    private int[] downRight(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row + 1, col + 1}; // Row increases, column increases
    }

    /**
     * Method to define the downleft direction (move diagonally down and left)
     * @param pos The current position
     * @return The new position
     */
    private int[] downLeft(int[] pos) {
        int row = pos[0];
        int col = pos[1];
        return new int[]{row + 1, col - 1}; // Row increases, column decreases
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



                if (listOfLists.get(row).get(col) == 'M'){
//                    System.out.println("Here");
                    int[] currentPos = {row, col};

                    // make sure we can
                    if (col > 0 && row < rowLength - 1){
                        // look upRight
                        if (wordSearch("MAS", currentPos, "upright")){
                            int[] tempCurrentPos = {currentPos[0] + 1, currentPos[1] - 1};

                            if (boundsChecker("upright", currentPos, rowLength, colLength) &&
                                    boundsChecker("downright", tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker("upleft", tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(currentPos, "downright");
                                int[] posTwo = newPositionHandler(currentPos, "upleft");

                                if (checkIfWeHaveMAS(posOne, posTwo)){
                                    xmasCounter++;
                                }
                            }
                        }
                    }

                    // make sure we can
                    if (col > 0 && row > 0){
                        // look upleft
                        if (wordSearch("MAS", currentPos, "upleft")){
                            int[] tempCurrentPos = {currentPos[0] - 1, currentPos[1] - 1};

                            if (boundsChecker("upleft", currentPos, rowLength, colLength) &&
                                    boundsChecker("downleft", tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker("upright", tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(currentPos, "downleft");
                                int[] posTwo = newPositionHandler(currentPos, "upright");

                                if (checkIfWeHaveMAS(posOne, posTwo)){
                                    xmasCounter++;
                                }
                            }
                        }
                    }

                    // make sure we can
                    if (col < colLength - 1 && row < rowLength - 1){
                        // look downright
                        if (wordSearch("MAS", currentPos, "downright")){
                            int[] tempCurrentPos = {currentPos[0] + 1, currentPos[1] + 1};

                            if (boundsChecker("downright", currentPos, rowLength, colLength) &&
                                    boundsChecker("upright", tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker("downleft", tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(currentPos, "upright");
                                int[] posTwo = newPositionHandler(currentPos, "downleft");

                                if (checkIfWeHaveMAS(posOne, posTwo)){
                                    xmasCounter++;
                                }
                            }
                        }
                    }

                    // make sure we can
                    if (col < colLength - 1 && row > 0){
                        // look downleft
                        if (wordSearch("MAS", currentPos, "downleft")){
                            int[] tempCurrentPos = {currentPos[0] - 1, currentPos[1] + 1};

                            if (boundsChecker("downleft", currentPos, rowLength, colLength) &&
                                    boundsChecker("downright", tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker("upleft", tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(currentPos, "downright");
                                int[] posTwo = newPositionHandler(currentPos, "upleft");

                                if (checkIfWeHaveMAS(posOne, posTwo)){
                                    xmasCounter++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return xmasCounter;
    }

    /**
     * Check if we have an M and an S
     * @param pos1 the pos one
     * @param pos2 the pos two
     */
    private boolean checkIfWeHaveMAS(int[] pos1, int[] pos2){
        int x1 = pos1[0];
        int y1 = pos1[1];
        int x2 = pos2[0];
        int y2 = pos2[1];

        // Check if pos1 and pos2 are within bounds
        if (x1 < 0 || x1 >= listOfLists.size() || y1 < 0 || y1 >= listOfLists.get(x1).size()) {
            return false;
        }
        if (x2 < 0 || x2 >= listOfLists.size() || y2 < 0 || y2 >= listOfLists.get(x2).size()) {
            return false;
        }

        if (listOfLists.get(x1).get(y1) == 'M' && listOfLists.get(x2).get(y2) == 'S'){
            return true;
        }
        if (listOfLists.get(x1).get(y1) == 'S' && listOfLists.get(x2).get(y2) == 'M'){
            return true;
        }
        return false;
    }







    /**
     * Method to search for the word in the given arraylist
     * @param word the word we are looking for
     */
    private boolean wordSearch(String word, int[] currentPos, String direction){
        int wordIndex = 0;
        int[] currentPosT = currentPos;

        while (wordIndex < word.length()) {
            // Perform bounds check before accessing
            if (!boundsChecker(direction, currentPosT, listOfLists.size(), listOfLists.get(0).size())) {
                return false;
            }
            int row = currentPosT[0];
            int col = currentPosT[1];

            // Compare characters
            if (listOfLists.get(row).get(col) != word.charAt(wordIndex)) {
                return false;
            }

            // Move to next position
            currentPosT = newPositionHandler(currentPosT, direction);
            wordIndex++;
        }

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

        int row = currentPos[0];
        int col = currentPos[1];

        // Check bounds based on the direction
        switch (direction) {
            case "right":
                return col + 1 < colLength;
            case "left":
                return col - 1 >= 0;
            case "up":
                return row - 1 >= 0;
            case "down":
                return row + 1 < rowLength;
            case "upright":
                return row - 1 >= 0 && col + 1 < colLength;
            case "upleft":
                return row - 1 >= 0 && col - 1 >= 0;
            case "downright":
                return row + 1 < rowLength && col + 1 < colLength;
            case "downleft":
                return row + 1 < rowLength && col - 1 >= 0;
            default:
                return false;
        }
    }





    public static void main(String[] args) throws IOException {


        File file = new File("C:\\Users\\19365\\OneDrive\\Documents\\GitHub\\AdventOfCodeDay4\\src\\input.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        X_masSearch xmasSearch = new X_masSearch(br);

        xmasSearch.setUpArrayList();

        int total = xmasSearch.checkAllDirections();

        System.out.println("This is the total number of occurrences: " + total);

    }
}
