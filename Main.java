import java.io.FileInputStream;
import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    music();
  }
  public static void music(){
    AudioPlayer MGP = AudioPlayer.player;
    AudioStream BGM;
    AudioData MD;

    ContinuousAudioDataStream loop = null;

    try{
        BGM = new AudioStream(new FileInputStream("./music/bgm.mp3"));
        MD = BGM.getData();
        loop = new ContinuousAudioDataStream(MD);
    }
    catch(IOException e)
    {
        System.out.println("cant find the file");
    }

    MGP.start(loop);
}
}