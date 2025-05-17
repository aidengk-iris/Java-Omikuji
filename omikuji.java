// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.Random;

enum Luck{
    DaiKichi,
    ChuuKichi,
    Kichi,
    Kyou,
}

class Main {
    public static void main(String[] args) {

        Random randomNumberGenerator = new Random();
        randomNumberGenerator.setSeed(80);
        // check our implementation with some random ages
        for ( int trialIdx = 0; trialIdx < 20; trialIdx +=1 ){
            int someAge = randomNumberGenerator.nextInt(6);
            Luck luckLevel = Omikuji( someAge );
            System.out.println("Age:" + someAge + " Luck level: " + luckLevel);
        }
    }
    
    // Maps a given probability to one of the 4 Luck levels, assuming equal probability
    private static Luck MapProbabilityToLuck(float omikujiProbability){
        if (omikujiProbability > 1.0 || omikujiProbability < 0.0){
            System.out.println("Omikuji probability out of range!");
        }
        // do the actual mapping. Assuming there are 4 Luck levels
        if (omikujiProbability >= 0.75){
            return Luck.DaiKichi;
        }
        else if ( omikujiProbability >= 0.5 ){
            return Luck.ChuuKichi;
        }
        else if ( omikujiProbability >= 0.25 ){
            return Luck.Kichi;
        }
        else{
            return Luck.Kyou;
        }
    }
    
    private static Luck Omikuji(int age){
        
        // sanity check:
        if (age < 0){
            System.out.println("Age is negative!");
        }
        
        Random randomNumberGenerator = new Random();
        randomNumberGenerator.setSeed(27);
        
        // keep track of how many draws were taken until a yasashii Kuji came out
        int numberOfDrawsTaken = 1;
        
        // first check the age
        if (age > 18){
            // can get DaiKichi or ChuuKichi
            float omikujiProbability = randomNumberGenerator.nextFloat();
            while ( omikujiProbability >= 0.25 ){
                omikujiProbability = randomNumberGenerator.nextFloat();
                numberOfDrawsTaken += 1;
            }
            // good, a yasashii kuji came out. Let's check how good it is.
            Luck luckLevel = MapProbabilityToLuck( omikujiProbability );
            return luckLevel;
        }
        else if (12 >= age && 6 <= age){
            float omikujiProbability = randomNumberGenerator.nextFloat();
            // this age group only get DaiKichi and ChuuKichi
            while (omikujiProbability <= 0.5){
                omikujiProbability = randomNumberGenerator.nextFloat();
                numberOfDrawsTaken += 1;
            }
            // good, a yasashii kuji came out. Let's check how good it is.
            Luck luckLevel = MapProbabilityToLuck( omikujiProbability );
            return luckLevel;
        }
        else if (6 > age){
            return Luck.DaiKichi;
        }
        else{
            // question didn't specify this case, so just return anything
            return Luck.DaiKichi;
        }
    }
}