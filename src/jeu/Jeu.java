package jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Jeu extends JFrame implements ActionListener{
    JButton bInfo=new JButton("Info");
    JButton bStart=new JButton("Start");
    JButton bStop=new JButton("Stop");
    JButton bExit=new JButton("Exit");
    JButton bRetab=new JButton("Rtablir");
    JButton bTest=new JButton("Tester le nombre");
    // Declaration et initialisation des JTextField et JTextArea
    JTextField essaie=new JTextField();
    JTextArea histo=new JTextArea(" Bonne Chance \n",20,30); // servira pour afficher l'historique
    JScrollPane scroll=new JScrollPane(histo);
    JLabel res=new JLabel();// affichage du resultat de la tentative
    JLabel nbrTent=new JLabel();// nombre de tentative
    int[] essai = new int[5];
    int [] secret = new int[5];
    int nbEssai=0, nbV, nbT;
    int sec, ess;
    // Constructeur
    void JeuVT()
    {

        // intialiser le theme de l'application par celui du systeme
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        essaie.setMaximumSize(new Dimension(30,40));essaie.setEnabled(false);
        JLabel lTitle=new JLabel("Tester Votre Intelligence");
        lTitle.setFont(new Font("Serif", Font.BOLD, 12));
        lTitle .setForeground(Color.RED);
        JLabel lInvite=new JLabel("Tapez Votre Essaie 5 chiffres  :");

        JLabel lRes=new JLabel("Votre Nombre est :");

        JLabel nbrEss=new JLabel("==> Le nombre d'essaie est :");

        JPanel menu =new JPanel();menu.setLayout(new GridLayout(1,4,10,10));
        bInfo.addActionListener(this);bStart.addActionListener(this);
        bStop.addActionListener(this);
        bRetab.addActionListener(this);bTest.addActionListener(this);
        bStop.setEnabled(false);bRetab.setEnabled(false);bTest.setEnabled(false);
        menu.add(bInfo);menu.add(bStart);menu.add(bStop);menu.add(bExit);
        JPanel p1 =new JPanel();JPanel p2 =new JPanel();JPanel p3 =new JPanel();
        JPanel p4 =new JPanel();JPanel p5 =new JPanel();p1.add(lTitle);
        p2.setLayout(new GridLayout(1,2,10,20));p2.add(lInvite);p2.add(essaie);
        p3.setLayout(new GridLayout(1,2,20,20));p3.add(bRetab);p3.add(bTest);
        p4.setLayout(new GridLayout(1,2,20,20));p4.add(lRes);p4.add(res);
        p5.setLayout(new GridLayout(1,2,20,20));p5.add(nbrEss);p5.add(nbrTent);
        JPanel p6=new JPanel(new FlowLayout(FlowLayout.CENTER));p6.add(scroll);
        this.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        this.getContentPane().add(menu);this.getContentPane().add(p1);
        this.getContentPane().add(p2);this.getContentPane().add(p3);
        this.getContentPane().add(p4);this.getContentPane().add(p5);
        this.getContentPane().add(p6);
        setLocation(200,100);
        this.setSize(420,650);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
          if(e.getSource()==bExit)
        {dispose();}
         if(e.getSource()==bStop){bStop.setEnabled(false);bStart.setEnabled(true);
            bRetab.setEnabled(false);bTest.setEnabled(false);essaie.setEnabled(false);
            }
        if(e.getSource()==bStart){bStart.setEnabled(false);
            bStop.setEnabled(true);bRetab.setEnabled(true);bTest.setEnabled(true);
            essaie.setEnabled(true);ConstructionCode();}
        if(e.getSource()==bRetab){essaie.setText("");}
        if(e.getSource()==bTest)
        {
            int code=Integer.parseInt(essaie.getText());
            if(!validerNum(code,essai)) res.setText("Erreur ,repetition des chiffres");

            else
            {     nbEssai++;
                if(( nbT!=5) && (nbEssai<=10) ){
                nbV=verifV(essai, secret);
                    nbT=verifT(essai, secret);
                    System.out.println(nbV+" V  -  "+nbT+" T");
                    res.setText(""+nbT+" T "+nbV+" V ");
                    histo.append(essaie.getText()+" : "+res.getText()+"\n");
                nbrTent.setText(""+nbEssai);

                }
            else if (nbT==5){
                res.setText("BRAVO, Tu a reussi a trouver le code");
                bStart.setEnabled(true);bStop.setEnabled(false);bRetab.setEnabled(false);
                bTest.setEnabled(false);essaie.setEnabled(false);essaie.setText("");
            }
            else if (nbEssai>11){
                res.setText("Echec!Num_Secret:" +sec);
                bStart.setEnabled(true);bStop.setEnabled(false);bRetab.setEnabled(false);
                bTest.setEnabled(false);essaie.setEnabled(false);essaie.setText("");
            }

            }

                }


               }



    private void ConstructionCode() {

        Random r = new Random();
        do
            sec = r.nextInt(90000)+10000;// min +r.nextInt(max-min)
            //sec=(int)(Math.random()*89999 +10000);

        while(! validerNum ( sec , secret) );
    }
    private boolean verifNum(int[] t){

        for(int i=0;i<t.length-1;i++)
            for(int j=i+1; j<t.length; j++)
                if(t[i]==t[j])
                    return false;
        return true;
    }
    private boolean validerNum(int x, int[] t){

        if ( ( x>99999) || (x<10000) )
            return false;

        t[0]= x / 10000 ;
        t[1]= ( x % 10000 ) / 1000 ;
        t[2]= ( x % 1000 ) / 100;
        t[3]= ( x % 100 ) / 10;
        t[4]= ( x % 10 );

        return ( verifNum(t) );
    }
    private int verifV(int[] a, int[] b){

        int v=0;

        for(int i=0; i< a.length; i++)
            for(int j=0; j<b.length; j++)
                if ( (a[i]==b[j]) && (i!=j) )
                    v++;
        return v;
    }

    private int verifT(int[] a, int[] b){

        int t=0;

        for(int i=0; i<a.length; i++)
            if(a[i]==b[i])
                t++;
        return t;
    }
    // Méthode main
    public static void main(String[]args)
    {
        Jeu jtv=new Jeu();
        jtv.JeuVT();
    }
}

