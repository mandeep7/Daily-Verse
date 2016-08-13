package com.mandy.mic.dailyverse;

import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Math;

public class mainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.main_screen2);


        Button button1=(Button)findViewById(R.id.button1);
        final TextView textView=(TextView)findViewById(R.id.book);
        final TextView textView2=(TextView)findViewById(R.id.verse);
        //bookdetails b2=getbook();



        /*
         #testing purpose
         String string1[]=getbook();//first choose Old testament or New testment through a random number
        String string2=getChapter(string1[0]);//fetch the content of bo.txt or bi.text
        String bookfilename=getFilename(string1[0],string1[2]);

        String temp2=getBookName(bookfilename);
        temp2=refineBookname(temp2);
        int HIGH=getHighest(bookfilename.toLowerCase());




        String[] verse=getVerse(bookfilename.toLowerCase(),HIGH);

        textView.setText(string1[1]+"\n"+temp2+"\n"+"Verse"+verse[0]);
       textView2.setText(verse[1]);
        //textView2.setText(temp);*/




        button1.setOnClickListener(
                new Button.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        String string1[]=getbook();//first choose Old testament or New testment through a random number
                        String string2=getChapter(string1[0]);//fetch the content of bo.txt or bi.text
                        String bookfilename=getFilename(string1[0],string1[2]);

                        String temp2=getBookName(bookfilename);
                        temp2=refineBookname(temp2);
                        int HIGH=getHighest(bookfilename.toLowerCase());




                        String[] verse=getVerse(bookfilename.toLowerCase(),HIGH);
                        String temp=temp2+":"+new Integer(HIGH).toString()+" "+verse[1];

                        textView.setText(string1[1]+"\n"+temp2+"\n"+"Verse:"+verse[0]);
                        textView2.setText(verse[1]);



                    }
                }
        );
    }


    //-----------------------------------------------------------------------------------------
    //to get the content of testament file i.e bi.txt or bo.txt which contain their books
    //-----------------------------------------------------------------------------------------

    public String[] getbook()
    {

        String[] booksdetails =new String[3];

        int i;
        i = (int)((int)(Math.random() * 10) * System.currentTimeMillis() % 2);//generate 1 or 0
        if(i==0){booksdetails[0] ="bo.txt" ;booksdetails[1] = "Old Testament";booksdetails[2] ="old";}
        if(i==1){booksdetails[0]="bi.txt";booksdetails[1] = "New Testament";booksdetails[2] ="new";}

        return booksdetails;
    }

    //-----------------------------------------------------------------------------------------
    //to get the content of testament file i.e bi.txt or bo.txt which contain their books
    //-----------------------------------------------------------------------------------------



    public String getChapter(String testamentfilename)
    {
        String mLine="";String line="";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(testamentfilename)));

            // do reading, usually loop until end of file reading

            while ((mLine = reader.readLine()) != null) {
                //process line
                line=line+mLine;
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return line;
    }


    //-----------------------------------------------------------------------------------------
    //generate another random number and according to that select a book of Bible .
    //-----------------------------------------------------------------------------------------
    public String getFilename(String testamentfile,String testament)
    {
        //System.out.println(2221+(int)(1000*Math.random())*System.currentTimeMillis()%260);//# for debugging purpose
        //String testament="old";
        Integer x=0;String filename="";
        //for(int z=0;z<100;z++)# for debugging purpose
        String path="";
        if(testament=="old"){path="bo.txt";
            x=(int)(2221+(int)(1000*Math.random())*System.currentTimeMillis()%929);}
        if(testament=="new"){path="bi.txt";
            x=(int)(2221+(int)(1000*Math.random())*System.currentTimeMillis()%260);}

        //System.out.println(x);# for debugging purpose
        try{
            // InputStream in = new FileInputStream(getAssets().open(getbook()[0]));
            // BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String mLine="";String line="";
            BufferedReader reader = null;
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(path)));
            StringBuilder out = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
            int y=out.toString().indexOf(x.toString());
            //char[] temparr=new char[40];
            filename="";
            for(int i=y+4;out.charAt(i)!=']';i++)
            {filename=filename+out.charAt(i);}
            //System.out.println(filename);     # for debugging purpose
            //System.out.println(out.substring(y, y+4));   # for debugging purpose
            reader.close();
        }catch(Exception e){}

        return filename;
    }

