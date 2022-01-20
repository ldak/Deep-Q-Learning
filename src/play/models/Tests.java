package play.models;

import bots.DeepQBot;
import bots.PatternBot;
import bots.RandomBot;
import utils.QBotSerializer;
import utils.board.Connect4Board;

public class Tests {

    public static void main(String[] args) throws Exception {
        qBotVsPattern();
        //qBotVsRandom();
    }

    private static void qBotVsPattern() throws Exception {
        QBotSerializer botSerializer=new QBotSerializer("v-2","pattern.",false);
        DeepQBot qBot=botSerializer.getFirstBot();
        qBot.setLearningRate(0.2f);
        qBot.setPercentsRandom(0);

        PatternBot randomBot=new PatternBot();

        BotsGame game=new BotsGame( qBot, randomBot, new Connect4Board(7,6));

        game.play(1000);
        botSerializer.save();
    }

    private static void qBotVsRandom() throws Exception {
        QBotSerializer botSerializer=new QBotSerializer("v-1");
        DeepQBot qBot=botSerializer.getFirstBot();
        qBot.setPercentsRandom(50);

        RandomBot randomBot=new RandomBot();

        BotsGame game=new BotsGame( qBot, randomBot, new Connect4Board(7,6));
        //game.play();
    }

}
