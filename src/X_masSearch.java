import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class X_masSearch {

    private BufferedReader br;
    private ArrayList<ArrayList<Character>> listOfLists = new ArrayList<ArrayList<Character>>();
    private int xmasCounter = 0;
    private ArrayList<ArrayList<Integer>> invalidPos = new ArrayList<>();
    public X_masSearch(BufferedReader br){
        this.br = br;
    }

    public enum Direction {
        RIGHT, LEFT, UP, DOWN, UPRIGHT, UPLEFT, DOWNRIGHT, DOWNLEFT;
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
//        System.out.println("This is the row: " + row + "This is the col: " + col);
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
//            System.out.println("NEW LINE");
            // loop through all the characters in the current line
            for (Character character : st.toCharArray()){
//                System.out.println("Adding the " + character);
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

                int[] currentPos = {row, col};

                if (listOfLists.get(row).get(col) == 'M'){
//                    System.out.println("Here");


                    // make sure we can
                    if (boundsChecker(Direction.UPRIGHT, currentPos, rowLength, colLength)){
                        // look upRight
                        if (wordSearch("MAS", currentPos, Direction.UPRIGHT)){
                            int[] tempCurrentPos = {currentPos[0] - 1 , currentPos[1] + 1};

                            if (boundsChecker(Direction.DOWNRIGHT, tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker(Direction.UPLEFT, tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(tempCurrentPos, Direction.DOWNRIGHT);
                                int[] posTwo = newPositionHandler(tempCurrentPos, Direction.UPLEFT);

                                if (checkIfWeHaveMAS(posOne, posTwo)){
                                    xmasCounter++;
                                }
                            }
                        }
                    }

                    // make sure we can
                    if (boundsChecker(Direction.UPLEFT, currentPos, rowLength, colLength)){
                        // look upleft
                        if (wordSearch("MAS", currentPos, Direction.UPLEFT)){
                            int[] tempCurrentPos = {currentPos[0] - 1, currentPos[1] - 1};

                            if (boundsChecker(Direction.DOWNLEFT, tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker(Direction.UPRIGHT, tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(tempCurrentPos, Direction.DOWNLEFT);
                                int[] posTwo = newPositionHandler(tempCurrentPos, Direction.UPRIGHT);

                                if (checkIfWeHaveMAS(posOne, posTwo)){
                                    xmasCounter++;
                                }
                            }
                        }
                    }

                    // make sure we can
                    if (boundsChecker(Direction.DOWNRIGHT, currentPos, rowLength, colLength)){
                        // look downright
                        if (wordSearch("MAS", currentPos, Direction.DOWNRIGHT)){
                            int[] tempCurrentPos = {currentPos[0] + 1, currentPos[1] + 1};

                            if (boundsChecker(Direction.UPRIGHT, tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker(Direction.DOWNLEFT, tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(tempCurrentPos, Direction.UPRIGHT);
                                int[] posTwo = newPositionHandler(tempCurrentPos, Direction.DOWNLEFT);

                                if (checkIfWeHaveMAS(posOne, posTwo)){
                                    xmasCounter++;
                                }
                            }
                        }
                    }

                    // make sure we can
                    if (boundsChecker(Direction.DOWNLEFT, currentPos, rowLength, colLength)){
                        // look downleft
                        if (wordSearch("MAS", currentPos, Direction.DOWNLEFT)){
                            int[] tempCurrentPos = {currentPos[0] + 1, currentPos[1] - 1};

                            if (
                                    boundsChecker(Direction.DOWNRIGHT, tempCurrentPos, rowLength, colLength) &&
                                    boundsChecker(Direction.UPLEFT, tempCurrentPos, rowLength, colLength)){

                                int[] posOne = newPositionHandler(tempCurrentPos, Direction.DOWNRIGHT);
                                int[] posTwo = newPositionHandler(tempCurrentPos, Direction.UPLEFT);

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



        if (listOfLists.get(x1).get(y1) == 'M' && listOfLists.get(x2).get(y2) == 'S'){

            invalidPos.add(new ArrayList<>(Arrays.asList(x1, y1)));
            return true;
        }
        if (listOfLists.get(x1).get(y1) == 'S' && listOfLists.get(x2).get(y2) == 'M'){

            invalidPos.add(new ArrayList<>(Arrays.asList(x2, y2)));
            return true;
        }
        return false;
    }

    private boolean isValidPos(int[] currentPos){
        for (ArrayList<Integer> invalidPos : invalidPos){
            if (invalidPos.get(0) == currentPos[0] &&
            invalidPos.get(1) == currentPos[1]){
                return false;
            }
        }
        return true;
    }







    /**
     * Method to search for the word in the given arraylist
     * @param word the word we are looking for
     */
    private boolean wordSearch(String word, int[] currentPos, Direction direction){
        int wordIndex = 0;
        int[] currentPosT = currentPos;

        while (wordIndex < word.length()) {
            // Perform bounds check before accessing

            int row = currentPosT[0];
            int col = currentPosT[1];

            // Compare characters
            if (listOfLists.get(row).get(col) != word.charAt(wordIndex)) {
                return false;
            }

            if (wordIndex == word.length() - 1){
                return true;
            }

            if (!boundsChecker(direction, currentPosT, listOfLists.size(), listOfLists.get(0).size())) {
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
    private int[] newPositionHandler(int[] currentPos, Direction direction){
        int[] newPos = new int[2];
        switch (direction){
            case RIGHT:
                newPos = right(currentPos);
                break;
            case LEFT:
                newPos = left(currentPos);
                break;
            case UP:
                newPos = up(currentPos);
                break;
            case DOWN:
                newPos = down(currentPos);
                break;
            case UPRIGHT:
//                System.out.println("This is the current pos");
                newPos = upRight(currentPos);
                break;
            case UPLEFT:
                newPos = upLeft(currentPos);
                break;
            case DOWNLEFT:
                newPos = downLeft(currentPos);
                break;
            case DOWNRIGHT:
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
    private boolean boundsChecker(Direction direction, int[] currentPos, int rowLength, int colLength){

        int row = currentPos[0];
        int col = currentPos[1];

        // Check bounds based on the direction
        switch (direction) {
            case RIGHT:
                return col + 1 < colLength;
            case LEFT:
                return col - 1 >= 0;
            case UP:
                return row - 1 >= 0;
            case DOWN:
                return row + 1 < rowLength;
            case UPRIGHT:
                return row - 1 >= 0 && col + 1 < colLength;
            case UPLEFT:
                return row - 1 >= 0 && col - 1 >= 0;
            case DOWNRIGHT:
                return row + 1 < rowLength && col + 1 < colLength;
            case DOWNLEFT:
                return row + 1 < rowLength && col - 1 >= 0;
            default:
                return false;
        }
    }





    public static void main(String[] args) throws IOException {


        File file = new File("C:\\Users\\Martin\\Documents\\GitHub\\AdventOfCodeDay4\\src\\input.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        X_masSearch xmasSearch = new X_masSearch(br);

        xmasSearch.setUpArrayList();

        int total = xmasSearch.checkAllDirections();

        System.out.println("This is the total number of occurrences: " + total / 2);

    }
}
