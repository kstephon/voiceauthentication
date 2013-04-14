/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voiceauthentication;

import vText.vTextClass;

/**
 *
 * @author Ken
 */
public class VoiceFunctions {
    
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
        vTextClass record = new vTextClass();
        record.dataAcq(1, userName);
        record.filterbgnoise(1, userName, userName + "filtered");
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
        match = compare.recognize(1, userName + "filtered", filterResult, "0.5", "1.5");
        
        int resultInt = Integer.parseInt(match[0].toString());
        
        //return true if sample is 70% match or better
        if(resultInt>=70)return true;
        return false;
        
        
    }
    
    //same as above, but the comparison sample is not recorded until the user speaks
    //add GUI warning that the auto detect will not function in loud environments
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean compareSampleAuto()throws Exception{
        vTextClass compare = new vTextClass();
        compare.autoDetectWrite(1, result, 60/*sets threshold for recording*/, 5);
        compare.filterbgnoise(1, result, filterResult);
        match = compare.recognize(1, userName + "filtered", filterResult, "0.5", "1.5");
        
        int resultInt = Integer.parseInt(match[0].toString());
        
        //return true if sample is 70% match or better
        if(resultInt>=70)return true;
        return false;
    }
    
}
