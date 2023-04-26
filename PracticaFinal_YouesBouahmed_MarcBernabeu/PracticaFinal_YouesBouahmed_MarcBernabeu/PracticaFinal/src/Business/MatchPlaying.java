package Business;

import java.util.ArrayList;
import java.util.Random;

public class MatchPlaying {
    /**
     * Método que simula un duelo entre un jugador atacante y un jugador defensivo de distintos equipos
     * @param Player attacker
     * @param Player defender
     * @return ArrayList<String> matchDetail
     */
    public ArrayList<String> matchDuel(Player attacker, Player defender) {

        ArrayList<String> matchDetail = new ArrayList<>();

        Random r = new Random();
        int rAttackerChoice = r.nextInt(3) + 1;

        switch(rAttackerChoice) {
            //velocidad
            case 1:
                if (attacker.getSpeed() > defender.getSpeed()){
                    String speedResultString = attacker.getName() + " chooses to outrun and succeed!";
                    matchDetail.add(speedResultString);
                    System.out.println(speedResultString);

                    int scoreProbability = r.nextInt((100+1)-1) + 1;
                    if (scoreProbability <= ((PlayerAttacker) attacker).getAccuracy()) {
                        String probability1 = attacker.getName() + " shoots and ... scores 1 point!";
                        System.out.println(probability1);
                        matchDetail.add(probability1+"\n");
                        attacker.addScoredPoints(1);
                    } else {
                        String failedProb = attacker.getName()+" shoots and ... misses!";
                        System.out.println(failedProb);
                        matchDetail.add(failedProb+"\n");
                    }
                } else {
                    String outrunFail = attacker.getName() + " chooses to outrun and fails!";
                    System.out.println(outrunFail);
                    matchDetail.add(outrunFail+"\n");
                }
                break;
            //resisténcia
            case 2:
                if (attacker.getEndurance() > defender.getEndurance()) {
                    String run = attacker.getName() + " chooses to keep running and succeed!";
                    System.out.println(run);
                    matchDetail.add(run);

                    int scoreProbability = r.nextInt((100+1)-1) + 1;
                    if (scoreProbability <= ((PlayerAttacker) attacker).getAccuracy()) {
                        String probability2 = attacker.getName() + " shoots and ... scores 1 point!";
                        System.out.println(probability2);
                        matchDetail.add(probability2+"\n");
                        attacker.addScoredPoints(1);
                    } else {
                        String fail = attacker.getName()+" shoots and ... misses!";
                        System.out.println(fail);
                        matchDetail.add(fail+"\n");
                    }
                } else {
                    String failRun = attacker.getName() + " chooses to keep running and fails!";
                    System.out.println(failRun);
                    matchDetail.add(failRun+"\n");
                }
                break;
            //Trickery
            case 3:
                if (attacker.getTrickery() > defender.getTrickery()){
                    String trickString = attacker.getName() + " chooses to dribble and succeed!";
                    System.out.println(trickString);
                    matchDetail.add(trickString);

                    int scoreProbability = r.nextInt((100+1)-1) + 1;
                    if (scoreProbability <= ((PlayerAttacker) attacker).getAccuracy()) {
                        String probability3 = attacker.getName()+" shoots and ... scores 1 point!";
                        System.out.println(probability3);
                        matchDetail.add(probability3+"\n");
                        attacker.addScoredPoints(1);
                    } else {
                        String fail = attacker.getName()+" shoots and ... misses!";
                        System.out.println(fail);
                        matchDetail.add(fail+"\n");
                    }
                } else {
                    String failTrick = attacker.getName() + " chooses to dribble and fails!";
                    System.out.println(failTrick);
                    matchDetail.add(failTrick+"\n");
                }
                break;
        }
        return matchDetail;
    }
}