//-------------------------------------------------------------------------------------------
//function to get the highest line number in the book passed as argument
//--------------------------------------------------------------------------------------------


    public  int getHighest(String filename)
    { Integer x;int highest=0;String temp="";

        // filename="_exodus23.txt";
        //String path=filename;

        BufferedReader reader = null;
        //System.out.println(x);
        try{
            // InputStream in = new FileInputStream(new File(path));
            // BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String mLine="";String line="";
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(filename)));

            StringBuilder out = new StringBuilder();
            while ((line = reader.readLine()) != null) {int tempno=0;temp="";
                out.append(line);
                for(int i=0;line.charAt(i)!=' ';i++)
                {temp=temp+line.charAt(i);}

                tempno = Integer.parseInt(temp);
                if(tempno>highest)highest=tempno;

            }

            reader.close();
        }catch(Exception e){}
        return highest;
    }
//-----------------------------------------------------------------------------------------
//funtion to generate a random verse
// -----------------------------------------------------------------------------------------

    public String[] getVerse(String filename,int HIGH)
    {
        String[] versedata=new String[2];int tempno;//HIGH=37;
        // filename="_Genesis30.txt";//# for debugging purpose
        Integer x=0;String Verse="";
        //System.out.print(HIGH);//# for debugging purpose
        // for(int z=0;z<100;z++) {   //# for debugging purpose

        //String  path="_2kings18.txt";

        x=(int)((int)(1000*Math.random())*System.currentTimeMillis()%(HIGH)+1);
        //System.out.print(" "+x);//# for debugging purpose

        //System.out.println(x);# for debugging purpose
        try{


            String mLine="";String line="";
            BufferedReader reader = null;
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(filename)));
            StringBuilder out = new StringBuilder();
            while ((line = reader.readLine()) != null) {String temp="";
                out.append(line);
                for(int i=0;line.charAt(i)!=' ';i++)
                {temp=temp+line.charAt(i);}

                tempno = Integer.parseInt(temp);

                //System.out.println(" "+temp);//# for debugging purpose
                if(tempno==x.intValue()){
                    //System.out.print(" "+tempno);//# for debugging purpose
                    line=line.replaceAll("&lt;","(");
                    line=line.replaceAll("&gt;",")");
                    Verse=line;

                    break;}

            }
            //System.out.println(filename);     # for debugging purpose
            //System.out.println(out.substring(y, y+4));   # for debugging purpose
            reader.close();
        }catch(Exception e){}

        //for removing the verse numbers present at the beginning of the string
        for(int i=0;Verse.charAt(i)!=' ';i++)
        {
            Verse=Verse.replaceFirst(x.toString(),"");
        }


        versedata[0]=x.toString();
        versedata[1]=Verse;
        return versedata;

    }


//-----------------------------------------------------------------------------------------
//function to remove '_' and '.txt' to get the book name from filename to the book
// -----------------------------------------------------------------------------------------


    public String getBookName(String bookname)
    {
        bookname=bookname.replaceAll("_", "");
        bookname=bookname.replaceAll(".txt", "");
        return bookname;
    }

    //------------------------------------------------------------------------------------------
//Functio to put relevent spaces in the Bookname
    //-------------------------------------------------------------------------------------------


    public String refineBookname(String bookname){
        String temp="";bookname=bookname+" ";
        for(int i=0;i<bookname.length()-1;i++)
        {
            if(
                    ((bookname.charAt(i)>=48 &&bookname.charAt(i)<=57)&&
                            ((bookname.charAt(i+1)>=65 &&bookname.charAt(i+1)<=90)
                                    ||(bookname.charAt(i+1)>=97 &&bookname.charAt(i+1)<=122)))
                            ||
                            ((bookname.charAt(i+1)>=48 &&bookname.charAt(i+1)<=57)&&
                                    ((bookname.charAt(i)>=65 &&bookname.charAt(i)<=90)
                                            ||(bookname.charAt(i)>=97 &&bookname.charAt(i)<=122)))
                    )
            {
                temp=temp+bookname.charAt(i);
                temp=temp+" ";
            }


            else temp=temp+bookname.charAt(i);

        }
        //System.out.print(temp);//# for debugging purpose

        return temp;
    }


    //-------------------------------------------------------------------------------------
//END OF PROGRAM
    //-------------------------------------------------------------------------------------




}
