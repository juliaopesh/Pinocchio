import javax.swing.*;
import java.io.FileNotFoundException;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.*;
import java.io.File;
import java.awt.geom.Ellipse2D;

public class HomeWindow 
{
    Database data = new Database(); 
    
    JFrame frame = new JFrame("Home Page");
    JScrollPane scrollPane = new JScrollPane();
    JPanel parentPanel;
    JButton button = new JButton("My Account");

    public void JPanelsInsideJScrollPane(Person user) throws Exception
    {
        parentPanel = new JPanel() 
        {
            @Override
            public Dimension getPreferredSize() 
            {
                return new Dimension(new Dimension(400, 7000));
            }

            @Override
            public void paintComponent(Graphics g) 
            {
                int margin = 10;
                Dimension dim = getSize();
                super.paintComponent(g);
                g.setColor(Color.WHITE);
            }
        };

        Post[] currentPosts = data.allPosts;
        JPanel[] pnlArray = new JPanel[currentPosts.length];
        for (int i = 0; i < currentPosts.length; i++)
        {
            pnlArray[i] = new JPanel()
            {
                @Override
                public Dimension getPreferredSize() 
                {
                    return new Dimension(new Dimension(400, 270));
                }
                
                @Override
                public void paintComponent(Graphics g) 
                {
                    int margin = 10;
                    Dimension dim = getSize();
                    super.paintComponent(g);
                    g.setColor(Color.WHITE);
                    g.fillRect(margin, margin, dim.width - margin * 2, dim.height - margin * 2);
                }
            };
            pnlArray[i].setLayout(null);

            Person[] currentUsers = data.allUsers;
            for (int j = 0; j < currentUsers.length; j++)
            {

                String usernm1 = currentUsers[j].userName;
                String author1 = currentPosts[i].author;


                if(String.valueOf(usernm1).equals(String.valueOf(author1)) )
                {
                    ImageIcon image = new ImageIcon(currentUsers[j].profile_pic);
                    JLabel img1 = new JLabel(image);
                    img1.setBounds(20,100,70,70);
                    pnlArray[i].add(img1);
                }
            }

         try
        {
            BufferedImage myPicture = ImageIO.read(new File("src/imgs/like.png"));
            Image tmp = myPicture.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            
            JButton like = new JButton(new ImageIcon(dimg));
            like.setOpaque(true);
            like.setContentAreaFilled(false);
            like.setBorderPainted(false);
            like.setFocusPainted(false);
            like.setBounds(110,190,50,50);
            pnlArray[i].add(like);

            like.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    System.out.print("liked");
                    //Post[] currentPosts = data.fetchPosts();
                    //currentPosts[i].likes +=1;
                }  
            });

        } 
        catch(Exception e)
        {

        }
        try
        {
            BufferedImage myPicture = ImageIO.read(new File("src/imgs/dislike.png"));
            Image tmp = myPicture.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            
            JButton dislike = new JButton(new ImageIcon(dimg));
            dislike.setOpaque(true);
            dislike.setContentAreaFilled(false);
            dislike.setBorderPainted(false);
            dislike.setFocusPainted(false);
            dislike.setBounds(220,195,50,50);
            pnlArray[i].add(dislike);

            dislike.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    System.out.print("disliked");
                }  
            });

        } 
        catch(Exception e)
        {

        }
        
            JLabel likes = new JLabel(String.valueOf(currentPosts[i].likes));
            JLabel dislikes = new JLabel(String.valueOf(currentPosts[i].dislikes));

            if (currentPosts[i].verifStatus == 0)
            {
                likes.setForeground(Color.black);
                dislikes.setForeground(Color.black);
            }
            else if (currentPosts[i].verifStatus == 1)
            {
                likes.setForeground(Color.blue);
                dislikes.setForeground(Color.black);
            }
            else if (currentPosts[i].verifStatus == 2)
            {
                likes.setForeground(Color.black);
                dislikes.setForeground(Color.red);
            }
        
            JTextArea text1 = new JTextArea();
            JButton button1 = new JButton(currentPosts[i].author);


            /*button1.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    System.out.print("clicked");
                    //frame.setVisible(false);
                    //UserWindow window = new UserWindow();
                    for (int i = 0; i < currentPosts.length; i++)
                    {
                        for (int j = 0; j < currentUsers.length; j++)
                        {
                            if(String.valueOf(currentUsers[j].userName).equals(String.valueOf(currentPosts[i].author)))
                            {
                                System.out.println(String.valueOf(currentUsers[j].userName));
                                System.out.println(String.valueOf(currentPosts[i].author));

                                frame.setVisible(false);
                                data.fetchUserPosts(data.allPosts, data.allUsers, currentUsers[j].userName);

                                UserWindow window = new UserWindow();
                                window.showHomeScreen(currentUsers[j],data);

                                
                                //UserWindow page = new UserWindow();
                                //page.showHomeScreen(user, data);
                            }
                        }   
                    }
                }
            });
            */
             //JLabel likes = new JLabel();

            String str = currentPosts[i].statement;

            text1.setText(str);

            // Wrap the lines of the JTextArea
            text1.setLineWrap(true);
            text1.setWrapStyleWord(true);

            button1.setBounds(170,40,150,30);
            text1.setBounds(100,100,280,130);
            likes.setBounds(160,190,80,50);
            dislikes.setBounds(270,190,80,50);

            pnlArray[i].add(likes);
            pnlArray[i].add(dislikes);
            pnlArray[i].add(button1);
            pnlArray[i].add(text1);

            parentPanel.add(pnlArray[i]);
        }
        

        //Child panels to hold individual posts
        //parentPanel.setLayout(new GridLayout(0, 1));

        parentPanel.setLayout(new FlowLayout());
    
        scrollPane.setViewportView(parentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);

        button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                UserWindow window = new UserWindow();
                window.showHomeScreen(user,data);
            }  
        });

        frame.add(scrollPane);
        frame.add(button, BorderLayout.NORTH);
        frame.setSize(450, 900 ); 
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }  
//////////////////////////////////////////////////////////////////////////////////////////////////
    
