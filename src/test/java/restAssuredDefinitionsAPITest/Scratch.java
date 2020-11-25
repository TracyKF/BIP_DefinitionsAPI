package restAssuredDefinitionsAPITest;

import java.util.Arrays;

public class Scratch {

//     How to sort a string array in java
    public static void main(String[] args) {
        String[] strVars  = new String[]{"hello", "A", "1453", "3.5", "true"};
        int size = strVars.length;
        for(int a = 0 ; a < size -1; a++)
        {
           for (int b = a + 1; b < strVars.length; b++)
           {
               if(strVars[a].compareTo(strVars[b] ) > 0) {
                   String temp = strVars[a];
                   strVars[a] = strVars[b];
                   strVars[b] = temp;
               }
           }
        }

        System.out.println(Arrays.toString(strVars));

    }
}
