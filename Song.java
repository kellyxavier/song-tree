import java.util.ArrayList;
/********************************************************************
 * Written by: Kelly Xavier, Pd 1
 * 
 * ASSIGNMENT:  To write a Song class that keeps track of a
 * word and the names of the songs that contain the word.
 * Your class should implement Comparable and have the following:
 * Two instance attributes:
 * 		the word
 * 		an ArrayList of songs that contain that word
 * 
 * Three constructors:
 * 		a default constructor
 * 		a parameter constructor that takes a word and a song title.
 * 		a parameter constructor that takes a word and an AL
 * 		
 * Getters & setters for both attributes
 * A setSong method that lets the user send in a song and add it
 * 		to the AL if it's not already there
 * A compareTo method that compares the words
 * An equals method that uses compareTo
 * A toString method that prints the word and it's AL of songs
 *
 *******************************************************************/
public class Song implements Comparable <Song>
{
	private String word;
	private ArrayList<String> al;

	public Song()
	{
		word = "";
		al = new ArrayList<String>();
	}
	public Song (String w, String title)
	{
		word = w;
		al = new ArrayList<String>();
		setSong(title);
	}
	public Song(String w, ArrayList<String> a)
	{
		word = w;
		al = a;
	}
	public String getWord() 
	{
		return word;
	}
	public void setWord(String word) 
	{
		this.word = word;
	}
	public void setSong(String title)
	{
		if(!(al.contains(title)))
		{
			al.add(title);
		}
	}
	public ArrayList <String> getAl()
	{
		return al;
	}
	public void setAl(ArrayList <String> al)
	{
		this.al=al;
	}
	public int compareTo(Song sg)
	{
		return (word.compareToIgnoreCase(sg.getWord()));
	}
	public boolean equals(Object o)
	{
		return (compareTo((Song)o)==0);
	}
	public String toString()
	{
		return ("word: " + word + ", list: " + al +"\n");
	}
}