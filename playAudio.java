// import the necessary classes for playing audio files
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

// create a class called playAudio
public class playAudio { 
	// method to play a music file
	Clip playMusic(String musicLocation){
		try{
			// create a URL object to locate the audio file
			URL audioURL = playAudio.class.getResource(musicLocation);

			// create an AudioInputStream object to read the audio file
		    	AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioURL);

			// create a Clip object to play the audio file
		    	Clip audioClip = AudioSystem.getClip();

			// open the audio file and start playing
		   	audioClip.open(audioStream);
			return audioClip;	
				
		}catch(Exception ex){
			// print the stack trace if an exception occurs
			ex.printStackTrace();
		}
		return null;
	} //end of playMusic
} //end of playMusic method


