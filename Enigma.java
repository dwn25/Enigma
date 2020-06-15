import java.util.*;
import java.lang.*;

public class Enigma {
    static char[] top =      {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
    static char[] arrLeft =  {'2','y','z','0','1','a','w','i','p','k','s','n','3','t','e','r','m','u','c','5','v','6','x','7','f','q','o','l','4','8','g','d','9','b','j','h'};
    static char[] arrMid =   {'0','l','x','1','2','8','h','b','3','n','r','o','k','d','t','7','c','6','p','i','v','j','4','a','u','w','m','e','9','5','q','s','z','g','y','f'};
    static char[] arrRight = {'3','5','h','e','f','g','d','q','8','m','2','k','l','j','n','s','u','w','o','v','r','x','z','c','i','9','t','7','b','p','a','0','1','y','6','4'};
    static char[] newLeft = arrLeft;
    static char[] newMid = arrMid;
    static char[] newRight = arrRight;
    //static int[] configuration = {3,1,4,5,9,2,6,8,7,0 };
    static int[] configuration = new int[10];
    static int[] reverseConfiguration = {0,1,2,3,4,5,6,7,8,9};
    static int iterator = 0;
    static int rightTurns = 0;
    static int middRot = 0;
    static int middTurns = 0;
    static int leftTurns = 0;
    static int leftRot = 0;
    static char left = 0;
    static char middle = 0;
    static char right = 0;

    public static void initializeMotors(char left, char middle, char right){
        for(int i = 0; i<newLeft.length;i++){
            if (top[i]==Character.toUpperCase(right)) {
                rightTurns = i;
                //System.out.println(newRight[i]);
                //System.out.println("Right turns: "+rightTurns);
            }
            if (top[i]==Character.toUpperCase(middle)) {
                middTurns = i;
                //System.out.println(newMid[i]);
                //System.out.println("Middle Turns: "+middTurns);
            }
            if (top[i]==Character.toUpperCase(left)) {
                leftTurns = i;
                //System.out.println(newLeft[i]);
                //System.out.println("Left Turns: "+leftTurns);
            }
        }
    }

    public static void reset(){
        newLeft = arrLeft;
        newMid = arrMid;
        newRight = arrRight;
        iterator = 1;
        rightTurns = 0;
        middTurns = 0;
        leftTurns = 0;
    }

    public static String encode(String word) {
        char[] permutedWord = permutator(word);
        char[] encodedWord = new char[10];
        for(int i = 0;i<permutedWord.length;i++){
            encodedWord[i] = match(permutedWord[i]);
        }
        reset();
        initializeMotors(left,middle, right);
        String newEncodedWord = new String(encodedWord);
        //System.out.println(newEncodedWord);
        return newEncodedWord;
    }

    public static char match(char currentChar){
        char encoded = '0';
        iterator += 1;
        rotateRightMotor();
        if(iterator%5 == 0){
            rotateLeftMotor();
            leftRot +=1;
        }
        if(iterator%7 == 0){
            rotateMiddleMotor();
            middRot += 1;
        }
        char current = Character.toUpperCase(currentChar);
        //System.out.println("iterator: " + iterator +" Current Char: "+ current);
        char first = searchRight(current);
        //System.out.println("Right Motor: " + first);
        char second = searchMid(first);
        //System.out.println("Middle Motor: " + second);
        char third = searchLeft(second);
        //System.out.println("Left Motor: " + third);

        char finalChar = searchTop(third);
        //System.out.println("Final: " + finalChar);
        return finalChar;
    }

    public static char searchTop(char current){
        char found = 0;
        int j = 0;
        int index = 0;
        while (j < top.length) {
            if (top[j] == Character.toUpperCase(current)) {
                index = j;
                break;
            } else {
                j += 1;
            }
        }
        //System.out.println("Left Turns: " + leftTurns);
        int x = index + (36 - leftTurns+leftRot+middRot);
        int finalIndex = (x %36);
        //System.out.println("index: " + index);
        found = top[finalIndex];
        //System.out.println("Final Index: " + finalIndex);
        //System.out.println("Found:" + found);
        found = top[finalIndex];
        //System.out.println("Found:" + found);
        return found;
    }

    public static char searchRight(char current){
        char found = 0;
        int j = 0;
        int index = 0;
        while (j < top.length) {
            if (top[j] == Character.toUpperCase(current)) {
                index = j;
                break;
            } else {
                j += 1;
            }
        }
        int x = index + rightTurns;
        int finalIndex = (x %36);
        //System.out.println("index: " + index);
        found = top[finalIndex];
        //System.out.println("Final Index: " + finalIndex);
        //System.out.println("Found:" + found);
        found = newRight[finalIndex];
        return found;
    }

    public static char searchMid(char current){
        char found = 0;
        int j = 0;
        int index = 0;
        while (j < top.length) {
            if (top[j] == Character.toUpperCase(current)) {
                //System.out.println("J: " + top[j]);
                index = j;
                break;
            } else {
                j += 1;
            }
        }
        int finalIndex  = index-iterator;
        if(finalIndex< 0){
            int x = finalIndex + 36;
            finalIndex = x % 36;
            found = top[finalIndex];
            //System.out.println("Found: " + found);
            found = newMid[finalIndex];
            //System.out.println("Index: " + index);
        }
        else{
            found = top[finalIndex];
            //System.out.println("Found: " + found);
            found = newMid[finalIndex];
        }
        return found;
    }

    public static char searchLeft(char current){
        char found = 0;
        int j = 0;
        int index = 0;
        while (j < top.length) {
            if (top[j] == Character.toUpperCase(current)) {
                index = j;
                break;
            } else {
                j += 1;
            }
        }
        int x = index + leftTurns;
        int finalIndex = (x %36);
        //System.out.println("index: " + index);
        found = top[finalIndex];
        //System.out.println("Final Index: " + finalIndex);
        //System.out.println("Found:" + found);
        found = newLeft[index];
        return found;
    }

    public static String decode(String wordToDecode){
        char[] permWord = new char[10];
        for (int i = 0; i<wordToDecode.length();i++){
            permWord[i] = wordToDecode.charAt(i);
        }
        char[] x = reversePermutator(permWord);
        matchDecode(wordToDecode);
        reset();
        initializeMotors(left,middle, right);
        String decoded = new String(x);
        return decoded;
    }

    public static void matchDecode(String wordToDecode){
        char last = wordToDecode.charAt(9);
        searchLeftDecode(last);
    }

    public static char searchLeftDecode(char current){
        char found = '0';
        int j = 0;
        int index = 0;
        while (j < top.length) {
            if (top[j] == Character.toUpperCase(current)) {
                index = j;
                break;
            } else {
                j += 1;
            }
        }
        int finalIndex = index - 10;

        return found;
    }


    public static char[] permutator(String word){
        char[] inWord = new char[50];
        for (int i = 0; i<word.length();i++){
            inWord[i] = word.charAt(i);
        }
        char[] outWord = {inWord[configuration[0]],inWord[configuration[1]],inWord[configuration[2]],
                inWord[configuration[3]], inWord[configuration[4]], inWord[configuration[5]],inWord[configuration[6]],
                inWord[configuration[7]],inWord[configuration[8]],inWord[configuration[9]]};
        //System.out.println(outWord);
        return outWord;
    }

    public static char[] reversePermutator(char[] word){
        char[] inWord = new char[10];
        for (int i = 0; i<word.length;i++){
            inWord[configuration[i]] = word[reverseConfiguration[i]];
        }
        System.out.println(inWord);
        return inWord;
    }

    public static void rotateLeftMotor(){
        leftTurns += 1;
    }

    public static void rotateMiddleMotor(){
        middTurns += 1;
    }

    public static void rotateRightMotor(){
        rightTurns += 1;
    }

    public static void main(String[] args) {
        String construction = "";
        String message = "";
        String key = "";
        String instruction = "";
        for (int i=0; i<args.length; i+=2){
            if (args[i].equals("-c")) {
                construction = args[i + 1];
            }
            if (args[i].equals("-k")) {
                key = args[i + 1];
            }
            if (args[i].equals("-m")) {
                message = args[i + 1];
            }
            if(args[i].equals("-i")){
                instruction = args[i + 1];
            }
        }
        for(int i = 0; i<construction.length(); i++){
            configuration[i] = Integer.parseInt(construction.substring(i,i+1));
        }
        //System.out.println(construction.substring(0,1));
        left = key.charAt(0);
        middle = key.charAt(1);
        right = key.charAt(2);
        System.out.println("Construction: " + construction);
        for(int i = 0; i<message.length(); i++){
            if (!(message.length()%10 == 0)){
                message += "x";
            }
        }
        System.out.println("Message: " + message);
        System.out.println("Key: " + key);
        //System.out.println("Substring: " + message.substring(0,10));
        int startInd = 0;
        int endInd = 10;
        int x = message.length() / 10;
        int i = 0;
        String output = "";
        if(!(Character.isLetterOrDigit(left)&Character.isLetterOrDigit(middle)&Character.isLetterOrDigit(right))){
            System.out.println("Invalid Key Entry");
            System.exit(0);
        }
        else if(instruction.equalsIgnoreCase("encode")) {
            while (i < x) {
                initializeMotors(left, middle, right);
                System.out.println("Input: " + message.substring(startInd, endInd));
                output = encode(message.substring(startInd, endInd));
                System.out.println("Encoded Message: " + output);
                startInd += 10;
                endInd += 10;
                i++;
            }
        }
        else if(instruction.equalsIgnoreCase("decode")){
            while (i < x) {
                initializeMotors(left, middle, right);
                System.out.println("Input: " + message.substring(startInd, endInd));
                output = decode(message.substring(startInd, endInd));
                System.out.println("Decoded Message: " + output);
                startInd += 10;
                endInd += 10;
                i++;
            }
        }
        else{
            System.out.println("Invalid Instruction");
        }
    }
}