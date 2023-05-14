package teach0516.zetcode;

import javax.swing.*;

public class TestSwing {
    public static void main(String[] args) {
//        JFrame jFrame = new JFrame("I'm title");
//        JLabel jLabel = new JLabel("xxx");
//        jFrame.add(jLabel);
//
//        jFrame.setSize(300, 200);
//        jFrame.setVisible(true);

        testJPanel();
    }

    private static void testJPanel(){
        JFrame jFrame = new JFrame("I'm title");

        JPanel jPanel = new JPanel();
        JLabel jLabel = new JLabel("xxx");
        jPanel.add(jLabel);
        jFrame.add(jPanel);

        jFrame.setSize(300, 200);
        jFrame.setVisible(true);
    }
}
