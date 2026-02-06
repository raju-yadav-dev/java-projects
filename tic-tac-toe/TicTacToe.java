import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.Font;

class TicTacToe
{
    static int r=1;
    static boolean reset=false;
    public static void main(String[] args)
    {
        int i,j;
        TicTacToe call = new TicTacToe();
        JFrame frame = new JFrame("Tic Tac Toe v1");
        JPanel panel = new JPanel(new GridLayout(3,3));
        JButton[][] btn = new JButton[3][3];
        int arr[][] = new int[3][3];

        //Adding buttons
        for(i=0;i<3;i++)
        {
            Font font = new Font("Arial", Font.BOLD, 40);
            for(j=0;j<3;j++)
            {
                btn[i][j]=new JButton("");
                btn[i][j].setFont(font);
                panel.add(btn[i][j]);
            }
        }

        //Button clicked effect
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                final int row=i,col=j;
                btn[i][j].addActionListener(clickEvent -> 
                {
                    if(arr[row][col]==0)
                    {
                        arr[row][col]=(r%2==0)?2:1;
                        btn[row][col].setText((r%2==0)?"O":"X");
                        r++;
                        if (call.OddChecker(arr)) 
                        {
                            JOptionPane.showMessageDialog(null, "X Wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            reset=true;
                        } else if (call.EvenChecker(arr)) 
                        {
                            JOptionPane.showMessageDialog(null, "O Wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            reset=true;
                        }else if(call.Filled(arr)) 
                        {
                            JOptionPane.showMessageDialog(null, "Match Draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            reset=true;
                        }
                        if(call.reset)
                        {
                            for(int n=0;n<3;n++)
                            {
                                for(int m=0;m<3;m++)
                                {
                                    btn[n][m].setText("");
                                    arr[n][m]=0;
                                    r=1;
                                }
                            }
                            reset=false;
                        }
                    }
                    frame.addComponentListener(new ComponentAdapter() 
                    {
                        public void componentResized(ComponentEvent e) {
                            int size = Math.min(frame.getWidth(), frame.getHeight());
                            float fontSize = Math.max(40f, size / 5f);
                            Font font = new Font("Arial", Font.BOLD, (int) fontSize);
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    btn[i][j].setFont(font);
                                }
                            }
                        }
                    });
                });
            }
        }

        //Frame Display
        frame.add(panel);
        frame.setVisible(true);
        frame.setMinimumSize(new Dimension(300, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    boolean EvenChecker(int[][] arr)
    {
        int i,j;
        for(i=0;i<3;i++)
        {
            if(arr[i][0]==2 && arr[i][1]==2 && arr[i][2]==2)
                return true;
        }
        for(i=0;i<3;i++)
        {
            if(arr[0][i]==2 && arr[1][i]==2 && arr[2][i]==2)
                return true;
        }
        if(arr[0][0]==2 && arr[1][1]==2 && arr[2][2]==2)
            return true;
        if(arr[0][2]==2 && arr[1][1]==2 && arr[2][0]==2)
            return true;
        return false;
    }

    boolean OddChecker(int[][] arr)
    {
        int i,j;
        for(i=0;i<3;i++)
        {
            if(arr[i][0]==1 && arr[i][1]==1 && arr[i][2]==1)
                return true;
        }
        for(i=0;i<3;i++)
        {
            if(arr[0][i]==1 && arr[1][i]==1 && arr[2][i]==1)
                return true;
        }
        if(arr[0][0]==1 && arr[1][1]==1 && arr[2][2]==1)
            return true;
        if(arr[0][2]==1 && arr[1][1]==1 && arr[2][0]==1)
            return true;
        return false;
    }

    boolean Filled(int[][] arr)
    {
        int i,j;
        for(i=0;i<3;i++)
        {
            for(j=0;j<3;j++)
            {
                if(arr[i][j]==0)
                    return false;
            }
        }
        return true;
    }
}