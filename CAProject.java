/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.project;
import java.io.BufferedWriter;
import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner; 
/**
 *
 * @author Adil Ayub
 */
public class CAProject {

    /**
     * @param args the command line arguments
     */
    public int counting(File file) throws FileNotFoundException 
    {
        Scanner scan= new Scanner(file);
        int count=0;
        while(scan.hasNextLine())
        {
            count++;
            scan.nextLine();
        }
        return count;
    }
    public int columns(File file) throws FileNotFoundException
    {
        int count=0;String temp;int index=0;
        Scanner scan= new Scanner(file);
        temp=scan.nextLine();
        //System.out.println(temp);
        while(index != temp.length())
        {
            if(temp.charAt(index) == ' ')
                count++;
            index++;
        }
        return count;
    }
    public int [][] fillarray(int arr[][],File file,int cols,int max) throws FileNotFoundException
    {
        Scanner sc = new Scanner(file);int row=0,temp,col=0;
        for(int i=0;i<=max;i++)
        {
            arr[row][i]=i;
        }
        row++;
        while (sc.hasNextInt())
        {
            temp=sc.nextInt();
            arr[row][temp]=1;
            col++;
            if(cols == col)
            {
                row++;
                col=0;
            }
        }
        return arr;
    }
    public int maxnumber(File file) throws FileNotFoundException 
    {
        int max,temp=0;Scanner sc = new Scanner(file);
        max=sc.nextInt();
        while(sc.hasNextInt())
        {
            temp=sc.nextInt();
            if(temp > max)  max=temp;
        }
        return max;
    }
     public int [][] smallerarray(int arr[][],int cols) throws FileNotFoundException
    {
        CAProject obj= new CAProject();int row=0, col=0;
        if(cols > 1)
        {
            for(int i=0;i<cols;i++)
            {
                row=arr.length;col=arr[0].length;
                arr=obj.removeholes(arr,row,col);
            }
        }
        return arr;
    }
    public int [][] removeholes(int arr[][],int row,int col) throws FileNotFoundException
    {
        Boolean allzero=true;int temp_index=0,newarr[][]=null;
        for(int i=1;i<col;i++)
        {
            allzero=true;
            for(int j=1;j<row;j++)
            {
                if(arr[j][i] == 1)
                {
                    temp_index=i+1;
                    allzero=false;
                    break;
                }
            }
            if(allzero)
            {
                newarr= new int [row][col-1];
                newarr=copy(arr,newarr,temp_index,row,col);
                break;
            }
        }
        if(allzero)
        {
            return newarr;
        }
        else
        {
            return arr;
        }
    } 
    public int [][] copy(int arr[][],int newarr[][],int not_col,int row,int col)
    {
        int newarrayrow=0,newarraycol=0;
        for(int i=0;i<col;i++)
        {
            if(i != not_col)
            {
                for(int j=0;j<row;j++)
                {
                    newarr[newarrayrow][newarraycol]=arr[j][i];
                    newarrayrow++;
                }
                newarraycol++;
            }
            newarrayrow=0;
        }
        return newarr;
    }
    public int counting(int arr[][],int col)
    {
        double count=0, percent=0;int temp=0;
        for(int i=1;i<arr.length;i++)
        {
            if(arr[i][col] == 1)
                count++;
        }
        percent=(count/((arr.length)-1));
        percent=percent*100;
        temp=(int) percent;
        return temp;
    }
    //look into this function
    public int [][] newdata(int arr[][],int percent)
    {
        int temp_percent=0;int newarr[][],math=0,ogarraylen=arr[0].length;
        for(int i=1;i<ogarraylen;i++)
        {
            temp_percent=counting(arr,i-math);
            if(temp_percent < percent)
            {
                newarr=new int [arr.length][((arr[0].length)-1)];
                arr=copy(arr,newarr,i-math,arr.length,arr[0].length);
                math++;
            } 
        }
        return arr;
    }
    public void outputnewdata(int arr[][]) throws IOException
    {
        BufferedWriter out = new BufferedWriter(new FileWriter("newdata.txt"));String zero="0";
        for(int i=1;i<arr.length;i++)
        {
            for(int j=1;j<arr[0].length;j++)
            {
                if(arr[i][j] == 1)
                {
                    int data=arr[0][j];String strdata=Integer.toString(data);
                    out.write(strdata+' ');
                }
                else
                {
                    out.write(zero+' ');
                }
            }
            out.newLine();
        }
        out.close();
    }
    public int [][] forsortingarray(int col) throws FileNotFoundException
    {
        File file = new File("newdata.txt");int arr[][],start=0,index=0,indexarr=0,intx;String temp,ints;char sub;
        int num_line=counting(file);
        arr = new int [num_line][col];
        Scanner sc = new Scanner(file);
        for(int i=0;i<arr.length;i++)
        {
            temp=sc.nextLine();
            while(true)
            {
                sub=temp.charAt(index);
                if(sub == ' ')
                {
                    ints=temp.substring(start, index);intx=Integer.parseInt(ints);
                    if(intx  != 0)
                    {
                        arr[i][indexarr]=intx;
                        indexarr++;
                    }
                    start=index+1;
                }    
                index++;
                if(index == (temp.length())-1) 
                {
                    ints=temp.substring(start, index);intx=Integer.parseInt(ints);
                    arr[i][indexarr]=intx;
                    indexarr=0;index=0;
                    start=0;break;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) throws FileNotFoundException, IOException 
    {
        File file = new File("D:\\University\\Semester 5\\Projects\\CA\\test.txt"); int arr[][],newdata[][];
        CAProject obj= new CAProject();int percent=0;
        Scanner scan= new Scanner(System.in);
        System.out.println("Input Percentage to create new data");
        percent=scan.nextInt();
        int max_num=obj.maxnumber(file);
        int line_count=obj.counting(file);
        int column=obj.columns(file);
        arr = new int[line_count+1][max_num+1];
        arr=obj.fillarray(arr,file,column,max_num);
        arr=obj.smallerarray(arr,max_num);
        arr=obj.newdata(arr, percent);
        obj.outputnewdata(arr);
        newdata=obj.forsortingarray((arr[0].length)-1);
    }
}
