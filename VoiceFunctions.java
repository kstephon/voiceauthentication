package VoiceAuthentication;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vText.vTextClass;


/**
*
* @author Ken
*/
public class VoiceFunctions extends DecryptOrNew{
    
    //all values kept private to increase the security of the system
    private String userName;
    private String result = "lastSample";
    private String filterResult = "lastSampleFiltered";
    private Object[] match;
    
    //constructor to create a VoiceFunctions object
    //only required parameter is the userName that the GUI is working with
    /**
*
* @param userName
*/
    public VoiceFunctions(String userName){
        this.userName = userName;
    }
    
    //records initial voice sample when a new user is enrolled
    /**
*
* @throws Exception
*/
    public void recordNewUser()throws Exception{
        File newAudioFile = new File(userName + "filtered.wav");
        vTextClass record = new vTextClass();
        record.dataAcq(1, userName);
        record.filterbgnoise(1, userName, userName + "filtered");
        try {
            encryptNewAudio(userName + "filtered", newAudioFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VoiceFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VoiceFunctions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(VoiceFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        cleanUpFirstAudio(userName);
    }
    
    //compares a speech sample to a previously recorded sample for an enrolled user
    /**
*
* @return
* @throws Exception
*/
    public boolean compareSample()throws Exception{
        vTextClass compare = new vTextClass();
        compare.dataAcq(1, result);
        compare.filterbgnoise(1, result, filterResult);
        
        DecryptAudio(userName + "filtered");
        
        match = compare.recognize(1, "decryptedAudio", filterResult, "0.5", "1.5");
        
        int resultInt = Integer.parseInt(match[0].toString());
        System.out.println(resultInt + "%");
        //return true if sample is 70% match or better
        if(resultInt>=60)return true;
        return false;
        
        
    }    
}