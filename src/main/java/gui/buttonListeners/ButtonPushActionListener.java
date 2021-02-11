package gui.buttonListeners;

import gui.windowListenersControllers.WindowExitWolfsController;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class ButtonPushActionListener implements ActionListener {
     Clip clip;
     JFrame jFrame;


    @Override
    public void actionPerformed(ActionEvent e) {
        jFrame = new JFrame();
        JLabel imgLabel = new JLabel(new ImageIcon("src/wolfs.jpg"));
        imgLabel.setBounds(0, 0, 575, 400);

        File soundFile = new File("src/Homie.wav");
        AudioInputStream inAudio;
        try {
            inAudio = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(inAudio);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        }
        if (clip != null) {
            clip.setFramePosition(0);
            FloatControl gainControl;
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (gainControl != null) {
                gainControl.setValue((float) -55);
            }
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        jFrame.add(imgLabel);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.setSize(575, 400);
        jFrame.setLocationRelativeTo(null);

        WindowListener exitWindowListener = new WindowExitWolfsController(jFrame,clip);
        jFrame.addWindowListener(exitWindowListener);

    }
}
//    ActionListener exitActionListener1 = new ExitButtonListener();
//                exitActionListener1.actionPerformed(jFrame.addWindowListener(new WindowAdapter() {
//@Override
//public void windowClosing(WindowEvent e) {
//        finalClip.close();
//        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);