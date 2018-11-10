package com.stock.analysis.utils;

import java.awt.Toolkit;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class AudioUtil {

	public static float SAMPLE_RATE = 8000f;

	  public static void tone(int hz, int msecs) 
	     throws LineUnavailableException 
	  {
	     tone(hz, msecs, 1.0);
	  }

	  public static void tone(int hz, int msecs, double vol)
	      throws LineUnavailableException 
	  {
	    byte[] buf = new byte[1];
	    AudioFormat af = 
	        new AudioFormat(
	            SAMPLE_RATE, // sampleRate
	            8,           // sampleSizeInBits
	            1,           // channels
	            true,        // signed
	            false);      // bigEndian
	    SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
	    sdl.open(af);
	    sdl.start();
	    for (int i=0; i < msecs*8; i++) {
	      double angle = i / (SAMPLE_RATE / hz) * 2.0 * Math.PI;
	      buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
	      sdl.write(buf,0,1);
	    }
	    sdl.drain();
	    sdl.stop();
	    sdl.close();
	  }

	  public static void notifyBuySignal(){
		  try{
			  AudioUtil.tone(5000,500);
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	  }
	  
	  public static void notifySellSignal(){
		  try{
			  AudioUtil.tone(400,500);
		  }
		  catch(Exception e){
			  e.printStackTrace();
		  }
	  }
	  public static void main(String[] args) throws Exception {
	    notifyBuySignal();
		Thread.sleep(1000);
		notifySellSignal();
	    Thread.sleep(1000);
	  }
}
