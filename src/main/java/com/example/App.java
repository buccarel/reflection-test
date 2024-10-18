package com.example;

public class App {
    static final int NUMBER_OF_OBJECTS = 5_000_000;
    static final int OBJECT_SIZE = 50;

    public static void main( String[] args ) {
        boolean[] reflective = {false, true};
        String[] messages = {
            "Comparing using static equals...",
            "Comparing using reflective equals..."
        };
        for(int testCase=0; testCase<reflective.length; testCase++) {
            System.out.println("Creating " + NUMBER_OF_OBJECTS + " objects...");

            FairlyBigObject[] objects = new FairlyBigObject[NUMBER_OF_OBJECTS];
            for(int i=0; i<objects.length; i++) {
                objects[i] = new FairlyBigObject(OBJECT_SIZE, reflective[testCase]);
            }
            FairlyBigObject comparee = new FairlyBigObject(OBJECT_SIZE, reflective[testCase]);
            boolean[] result = new boolean[NUMBER_OF_OBJECTS];

            System.out.println(messages[testCase]);

            int lastPercentage = -1;
            long startTime = System.currentTimeMillis();
            for(int i=0; i<NUMBER_OF_OBJECTS; i++) {
                result[i] = objects[i].equals(comparee);
                int percentage = (i*100)/NUMBER_OF_OBJECTS;
                if(percentage != lastPercentage) {
                    System.out.print(percentage + "%\r");
                    lastPercentage = percentage;
                }
            }
            System.out.println();
            long endTime = System.currentTimeMillis();

            double totalTimeInSeconds = ((double) endTime - (double)startTime)/1000;
            System.out.println("Total time: " + totalTimeInSeconds + " s.");

            boolean finalResult = true;
            for(int i=0; i<NUMBER_OF_OBJECTS; i++) {
                finalResult = finalResult&result[i];
            }
        }
    }
}