public void individualPane(Person user) throws Exception
    {
        parentPanel = new JPanel() 
        {
            @Override
            public Dimension getPreferredSize() 
            {
                return new Dimension(new Dimension(400, 2200));
            }

            @Override
            public void paintComponent(Graphics g) 
            {
                int margin = 10;
                Dimension dim = getSize();
                super.paintComponent(g);
                g.setColor(Color.WHITE);
            }
        };

        Post[] currentPosts = data.allPosts;
        JPanel[] pnlArray = new JPanel[user.posts.length];
        for (int i = 0; i < user.posts.length; i++)
        {
            pnlArray[i] = new JPanel()
            {
                @Override
                public Dimension getPreferredSize() 
                {
                    return new Dimension(new Dimension(400, 270));
                }
                
                @Override
                public void paintComponent(Graphics g) 
                {
                    int margin = 10;
                    Dimension dim = getSize();
                    super.paintComponent(g);
                    g.setColor(Color.WHITE);
                    g.fillRect(margin, margin, dim.width - margin * 2, dim.height - margin * 2);
                }
            };
            pnlArray[i].setLayout(null);


                String usernm1 = user.userName;
                String author1 = currentPosts[i].author;


                if(String.valueOf(usernm1).equals(String.valueOf(author1)) )
                {
                    ImageIcon image = new ImageIcon(user.profile_pic);
                    JLabel img1 = new JLabel(image);
                    img1.setBounds(20,100,70,70);
                    pnlArray[i].add(img1);
                }
            //}

         try
        {
            BufferedImage myPicture = ImageIO.read(new File("src/imgs/like.png"));
            Image tmp = myPicture.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            
            JButton like = new JButton(new ImageIcon(dimg));
            like.setOpaque(true);
            like.setContentAreaFilled(false);
            like.setBorderPainted(false);
            like.setFocusPainted(false);
            like.setBounds(110,190,50,50);
            pnlArray[i].add(like);

            like.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    System.out.print("liked");
                    //Post[] currentPosts = data.fetchPosts();
                    //currentPosts[i].likes +=1;
                }  
            });

        } 
        catch(Exception e)
        {

        }
        try
        {
            BufferedImage myPicture = ImageIO.read(new File("src/imgs/dislike.png"));
            Image tmp = myPicture.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            BufferedImage dimg = new BufferedImage(25, 25, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = dimg.createGraphics();
            g2d.drawImage(tmp, 0, 0, null);
            g2d.dispose();
            
            JButton dislike = new JButton(new ImageIcon(dimg));
            dislike.setOpaque(true);
            dislike.setContentAreaFilled(false);
            dislike.setBorderPainted(false);
            dislike.setFocusPainted(false);
            dislike.setBounds(220,195,50,50);
            pnlArray[i].add(dislike);

            dislike.addActionListener(new ActionListener() 
            {
                public void actionPerformed(ActionEvent e) 
                {
                    System.out.print("disliked");
                }  
            });

        } 
        catch(Exception e)
        {

        }
        
            JLabel likes = new JLabel(String.valueOf(currentPosts[i].likes));
            JLabel dislikes = new JLabel(String.valueOf(currentPosts[i].dislikes));

            if (currentPosts[i].verifStatus == 0)
            {
                likes.setForeground(Color.black);
                dislikes.setForeground(Color.black);
            }
            else if (currentPosts[i].verifStatus == 1)
            {
                likes.setForeground(Color.blue);
                dislikes.setForeground(Color.black);
            }
            else if (currentPosts[i].verifStatus == 2)
            {
                likes.setForeground(Color.black);
                dislikes.setForeground(Color.red);
            }
        
            JTextArea text1 = new JTextArea();
            JButton button1 = new JButton(currentPosts[i].author);


            String str = currentPosts[i].statement;

            text1.setText(str);

            // Wrap the lines of the JTextArea
            text1.setLineWrap(true);
            text1.setWrapStyleWord(true);

            button1.setBounds(170,40,150,30);
            text1.setBounds(100,100,280,130);
            likes.setBounds(160,190,80,50);
            dislikes.setBounds(270,190,80,50);

            pnlArray[i].add(likes);
            pnlArray[i].add(dislikes);
            pnlArray[i].add(button1);
            pnlArray[i].add(text1);

            parentPanel.add(pnlArray[i]);
        }
        

        //Child panels to hold individual posts
        //parentPanel.setLayout(new GridLayout(0, 1));

        parentPanel.setLayout(new FlowLayout());
    
        scrollPane.setViewportView(parentPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);

        button.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                frame.setVisible(false);
                UserWindow window = new UserWindow();
                window.showHomeScreen(user,data);
            }  
        });

        frame.add(scrollPane,BorderLayout.SOUTH);
        frame.add(button, BorderLayout.NORTH);
        frame.setSize(450, 450 ); 
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}