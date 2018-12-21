import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/********************************************************************
Written by: Kelly Xavier, Pd 1

ASSIGNMENT:  To write a program that uses your BST class to store
instances of the Song class.  
The song names will be read from a data file (you should ask for the 
name of the file).
Each TreeNode will contain a word from a song name and EVERY song in 
the data file that contains that word.  
If a word is in a song title more than once the title should only be
listed once.
You should print your tree in a TreePainter window and using an inorder
traversal.
After you close the JOP window you should print a menu that gives the
user a choice of searching, adding, deleting, printing or exiting.
- Searching: ask the user to input a word to search for.  If the word 
is found you should print the number of songs that contain that word
and every song title that contains that word. Otherwise you should 
print a polite message saying the word wasn't found.
Upper or lower case shouldn't matter.  A == a is true!
I should not have ANY trouble reading your JOP windows!
- Adding:  let the user input a song title and add the words & the
title to the tree.
- Deleting:  let the user input a song title and delete the song 
from the tree.
- Printing:  prints your tree using TreePainter & an inorder traversal.
- Exit: ends the program.

You should have fairly short methods and no duplicate code.  If 
you are doing the same thing in several methods you should put that
code in a separate method and call it.

HAND IN:  Make a jar file  and print your SongTree class.
Make sure to include all 4 classes - TreeNode, BST Class, Song & SongTree

 ********************************************************************/
public class SongTree
{
	public static void changeJOP()
	{
		UIManager.put("Label.font", new FontUIResource 
				(new Font("Tempus Sans ITC", Font.BOLD, 20)));
		UIManager.put("OptionPane.messageForeground",Color.green);
		UIManager.put("TextField.background", Color.black);
		UIManager.put("TextField.font", new FontUIResource
				(new Font("Dialog", Font.ITALIC, 24)));
		UIManager.put("TextField.foreground", Color.green);
		UIManager.put("Panel.background",Color.black);
		UIManager.put("OptionPane.background", Color.black);
		UIManager.put("Button.background",new Color(0,0,255));
		UIManager.put("Button.foreground", new Color(255,255,255));
		UIManager.put("Button.font", new FontUIResource	
				(new Font("Tempus Sans ITC", Font.BOLD, 14)));
	}

	public static int menu()
	{
		String [] options = { "Add", "Delete", "Search", "Print", 
		"Exit"};
		int response = JOptionPane.showOptionDialog(null,
				"What do you want to do?", null, 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null,
				options, "Add");
		return response;
	}

	public static void fillSongTitles(String fileName,
			ArrayList<String>songTitles) 
	{
		try
		{ 
			Scanner inFile = new Scanner(new File(fileName)); 

			while (inFile.hasNext()) 
			{ 
				String title=inFile.nextLine();
				songTitles.add(title);
			}
			inFile.close();
		}
		catch (Exception e)
		{ 
			e.printStackTrace();
		}
	}

	public static void fillTree(BSTClass <Song> tree, 
			ArrayList<String>songTitles)
	{
		String line;
		String [] data;
		//ArrayList<String>songList=new ArrayList<String>();
		String word = "";
		Song s = null;

		for(int i =0; i<songTitles.size();i++)
		{ 
			line = songTitles.get(i);
			data = line.split(" ");

			for(int j = 0; j < data.length; j++)
			{
				s = new Song(data[j], new ArrayList<String>());
				if(tree.contains(s)==null)
				{
					tree.add(s);
				}
				else
				{
					s.setAl(tree.contains(s).getAl());
				}
				if (data.length==1)
				{
					word = data[j];
				}
				else if(j==0)
				{
					word = data[j]+" ";
				}
				else if (j==data.length-1)
				{
					word = " "+data[j];
				}
				else
				{
					word = " " + data[j] +" ";
				}
				if((songTitles.get(i)).contains(word))
				{
					s.setSong(songTitles.get(i));
				}
				//songList=new ArrayList<String>();
			}
		}
	}
	
	public static void print(BSTClass <Song> tree)
	{
		String traversals = tree.printInOrder();
		JOptionPane.showMessageDialog(null, traversals);
	}
	
	public static void search(BSTClass <Song> tree)
	{
		String word = JOptionPane.showInputDialog(null, 
				"What word would you like to search for?");
		Song s = new Song(word, new ArrayList<String>());
		if (tree.contains(s)==null)
		{
			JOptionPane.showMessageDialog(null, "Sorry we did not "
					+ "find "+ word + " in the tree");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "We found "+ word 
					+ " in " + tree.contains(s).getAl().size() 
					+ " songs: " + tree.contains(s).getAl());
		}
	}

	public static void add(BSTClass <Song> tree, 
			ArrayList<String>songTitles)
	{
		String newSongTitle = JOptionPane.showInputDialog(null, 
				"What song title would you like to add");
		for(int i = 0; i < songTitles.size(); i++)
		{
			if(newSongTitle.compareToIgnoreCase(songTitles.get(i))==0)
			{
				newSongTitle = songTitles.get(i);
			}
		}
		if(songTitles.contains(newSongTitle))
		{
			JOptionPane.showMessageDialog(null, "Sorry "+ newSongTitle 
					+ " is already in the tree");
			return;
		}
		songTitles.add(newSongTitle);
		fillTree(tree, songTitles);
	}

	public static void del(BSTClass <Song> tree, 
			ArrayList<String>songTitles)
	{
		String remSongTitle = JOptionPane.showInputDialog(null, 
				"What song title would you like to delete");
		String line= remSongTitle;
		String [] data = line.split(" ");
		ArrayList<String>songList=new ArrayList<String>();
		Song s = null;
		for(int i = 0; i < songTitles.size(); i++)
		{
			if(remSongTitle.compareToIgnoreCase(songTitles.get(i))==0)
			{
				remSongTitle = songTitles.get(i);
			}
		}

		if(!(songTitles.contains(remSongTitle)))
		{
			JOptionPane.showMessageDialog(null, "Sorry we did not "
					+ "find "+ remSongTitle + " in the tree");
			return;
		}
		for(int j = 0; j < data.length; j++)
		{
			s = new Song(data[j], songList);
			s.setAl(tree.contains(s).getAl());
			if(s.getAl().size()==1)
			{
				tree.delete(s);
			}
			else
			{
				s.getAl().remove(remSongTitle);
				songTitles.remove(remSongTitle);
			}
			songList=new ArrayList<String>();
		}
	}

	public static void main (String [] args)
	{
		changeJOP();
		BSTClass <Song> tree = new BSTClass <Song>();
		ArrayList<String>songTitles = new ArrayList<String>();
		String fileName = "songss.txt";
		fillSongTitles(fileName, songTitles);
		fillTree(tree, songTitles);
		int choice = menu();
		while(choice != 4)
		{
			switch (choice)
			{
			case 0:
				add(tree, songTitles);
				choice = menu();
				break;
			case 1:
				del(tree, songTitles);
				choice=menu();
				break;
			case 2:
				search(tree);
				choice=menu();
				break;
			case 3:
				print(tree);
				choice = menu();
				break;
			}
		}
		tree.exit();
	}
}