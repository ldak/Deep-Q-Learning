package play.training;

import bots.DeepQBot;
import bots.PatternBot;
import bots.RandomBot;
import utils.QBotSerializer;
import utils.board.Connect4Board;

public class Tests {

    public static void main(String[] args) throws Exception {
        int episodes=100;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-e")||args[i].equals("--episodes"))
                episodes=Integer.parseInt(args[++i]);
        }
        //qBotVsPattern();
        qBotVsQBot(episodes);
        //qBotVsRandom();
    }

    private static void qBotVsQBot(int episodes) throws Exception{
        QBotSerializer botSerializer=new QBotSerializer("v-1","inhuman.",true);

        DeepQBot qBot1=botSerializer.getFirstBot();
        qBot1.setPercentsRandom(50);

        DeepQBot qBot2=botSerializer.getSecondBot();
        qBot2.setPercentsRandom(50);


        BotsGame game=new BotsGame( qBot1, qBot2, new Connect4Board(7,6));
        //game.printGame();
         for (int j = 0; j <episodes ; j++) {
            game.play(10_000);
            System.out.println("Percents random: "+qBot1.getPercentsRandom()+", "+qBot2.getPercentsRandom());
            System.out.println(((j+1)/episodes)*100+"%.......");
        }
        botSerializer.save();
        System.out.println("Everything is done");
    }

    private static void qBotVsRandom() throws Exception {
        QBotSerializer botSerializer=new QBotSerializer("v-3","",true);
        DeepQBot qBot=botSerializer.getFirstBot();
        qBot.setPercentsRandom(0);

        RandomBot randomBot=new RandomBot();

        BotsGame game=new BotsGame( qBot, randomBot, new Connect4Board(7,6));

            game.play(5000);

        //game.play();
    }

    private static void qBotVsPattern() throws Exception {
        QBotSerializer botSerializer=new QBotSerializer("v-2","pattern.",false);
        DeepQBot qBot=botSerializer.getFirstBot();
        qBot.setLearningRate(0.2f);
        qBot.setPercentsRandom(50);

        PatternBot randomBot=new PatternBot();

        BotsGame game=new BotsGame( qBot, randomBot, new Connect4Board(7,6));

        game.play(1000);
        botSerializer.save();
    }

}
