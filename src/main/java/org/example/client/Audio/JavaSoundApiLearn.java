package org.example.client.Audio;

import javax.sound.sampled.*;

public class JavaSoundApiLearn {
    public static void main(String[] args) throws InterruptedException {
        AudioFormat audioFormat = buildAudioFormatInstance();

        SoundRecorder soundRecorder = new SoundRecorder(audioFormat);
        System.out.println("Start Rec...");
        soundRecorder.start();
        Thread.sleep(10000);
        soundRecorder.stop();

        AudioToWave audioToWave = new AudioToWave();
        Thread.sleep(3000);
        audioToWave.saveToFile("/Check", AudioFileFormat.Type.WAVE, soundRecorder.getAudioInputStream());
    }

    public static AudioFormat buildAudioFormatInstance() {
        float sampleRate = 8000.0F;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }


}
