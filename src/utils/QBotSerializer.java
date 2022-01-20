package utils;

import bots.DeepQBot;

import java.io.*;

public class QBotSerializer {

    private final File firstBotFile;
    private File secondBotFile;
    private DeepQBot firstBot;
    private DeepQBot secondBot;

    public QBotSerializer(String version, String prefix, Boolean secondBot){
        this.firstBotFile = new File(prefix+"firstBot."+version);
        this.firstBot=readFile(firstBotFile);
        if (secondBot){
            this.secondBotFile = new File(prefix+"secondBot."+version);
            this.secondBot=readFile(secondBotFile);
        }
    }

    public QBotSerializer(String version){
        this(version,"connect4.",false);
    }

    public DeepQBot getFirstBot(){
        return firstBot;
    }

    public DeepQBot getSecondBot(){
        return secondBot;
    }

    private DeepQBot readFile(File file) {
        System.out.println("Reading file "+file.getName());
        DeepQBot qBot;
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));
            qBot = (DeepQBot) in.readObject();
            in.close();
        }catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            qBot =new DeepQBot();
        }
        return qBot;
    }

    public void save() throws IOException {
        saveInFile(firstBot,firstBotFile);
        if (secondBot!=null)
            saveInFile(secondBot,secondBotFile);
    }

    private void saveInFile(DeepQBot qBot, File saveFile) throws IOException {
        System.out.println("Saving in file: "+saveFile.getName());
        if (!saveFile.exists()){
            System.out.println("Creating the file: "+saveFile.createNewFile());
        }
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(saveFile));
        out.writeObject(qBot);
        out.close();
    }

    public void setFirstBot(DeepQBot firstBot) {
        this.firstBot = firstBot;
    }

    public void setSecondBot(DeepQBot secondBot) {
        this.secondBot = secondBot;
    }
}
