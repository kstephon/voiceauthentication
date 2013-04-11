/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package voicetest;

import com.mathworks.toolbox.javabuilder.*;
import vText.*;
import java.io.File;

/**
 *
 * @author Ken
 */
public class VoiceTest {

    public static void main(String[] args) throws MWException {
    vTextClass test1 = new vTextClass();    
    String source = "C:" + File.separator + "users" + File.separator + "ken" + File.separator + "desktop" + File.separator + "source";
    String compare = "C:" + File.separator + "users" + File.separator + "ken" + File.separator + "desktop" + File.separator + "compare";
    String filteredsource = "C:" + File.separator + "users" + File.separator + "ken" + File.separator + "desktop" + File.separator + "filteredsource";
    String filteredcompare = "C:" + File.separator + "users" + File.separator + "ken" + File.separator + "desktop" + File.separator + "filteredcompare";
    Object[] result = null;
    boolean isMatch = false;

try
{
    //record for 5 seconds
    test1.dataAcq(1, source); 
    System.out.println("Recording completed");
}
catch (Exception e)
{
    System.out.println("Exception: " + e.toString());
}  

try
{
    test1.filterbgnoise(1, source, filteredsource);
    System.out.println("Filter process completed");
}
catch (Exception e)
{
    System.out.println("Exception: " + e.toString());
}

try
{
    //record for 5 seconds
    test1.dataAcq(1, compare); 
    System.out.println("Recording completed");
}
catch (Exception e)
{
    System.out.println("Exception: " + e.toString());
}

try
{
    test1.filterbgnoise(1, compare, filteredcompare);
    System.out.println("Filter process completed");
}
catch (Exception e)
{
    System.out.println("Exception: " + e.toString());
}
 
try
{ 
    result = test1.recognize(1, filteredsource, filteredcompare, "0.5", "1.5");
    //System.out.println("voice password result=" + result[0].toString()+ "/100");
}
catch (Exception e)
{
    System.out.println("Exception: " + e.toString());
}

int resultInt = Integer.parseInt(result[0].toString());

System.out.println("The match percentage is " + result[0].toString() + "/100");

if (resultInt >= 70){
    isMatch = true;
}

if (isMatch == true){
    System.out.println("The password is a match.");
}
else{
    System.out.println("The password is not a match.");
}
    }
}
