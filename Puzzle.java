import java.io.FileOutputStream;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.logging.FileHandler;
import javax.swing.border.*;
import java.io.*;

import static javafx.application.Platform.exit;

public class Puzzle {

    public static void main(String[] args) throws Exception {

        int b;
        int cs=0;
        String hs="";
        try {

            FileReader fr = new FileReader("F:\\web\\bestscore.txt");
            BufferedReader br = new BufferedReader(fr);
            hs = br.readLine();
            b = new Integer(hs);
        }
        catch(Exception e)
        {
            System.out.println("caught");
            b=0;
            hs="0";
        }
        JFrame jframe = new JFrame("8 puzzle Game");
        Game g=new Game();

        BorderLayout bl=new BorderLayout();
        jframe.setLayout(bl);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setBackground(Color.BLACK);
        jframe.setSize(1100,720);
        Icon re= new ImageIcon("F:\\web\\replay.jpg");
        JButton play=new JButton(re);

        Icon ex= new ImageIcon("F:\\web\\ex.jpg");
        JButton exit=new JButton(ex);
        exit.setBackground(Color.white);
        exit.setBorder(new LineBorder(Color.lightGray,3));
        JLabel Hs=new JLabel("<html><head><style>body{font-size:20px;margin:60px;color:teal;font-family:serif;</style></head><body>Best Play:<br><pre>"+hs+" moves</pre></body></html>");
        Hs.setBorder(new LineBorder(Color.lightGray,3));
        JLabel CurrentScore=new JLabel("<html><head><style>body{font-size:20px;margin:50px;color:teal;font-family:serif;</style></head><body>Current Play:<br><pre> "+cs+" moves</pre></body></html>");
        CurrentScore.setBorder(new LineBorder(Color.lightGray,3));
        JPanel pan=new JPanel();
        BorderLayout l=new BorderLayout();
        pan.setLayout(l);
        Icon bg= new ImageIcon("F:\\web\\bg.jpg");
        JLabel blank=new JLabel(bg);
        pan.add(blank,BorderLayout.WEST);
        JPanel pan1=new JPanel();
        GridLayout gl=new GridLayout(4,1);
        pan1.setLayout(gl);
        pan1.setBackground(Color.WHITE);
        pan1.add(Hs);
        pan1.add(CurrentScore);
        pan1.add(play);
        pan1.add(exit);
        pan.add(pan1,BorderLayout.CENTER);
        jframe.add(pan,BorderLayout.EAST);
        ActionListener foreplay=new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                jframe.setVisible(false);
                JFrame conf=new JFrame("Confirm");
                conf.setSize(400,600);
                conf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                JPanel conp=new JPanel();
                conp.setLayout(new FlowLayout());
                JLabel conl=new JLabel("Are you sure");
                JButton yes=new JButton("Yes");
                JButton no=new JButton("Go back");
                ActionListener oy=new ActionListener() {
                    public void actionPerformed(ActionEvent cy) {
                        String bs="";
                        int b;
                        int cs=0;
                        try {

                            FileReader fr = new FileReader("F:\\web\\bestscore.txt");
                            BufferedReader br = new BufferedReader(fr);
                            bs = br.readLine();
                            b = new Integer(bs);
                            br.close();
                        }
                        catch(Exception ne)
                        {
                            System.out.println("caught");
                            b=0;
                            bs="0";
                        }
                        conf.dispose();
                            g.jc.removeAll();
                            g.h = 0;
                            jframe.remove(g.jc);
                            CurrentScore.setText("<html><head><style>body{font-size:20px;margin:50px;color:teal;font-family:serif;</style></head><body>Current Play:<br><pre> " + cs + " moves</pre></body></html>");
                            g.play(jframe, CurrentScore, b, Hs);
                        }
                };
                ActionListener on=new ActionListener() {
                    public void actionPerformed(ActionEvent cy) {
                        jframe.setVisible(true);
                        conf.dispose();
                    }
                };
                yes.addActionListener(oy);
                no.addActionListener(on);
                conp.add(conl);
                conp.add(yes);
                conp.add(no);
                conf.add(conp);
                conf.setSize(400,600);
                conf.setVisible(true);
            }
        };
                ActionListener forexit=new ActionListener() {
                    public void actionPerformed(ActionEvent lc) {
                        jframe.setVisible(false);
                        JFrame njf=new JFrame("Confirm");
                        njf.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        JLabel mes = new JLabel("Are you sure?");
                        JButton go = new JButton("Yes");
                        JButton no = new JButton("Go back");

                        ActionListener forgo = new ActionListener() {
                            public void actionPerformed(ActionEvent t1) {
                                System.exit(1);
                                System.exit(1);
                            }
                        };
                        go.addActionListener(forgo);
                        ActionListener forno = new ActionListener() {
                            public void actionPerformed(ActionEvent m1) {
                                njf.dispose();
                                jframe.setVisible(true);
                            }
                        };
                        no.addActionListener(forno);
                        njf.setSize(400,300);
                        JPanel np=new JPanel();
                        np.setLayout(new FlowLayout());
                        np.add(mes);
                        np.add(go);
                        np.add(no);
                        njf.add(np);
                        njf.setVisible(true);
                    }
                };
        exit.addActionListener(forexit);
        play.setBackground(Color.white);
        play.setBorder(new LineBorder(Color.lightGray,3));
        play.addActionListener(foreplay);
        g.play(jframe,CurrentScore,b,Hs);
    }
}
class Game
        {
            int h=0;
            JPanel jc = new JPanel();
            JButton[] jl = new JButton[9];
            String[] boxes = new String[]{"13", "024", "15", "046", "1357", "428", "37", "468", "75"};
            int[] fin = new int[9];
            int[] track= new int[9];
            public void play(JFrame jf,JLabel cs,int bs,JLabel Hs)
        {

            jc.setBackground(Color.darkGray);

            for(int i=0;i<9;i++) {
                System.out.print("" + track[i]);
            }
            try {
                track[0]=0;track[1]=1;track[2]=3;track[3]=4;track[4]=2;track[5]=5;track[6]=7;track[7]=8;track[8]=6;
                for(int i=0;i<9;i++) {
                    System.out.print(""+track[i]);
                }
              System.out.println(bs);
                 int i;
                 Random r=new Random();
                 for(i=0;i<9;i++) {
                 int i1 = r.nextInt(8);
                 int i2 = r.nextInt(8);
                 if (i1 == 0 || i2 == 0) {
                 continue;
                 } else {
                 int temp = track[i1];
                 track[i1] = track[i2];
                 track[i2] = temp;
                 }
                 }
                int inv=0;
                for(int k=0;k<9-1;k++) {
                    for(int j=k+1;j<9;j++) {
                        if((track[k]!=0) && (track[j]!=0) && track[k]>track[j])
                        {
                            inv++;
                        }
                    }
                }
                System.out.println("Inversion"+inv);
                int v=0;
                if(inv%2!=0)
                {
                    for(int k=0;k<9-1;k++) {
                        if(v==1)
                        {
                            break;
                        }
                        for(int j=k+1;j<9;j++) {
                            if((track[k]!=0) && (track[j]!=0) && track[k]>track[j])
                            {
                                int f=track[k];
                                track[k]=track[j];
                                track[j]=f;
                                v=1;
                                break;
                            }
                        }
                    }
                }
                for (i = 0; i < 8; i++) {
                    fin[i] = i + 1;
                }
                fin[8] = 0;
                ActionListener al = new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        String st = e.getActionCommand();
                        char[] str = st.toCharArray();
                        int op = str[1] - 48;

                        for (int j = 0; j < 9; j++) {
                            if (op == j) {
                                char[] b = boxes[j].toCharArray();
                                for (int i = 0; i < b.length; i++) {
                                    int l = b[i] - 48;
                                    if (track[l] == 0) {
                                        int k = track[op];
                                        track[op] = track[l];
                                        track[l] = k;
                                        h++;
                                        try {
                                            if (bs == 0) {
                                                FileWriter fw = new FileWriter("F:\\web\\bestscore.txt");
                                                BufferedWriter bw = new BufferedWriter(fw);
                                                bw.write(new Integer(h).toString());
                                                bw.close();
                                                Hs.setText("<html><head><style>body{font-size:20px;margin:50px;color:teal;font-family:serif;</style></head><body>Current Play:<br><pre> " + h + " moves</pre></body></html>");
                                            }
                                        }
                                        catch(Exception q)
                                        {

                                        }
                                        cs.setText("<html><head><style>body{font-size:20px;margin:50px;color:teal;font-family:serif;</style></head><body>Current Play:<br><pre> " + h + " moves</pre></body></html>");
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        for (int i = 0; i < 9; i++) {
                            if (track[i] == 0) {
                                jl[i].setVisible(false);
                            } else {
                                jl[i].setVisible(true);
                                jl[i].setText("<html><head><style>h1{font-size:40px;color:black;font-family:verdana;}</style></head><body><h1>" + track[i] + "</h1></body></html>");
                            }
                        }
                        int f = 0;
                        int x=0;
                        int j=1;
                        while(x<9&&j<9) {
                           if(track[j]==0)
                           {
                               j++;
                               continue;
                           }
                           else if(track[j]-1==track[x])
                           {
                               x++;
                               j++;
                               continue;
                           }
                           else
                           {
                               f=1;
                               break;
                           }
                        }
                        if (f == 0) {
                            try {
                                System.out.println("Game won");
                                JLabel jm = new JLabel("Game Won");
                            try {
                                  if (bs > h) {
                                      FileWriter fw = new FileWriter("F:\\web\\bestscore.txt");
                                      BufferedWriter bw = new BufferedWriter(fw);
                                     bw.write(new Integer(h).toString());
                                     bw.close();
                                       }
                                    }
                              catch(Exception i)
                                 {

                                   }
                                for (int i = 0; i < 9; i++)
                                    jf.remove(jl[i]);
                                jf.add(jm);
                                jf.setVisible(true);
                            } catch (Exception ex) {

                            }
                        }
                    }
                };
                GridLayout gl = new GridLayout(3, 3);
                jc.setLayout(gl);
                for (i = 0; i < 9; i++) {
                    String s = " " + i;
                    jl[i] = new JButton();
                    jl[i].addActionListener(al);
                    jl[i].setActionCommand(" " + i);
                }
                for (i = 0; i < 9; i++) {
                    System.out.print("cr"+track[i]+"ac"+jl[i].getActionCommand());
                    if (track[i] == 0) {
                        jl[i].setVisible(false);
                    }
                    String htmls = "<html><head><style>h1{font-size:40px;color:black;font-family:verdana;}</style></head><body><h1>" + track[i] + "</h1></body></html>";
                    jl[i].setText(htmls);
                    jl[i].setBackground(Color.lightGray);
                    jc.add(jl[i]);
                }
                jf.add(jc, BorderLayout.CENTER);
                jf.setVisible(true);
            }
            catch(Exception ex)
            {

            }
        }
        }
