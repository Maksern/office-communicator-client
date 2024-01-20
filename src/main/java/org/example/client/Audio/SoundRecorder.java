package org.example.client.Audio;

import lombok.Data;
import lombok.Setter;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Data
public class SoundRecorder implements Runnable{
    private AudioInputStream audioInputStream;
    private AudioFormat audioFormat;
    private double duration;
    public Thread thread;

    public SoundRecorder(AudioFormat audioFormat) {
        this.audioFormat = audioFormat;
    }

    public void start() {
        thread = new Thread(this);
        thread.setName("Capture Microphone");
        thread.start();
    }

    public void stop() {
        thread = null;
    }

    @Override
    public void run() {
        duration = 0;

        try (final ByteArrayOutputStream out = new ByteArrayOutputStream(); final TargetDataLine line = getTargetDataLineForRecord();) {

            int frameSizeInBytes = audioFormat.getFrameSize();
            int bufferLengthInFrames = line.getBufferSize() / 8;
            final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
            buildByteOutputStream(out, line, frameSizeInBytes, bufferLengthInBytes);
            this.audioInputStream = new AudioInputStream(line);
            setAudioInputStream(convertToAudioIStream(out, frameSizeInBytes));
            audioInputStream.reset();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    public void buildByteOutputStream(final ByteArrayOutputStream out, final TargetDataLine line, int frameSizeInBytes, final int bufferLengthInBytes) throws IOException {
        final byte[] data = new byte[bufferLengthInBytes];
        int numBytesRead;

        line.start();
        while (thread != null) {
            if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
                break;
            }
            out.write(data, 0, numBytesRead);
        }
    }

    public AudioInputStream convertToAudioIStream(final ByteArrayOutputStream out, int frameSizeInBytes) {
        byte audioBytes[] = out.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
        AudioInputStream audioStream = new AudioInputStream(bais, audioFormat, audioBytes.length / frameSizeInBytes);
        long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / audioFormat.getFrameRate());
        duration = milliseconds / 1000.0;
        System.out.println("Recorded duration in seconds:" + duration);
        return audioStream;
    }

    public TargetDataLine getTargetDataLineForRecord() {
        TargetDataLine line;
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);
        if (!AudioSystem.isLineSupported(info)) {
            return null;
        }
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(audioFormat, line.getBufferSize());
        } catch (final Exception ex) {
            return null;
        }
        return line;
    }
}
