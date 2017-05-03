/*===================================================================================================
 * Assignment 3 - 2D ARRAYS/SORTING/SEARCHING ASSIGNMENT
 * @Andrew Pai
 * @June 10, 2015
 * @Java, V1.6
 * ==================================================================================================
 * -This program generates random 4-word sentences based off of certain rules:
 * -	1. article (i.e The, It, A, etc.)  + noun + verb + adverb
 * -	2. name + verb + adjective + noun
 * -	3. name + verb + article + noun
 * -This program uses words that are imported from the text file, Words.txt
 * -The first column of words contain a minimum of 16 names or articles
 * -The second column of words contain a minimum of 16 nouns or verbs(past tense)
 * -The third column of words contain a minimum of 16 verbs, adjectives, or articles
 * -The fourth column of words contain a minimum of 16 adverbs or nouns
 * The user is able to modify any of these words as long as there is a minimum of 2 types of each needed type in each column
 * The user can also add words
 * This program can sort the words alphabetically or by word length(A-Z, Z-A, longest to shortest, shortest to longest)
 * Locate a word within the table to modify it
 * Generate as many sentences as they want
 * Calculate the occurrences of each word within the array
 * This program can also output the table of words and sentences to a text file
 * Uses bubble and insertion sort
 * 
 * ==================================================================================================
 * Identifiers:
 * 				-Let location represent the coordinate and row location of the word the user has located (type Integer)
 * 				-Let sentence represent the sentences created by the random sentence generator (type String)
 * 				-Let sorted represent the sorted words (type String)
 * 				-Let words represent the words (type String)
 * 				-Let userInput represent the input of the user (type String)
 * 				-Let sentenceOutput represent the object used to output the sentences created to a text file(type PrintWriter)
 * 				-Let Ass3 represent an object of the Assignment3 class (type Assignment3)  
 * 				-Let startTime represent the time the bubble sort started(type long)
 * 				-Let startTime2 represent the time the insertion sort took(type long)
 * 				-Let elapsedTime represent the time the bubble sort started(type long)
 * 				-Let elapsedTIme2represent the time the insertion sort took(type long)
 * 				-Let column represent the column the user wants to target(type Integer)
 *				-Let target represent the word the user wants to target(type String)
 * ===================================================================================================
 */
import java.io.*;//allow access to the coding within the io library 
import java.util.*;//allow access to the coding within the util library 

public class Assignment3 {// Start of Assignment3 class

	public static void main(String[] args) throws IOException,InterruptedException {// Main method header, IOException to get user input through BufferedReader, InterruptedException to interrupt the thread
		PrintWriter sentenceOutput = new PrintWriter(new FileWriter("Sentence.txt"));// Declaration and instantiation of a PrintWriter class to create the file, Sentence.txt, and write to it
		Assignment3 Ass3 = new Assignment3();// Declaration and instantiation of the Assignment3 class
		String userInput, target;// Declare a String object data type named userInput and target
		String words[][][];//Declare a String array object data type named words
		String sorted[][][];//Declare a String array object data type named sorted
		String sentence[];//Declare a String array object data type named sentence
		ArrayList<Integer> location = new ArrayList<Integer>();//Declare an Integer named location
		long startTime, startTime2, elapsedTime, elapsedTime2;// Declare a long primitive data type named starTime, startTime, elapsedTIme, elapsedTime2
		int column;// Declare a integer primitive data type named column

		Ass3.lineCreater(63, "-");//Calls method lineCreater to output lines
		System.out.println("	|Welcome to Andrew's Random Sentence Generator|");
		System.out.println("		    |The Best in the West!|");
		Ass3.lineCreater(63, "-");//Calls method lineCreater to output lines
		System.out.println("This innovative program can create amazing sentences of different formats!");
		System.out.println("1. article + noun + verb + adverb");
		System.out.println("2. name + verb + adjective + noun"); 
		System.out.println("3. name + verb + article + noun");
		System.out.println("You can also sort and modify the words as much as you want!");
		while (true) {//While loop, that loops until the user wants to stop using the program
				words = Ass3.Input("Words.txt");// Gets words from the text file and fills the array words
				sorted = words.clone();//Copy the array words into the array sorted
				Ass3.lineCreater(60, "~");// Calls lineCreater method to create lines
				System.out.println("What would you like to do?");
				System.out.println("1. Generate sentences");
				System.out.println("2. View the table of words");
				System.out.println("3. Sort the table");
				System.out.println("4. Locate and modify a word");
				System.out.println("5. Add a word");
				System.out.println("6. Export the words to a text file");
				System.out.println("7. To see the occurances of each word");
				System.out.println("Enter any other number to save the current words and exit the program");
				userInput = Ass3.getInput(1);// Calls getInput method to get the users input(Numbers only) and assigns it to the String userInput
				Ass3.lineCreater(60, "_");
				if (userInput.equals("1")) {//If statement to see if the user wants to create sentences
					System.out.println("How many sentences would you like to create?");
					userInput = Ass3.getInput(1);//Calls getInput method to get users input
					sentence = Ass3.sentenceCreater(words,Integer.parseInt(userInput));//Calls sentenceCreater method to generate random sentences
					for (int x = 0; x < sentence.length; x++) {//For statement to output the generated sentences to a text file and console
						System.out.println(x + 1 + ". " + sentence[x]);//Prints the generated sentences
						sentenceOutput.println(x + 1 + ". " + sentence[x]);//Outputs the generated sentences to the text file Sentence.txt
					}//End of for statement
					Ass3.lineCreater(60, "~");// Calls lineCreater method to create lines
					System.out.println("The generated sentences have also been outputed to a text file named Sentence.txt");
					Ass3.lineCreater(60, "~");// Calls lineCreater method to create lines
				} else if (userInput.equals("2")) {//Else if statement to see if the user wants to view the words in tabular form
					System.out.println("Would you like to view the original list or the sorted list?");
					System.out.println("1. Original list");
					System.out.println("2. Sorted list");
					System.out.println("Enter anything else to go back to the menu");
					userInput = Ass3.getInput(1);//Calls getInput method to get users input
					Ass3.lineCreater(60, "~");
					if (userInput.equals("1"))//If statement to check if the user wants to view the original words
						Ass3.tableCreater(words);//Calls tableCreater method to output the regular words in tabular form
					if (userInput.equals("2"))//If statement to check if the user wants to view the sorted words
						Ass3.tableCreater(sorted);//Calls tableCreater method to output the sorted words in tabular form
				} else if (userInput.equals("3")) {//Else if statement to see if the user wants to sort the words
					sorted = words.clone();//Copies the array words to the array sorted
					System.out.println("How would you like to sort the table?");
					System.out.println("1. Alphabetically");
					System.out.println("2. Length");
					userInput = Ass3.getInput(1);//Calls getInput method to get users input
					if (userInput.equals("1")) {//If statement to see how the user wants to sort
						System.out.println("Which column do you want to sort?");
						column = Integer.parseInt(Ass3.getInput(1));//Calls getInput method to get users input and assigns to column
						if (column < 5 && column > 0) {//If statement to make sure the column the user inputed is within range
							System.out.println("Would you like to sort from A-Z or Z-A?");
							System.out.println("1. A-Z");
							System.out.println("2. Z-A");
							userInput = Ass3.getInput(1);//Calls getInput method to get users input
							if (userInput.equals("1")) {//If statement to check if the user wants to sort from A-Z
								startTime = System.nanoTime();//Sets the system time to startTime
								sorted = Ass3.bubbleSort(sorted, column, 0);//Calls bubbleSort method to sort the array sorted
								elapsedTime = System.nanoTime() - startTime;//Subtracts current system time by startTime to get how long it took for the sort
								startTime2 = System.nanoTime();//Sets the system time to startTime2
								sorted = Ass3.insertionSort(sorted, column, 0);//Calls insertionSort method to sort the array sorted
								elapsedTime2 = System.nanoTime() - startTime2;//Subtracts current system time by startTime2 to get how long it took for the sort
								System.out.println("Using bubble sort to sort the words took "+ elapsedTime + " nanoseconds to sort.");
								System.out.println("Using insertion sort to sort the words took "+ elapsedTime2+ " nanoseconds to sort.");
							}//End of if statement
							if (userInput.equals("2")) {//If statement to check if the user wants to sort from Z-A
								startTime = System.nanoTime();//Sets the system time to startTime
								sorted = Ass3.bubbleSort(sorted, column, 1);//Calls bubbleSort method to sort the array sorted
								elapsedTime = System.nanoTime() - startTime;//Subtracts current system time by startTime to get how long it took for the sort
								startTime2 = System.nanoTime();//Sets the system time to startTime2
								sorted = Ass3.insertionSort(sorted, column, 1);//Calls insertionSort method to sort the array sorted
								elapsedTime2 = System.nanoTime() - startTime2;//Subtracts current system time by startTime2 to get how long it took for the sort
								System.out.println("Using bubble sort to sort the words took "+ elapsedTime+ " nanoseconds to sort.");
								System.out.println("Using insertion sort to sort the words took "+ elapsedTime2+ " nanoseconds to sort.");
							}//End of if statement
						} else//Else statement if the user inputs an invalid column
							System.out.println("Sorry, that is not a valid column");
						userInput = "0";//Sets userInput to 0
					}//End of if statement
					if (userInput.equals("2")) {
						System.out.println("Which column do you want to sort?");
						column = Integer.parseInt(Ass3.getInput(1));//Calls getInput method to get users input
						if (column < 5 && column > 0) {//If statement to make sure the column the user inputed is within range
							System.out.println("Would you like to sort from:");
							System.out.println("1. Shortest to Longest ");
							System.out.println("2. Longest to Shortest");
							System.out.println("Enter any other number to go back to the menu");
							userInput = Ass3.getInput(1);//Calls getInput method to get users input
							if (userInput.equals("1")) {//If statement to check if the user wants to sort from shortest to longest
								startTime = System.nanoTime();//Sets the system time to startTime
								sorted = Ass3.bubbleSort(sorted, column, 2);//Calls bubbleSort method to sort the array sorted
								elapsedTime = System.nanoTime() - startTime;//Subtracts current system time by startTime to get how long it took for the sort
								startTime2 = System.nanoTime();//Sets the system time to startTime2
								sorted = Ass3.insertionSort(sorted, column, 2);//Calls insertionSort method to sort the array sorted
								elapsedTime2 = System.nanoTime() - startTime2;//Subtracts current system time by startTime2 to get how long it took for the sort
								System.out.println("Using bubble sort to sort the words took "+ elapsedTime+ " nanoseconds to sort.");
								System.out.println("Using insertion sort to sort the words took "+ elapsedTime2+ " nanoseconds to sort.");
							}//End of if statement
							if (userInput.equals("2")) {//IF statement to check if user wants to sort from longest to shortest
								startTime = System.nanoTime();//Sets the system time to startTime
								sorted = Ass3.bubbleSort(sorted, column, 3);//Calls bubbleSort method to sort the array sorted
								elapsedTime = System.nanoTime() - startTime;//Subtracts current system time by startTime to get how long it took for the sort
								startTime2 = System.nanoTime();//Sets the system time to startTime2
								sorted = Ass3.insertionSort(sorted, column, 3);//Calls insertionSort method to sort the array sorted
								elapsedTime2 = System.nanoTime() - startTime2;//Subtracts current system time by startTime2 to get how long it took for the sort
								System.out.println("Using bubble sort to sort the words took "+ elapsedTime+ " nanoseconds to sort.");
								System.out.println("Using insertion sort to sort the words took "+ elapsedTime2+ " nanoseconds to sort.");
							}//End of if statement
						} else//Else to check if the user inputed a valid column
							System.out.println("Sorry, that is not a valid column");
					}//End of if statement
				} else if (userInput.equals("4")) {// Else if statement to see if the user wants to locate a word and modify it
					location.clear();//Clears the values stored in the array list location
					System.out.println("What word would you like to locate?");
					target = Ass3.getInput(0);//Calls getInput method to get users input for what word they want to locate
					location = Ass3.wordLocator(target, words);//Calls wordLocator method to display the location of the target word
					if (location.size() > 0) {//If statement to see if there is a location for the target word
						System.out.println("Enter 1 if you would like to modify the word");
						System.out.println("Enter anything else to go back to the menu");
						userInput = Ass3.getInput(1);//Calls getInput method to get users input
						if (userInput.equals("1")) {//If statement to see if the user wants to modify the target word
							words = Ass3.wordReplacer(target, words, location);//Calls wordReplacer method to replace the target word
							Ass3.save(words);//Outputs the words in the array words to a text file
						}//End of if statement
					}//End of if statement

				} else if (userInput.equals("5")) {//Else if statement to see if user wants to add a word to the list
					System.out.println("Which column would you like to add a new word to?");
					column = Integer.parseInt(Ass3.getInput(1));//Calls getInput method to get users input
					if (column < 5 && column > 0) {//Check if column is within range
						System.out.println("What new word would you like to add?");
						words = Ass3.wordAdder(words, Ass3.getInput(0), column);//Calls getInput method to get users input
						Ass3.save(words);//Outputs the words in the array words to a text file
					}//End of if statement
				} else if (userInput.equals("6")) {//Else if statement to see if the user wants to export the words in tabular form
					System.out.println("Would you like to export the original list or the sorted list?");
					System.out.println("1. Original list");
					System.out.println("2. Sorted List");
					System.out.println("Enter any other number to go back to the menu");
					userInput = Ass3.getInput(1);//Calls getInput method to get users input
					if (userInput.equals("1")) {//If to check if user wants to output original list
						System.out.println("What is the name of the text file to export to?");
						Ass3.output(words, Ass3.getInput(0));//Calls getInput method to get users input
					}//End of if
					if (userInput.equals("2")) {//If to check if user wants to output sorted list
						System.out.println("What is the name of the textfile to export to?");
						Ass3.output(sorted, Ass3.getInput(0));//Calls getInput method to get users input
					}//End of if
				} else if (userInput.equals("7")) {//else if to see if user wants to see occurrences of each word
					Ass3.lineCreater(60, "-=");//Calls lineCreater method to output line
					Ass3.count(words);//Calls count method to output occurrences of each word
				} else {//Else if user inputs anything else
					Ass3.save(words);//Outputs the words in the array words to a text file
					System.out.println("Thank you for using this program!");
					System.out.println("Hope you have a nice day :D");
					break;//Exits the while loop
				}//End of else statement
		}//End of while loop
		sentenceOutput.close();//Closes the file sentenceOutput
	}//End of main method
	/**getInput method 
	 * 
	 * This functional method gets the users input for the values
	 * It exits the program if the user inputs '!quit'
	 * 
	 * -Let br represent the object used to get user input (type BufferedReader)
	 * -Let userInput represent the input of the user (Type string)
	 * 
	 * @para userInput=Whether the user is inputing a letter or a number
	 * 
	 * @return (String) Enter a word or to choose an option
	 * 
	 * @throws IOException
	 */
	public String getInput(int type) throws IOException {//getInput method header
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));// Declaration and instantiation of a BufferedReader object
		String userInput;// Declare a String object data type named userInput
		while (true) {//While loop
			userInput = br.readLine();//Assigns user input to variable userInput
			if (userInput.length() > 15)//Checks to see if users input is too long
				System.out.println("Sorry, the word you have entered is too long");
			else if (userInput.equalsIgnoreCase("!quit")){//Checks to see if user wishes to quit the program
				System.out.println("Thank you for using this program!");
				System.exit(0);//Exits the program
			}else if (type == 0) {//Else if for when user can only enter letters
				if (userInput.matches("[a-zA-Z]+"))//Checks if the users input contains only letters
					return userInput;//returns the word the user inputed
				else//Else statement if user inputs a non-letter
					System.out.println("Please enter a valid input.");
			} else {
				if (userInput.matches("[0-9]+"))// Checks if the input of the user contains only numbers
					return userInput;//Returns the number the user inputed
				else//Else statement if user inputs a non-number
					System.out.println("Please enter a valid input.");
			}//End of else statement
			System.out.println("(Enter '!quit' to exit the program)");
		}//End of while loop
	}//End of main method

	/**input method 
	 * 
	 * This functional method inputs words from a text file
	 * 
	 * -Let wordInput represent the object used to get the words from a text file (type BufferedReader)
	 * -Let words represent the words inputed from the text file(type String)
	 * 
	 * @para file=What file to input from
	 * 
	 * @return (String) The inputed words to use
	 * 
	 * @throws IOException
	 */
	public String[][][] Input(String file) throws IOException {//input method header
		BufferedReader wordInput = new BufferedReader(new FileReader(file));// Declaration and instantiation of a BufferedReader object
		String words[][][] = new String[Integer.parseInt(wordInput.readLine())][Integer.parseInt(wordInput.readLine())][3];//Declare and instantiate the array words
		for (int x = 0; x < words.length; x++) {//For loop that loops through the columns
			for (int y = 0; y < words[x].length; y++) {//For loop that loops through each row
				for (int z = 0; z < words[x][y].length; z++) {//For loop that loops through the word, word type, and plural form
					words[x][y][z] = wordInput.readLine();//Gets value from text file and assigns to array words
				}//End of for statement
			}//End of for statement
		}//End of for statement
		wordInput.close();//Close text file
		return words;//Return the array
	}//End of main method
	/**output method 
	 * 
	 * This procedural method outputs words in the array in tabular form to a text file
	 * 
	 * -Let wordOutput represent the object used to output the words to a text file (type BufferedReader)
	 * 
	 * @para 	outputWords=What words to output in tabular form
	 * 			file=What file to output to
	 * 
	 * @throws IOException
	 */
	public void output(String outputWords[][][], String file)throws IOException {
		PrintWriter wordOutput = new PrintWriter(new FileWriter(file + ".txt"));
		if (!file.equals("Words")) {
			for (int y = 0; y < outputWords[0].length; y++) {
				for (int x = 0; x < outputWords.length; x++) {
					// Prints words in tabular format
					wordOutput.printf("|%-15s", outputWords[x][y][0]);
				}
				wordOutput.println();
			}
			wordOutput.close();
		} else
			System.out.println("Sorry that file name, is not available. ");
	}
	/**save method 
	 * 
	 * This procedural method saves the words to a text file
	 * That can be read to fill the array
	 * 
	 * -Let wordOutput represent the object used to output the words to a text file (type BufferedReader)
	 * 
	 * @para 	outputWords=What words to output in tabular form
	 * 
	 * @throws IOException
	 */
	public void save(String outputWords[][][]) throws IOException {
		PrintWriter wordOutput = new PrintWriter(new FileWriter("Words.txt"));
		wordOutput.println(outputWords.length);
		wordOutput.println(outputWords[0].length);
		for (int x = 0; x < outputWords.length; x++) {
			for (int y = 0; y < outputWords[x].length; y++) {
				for (int z = 0; z < outputWords[x][y].length; z++) {
					wordOutput.println(outputWords[x][y][z]);

				}
			}
		}
		wordOutput.close();
	}

	/**tableCreater method 
	 * 
	 * This procedural method outputs the words in tabular form
	 * 
	 * 
	 * @para 	word=What words to output in tabular form
	 * 
	 */
	public void tableCreater(String word[][][]) {
		for (int y = 0; y < word[0].length; y++) {
			for (int x = 0; x < word.length; x++) {
				// Prints words in tabular format
				System.out.printf("|%-15s", word[x][y][0]);
			}
			System.out.println();
		}
	}

	/**lineCreater method 
	 * 
	 * This procedural method outputs a line 
	 * 
	 * @para 	length=Number of symbols to output
	 * 			type=What symbol to output

	 */
	public void lineCreater(int length, String type) {
		for (int x = 0; x < length; x++)
			System.out.print(type);
		System.out.println();
	}

	/**wordLocator method 
	 * 
	 * This functional method highlights the word the user has targeted and outputs the location of it
	 * 
	 * -Let location represent the row and column of the target word (type Integer)
	 * 
	 * @para 	target=What word to locate
	 * 			word=Where to locate the word
	 * 
	 * @throws InterruptedException
	 * 
	 * @return (Integer) the locations(row and column) of the target word
	 */
	public ArrayList<Integer> wordLocator(String target, String word[][][])throws InterruptedException {
		ArrayList<Integer> location = new ArrayList<Integer>();
		for (int y = 0; y < word[0].length; y++) {
			for (int z = 0; z < word.length; z++) {
				if (word[z][y][0].equalsIgnoreCase(target)) {
					Thread.sleep(5);
					System.err.printf("|%-15s", word[z][y][0]);

					Thread.sleep(5);
					location.add(z);
					location.add(y);
				} else {
					Thread.sleep(5);
					System.out.printf("|%-15s", word[z][y][0]);
				}
			}
			System.out.println();

		}

		if (location.size() < 1)
			System.out.println("Sorry, " + target + " could not be found");
		else {
			System.out.println("The word, " + target
					+ ", is highlighted and located at:");
			for (int x = 0; x < location.size() - 1; x += 2)
				System.out.println("Column: " + (location.get(x) + 1)
						+ " and Row: " + (location.get(x + 1) + 1));
		}
		return (location);

	}
	/**wordReplacer method 
	 * 
	 * This functional method allows user to modify the words in the array
	 * 
	 * -Let newWord what word the user wants to replace the target word with
	 * -Let wordType what word type the inputed word is
	 * 
	 * @para 	target=Word the user wants to locate
	 * 			newWords=The array of words to modify
	 * 			location=The locations of the target words
	 * 
	 * @throws InterruptedException
	 * 
	 * @return (String) the modified word list
	 */
	public String[][][] wordReplacer(String target, String newWords[][][],ArrayList<Integer> location) throws IOException {
		Assignment3 Ass3 = new Assignment3();
		String newWord,  wordType = "";
		int row, column;
		if (location.size() > 2) {
			System.out.println("The word, " + target+ ", is located at locations:");
			for (int x = 0; x < location.size() - 1; x += 2)
				System.out.println("Column: " + (location.get(x) + 1)+ " and Row: " + (location.get(x + 1) + 1));
			while (true) {
				try {
					System.out.println("What column " + target+ " would you like to replace?");
					column = Integer.parseInt(Ass3.getInput(1));//Calls getInput method to get users input
					System.out.println("What row " + target+ " would you like to replace");
					row = Integer.parseInt(Ass3.getInput(1));//Calls getInput method to get users input
					if (!newWords[column - 1][row - 1][0]
							.equalsIgnoreCase(target))
						System.out.println("Sorry, " + target
								+ " is not located there");
					else
						break;
				} catch (InputMismatchException e) {
					System.out.println("Sorry, '" + target
							+ "' is not located there");
				}
			}
		} else {
			System.out.println("Would you like to replace the word, " + target
					+ ", at column: " + location.get(0) + ", and row: "
					+ location.get(1));
			column = location.get(0);
			row = location.get(1);
		}
		while (true) {

			System.out.println("What word would like to replace " + target+ " with?");
			System.out.println("(Maximum of 15 characters");
			newWord = Ass3.getInput(0);//Calls getInput method to get users input
			System.out.println("What type of word is " + newWord + "?");
			System.out.println("1. Name");
			System.out.println("2. Noun");
			System.out.println("3. Verb");
			System.out.println("4. Adjective");
			System.out.println("5. Article(A, the, his, this)");
			System.out.println("6. Adverb");
			wordType = Ass3.getInput(1);//Calls getInput method to get users input
			if (wordType.equals("1"))
				wordType = "N";
			else if (wordType.equals("2"))
				wordType = "n";
			else if (wordType.equals("3"))
				wordType = "v";
			else if (wordType.equals("4"))
				wordType = "dp";
			else if (wordType.equals("5"))
				wordType = "a";
			else if (wordType.equals("6"))
				wordType = "b";
			else
				System.out.println("That is not a valid option.");
			newWords[column][row][0] = newWord;
			newWords[column][row][1] = wordType;
			newWords[column][row][2] = "";
			if (Ass3.checkType(newWords, column, wordType)) {
				if (wordType.equals("n")) {
					System.out
							.println("What is the singular version of the word?");
					newWords[column - 1][row - 1][0] = Ass3.getInput(0);//Calls getInput method to get users input
					System.out.println("What is the plural version of the word?");
					newWords[column - 1][row - 1][2] = Ass3.getInput(0);
				}

				if (wordType.equals("a")) {
					System.out
							.println("Is this the plural version of the word?");
					System.out
							.println("e.g. These is the plural version of this");
					System.out.println("Enter 1 if it is the plural version");
					System.out
							.println("Enter any other number else if it isn't");
					if (Ass3.getInput(1).equals("1"))//Calls getInput method to get users input
						wordType += "p";

					break;
				}
			} else
				System.out
						.println("Sorry, you can't enter that type of word in this location");

		}
		return newWords;
	}
	/**checkType method 
	 * 
	 * This functional method checks to see if the column has at least 2 types of each needed type 
	 * 
	 * -Let newWord what word the user wants to replace the target word with
	 * -Let wordType what word type the inputed word is
	 * 
	 * @para 	target=Word the user wants to locate
	 * 			newWords=The array of words to modify
	 * 			location=The locations of the target words
	 * 
	 * @throws InterruptedException
	 * 
	 * @return (boolean) returns true or false for if the users word type can be added
	 */
	public boolean checkType(String wordCheck[][][], int column, String type) {
		int counter = 0;
		for (int x = 0; x < wordCheck[column - 1].length; x++) {
			if (wordCheck[column - 1][x][1].contains(type))
				counter++;
		}
		if (counter > 1)
			return true;
		return false;
	}
	/**bubbleSort method 
	 * 
	 * This functional method sorts the list of words by length or alphabetically using bubble sort
	 * It sorts a column of the users choice based on how they want to sort the column
	 * 
	 *	-Let Ass3 represent an object of the Assignment3 class (type Assignment3)  
	 *	-Let temp(2,3) temporarily store the values of the array
	 * 
	 * @para 	column=Which column the user wants to sort
	 * 			unsorted=The array of words to sort
	 * 			type=How the user wants to sort
	 * 
	 * @return (String) the sorted version of the list of words
	 */
	public String[][][] bubbleSort(String unsorted[][][], int column, int type) {
		Assignment3 Ass3 = new Assignment3();
		String temp, temp2, temp3;

		for (int x = 0; x < unsorted[column - 1].length; x++) {
			for (int z = 0; z < unsorted[column - 1].length - 1; z++) {
				if (Ass3.compareParameter(unsorted[column - 1][z][0],
						unsorted[column - 1][z + 1][0], type)) {
					for (int y = 0; y < unsorted.length; y++) {
						temp = unsorted[y][z][0];
						temp2 = unsorted[y][z][1];
						temp3 = unsorted[y][z][2];
						unsorted[y][z][0] = unsorted[y][z + 1][0];
						unsorted[y][z][1] = unsorted[y][z + 1][1];
						unsorted[y][z][2]=unsorted[y][z+1][2];
						unsorted[y][z + 1][0] = temp;
						unsorted[y][z + 1][1] = temp2;
						unsorted[y][z+1][2] = temp3; 
					}
				}

			}
		}
		return unsorted;

	}
	/**insertionSort method 
	 * 
	 * This functional method sorts the list of words by length or alphabetically using insertion sort
	 * It sorts a column of the users choice based on how they want to sort the column
	 * 
	 *	-Let Ass3 represent an object of the Assignment3 class (type Assignment3)  
	 *	-Let temp(2,3) temporarily store the values of the array
	 * 
	 * @para 	column=Which column the user wants to sort
	 * 			unsorted=The array of words to sort
	 * 			type=How the user wants to sort
	 * 
	 * @return (String) The sorted version of the list of words
	 */
	public String[][][] insertionSort(String unsorted[][][], int column,int type) {
		Assignment3 Ass3 = new Assignment3();
		String temp[][] = new String[4][2];
		int y;
		for (int x = 1; x < unsorted[0].length; x++) {// Insertion Sort
			for (y = 0; y < unsorted.length; y++) {
				for (int z = 0; z < temp.length; z++) {
					temp[z][0] = unsorted[z][x][0];
					temp[z][1] = unsorted[z][x][1];
				}
			}
			for (y = x - 1; y >= 0
					&& Ass3.compareParameter(temp[column - 1][0],
							unsorted[column - 1][y][0], type); y--) {
				for (int z = 0; z < unsorted.length; z++) {
					unsorted[z][y + 1][0] = unsorted[z][y][0];
					unsorted[z][y + 1][1] = unsorted[z][y][1];
				}
			}

			for (int z = 0; z < temp.length; z++) {
				unsorted[z][y + 1][0] = temp[z][0];
				unsorted[z][y + 1][1] = temp[z][1];
			}

		}
		return unsorted;
	}
	/**compareParameter method 
	 * 
	 * This functional method is to select how to sort the list of words
	 * It will return either sorting from a-z, z-a, shortest to longest, longest to shortest
	 * 
	 * 
	 * @para 	word1=first word to compare
	 * 			word2=second word to compare
	 * 			type=How the user wants to sort
	 * 
	 * @return (boolean) True or false for how the user wants to sort the list of words
	 */
	public boolean compareParameter(String word1, String word2, int type) {
		if (type == 0) {
			return word1.compareToIgnoreCase(word2) < 0;// alphabetical a-z
		} else if (type == 1) {
			return word1.compareToIgnoreCase(word2) > 0;// alphabetical z-a
		} else if (type == 2) {
			return word1.length() < word2.length();// shortest to longest
		} else {
			return word1.length() > word2.length();// longest to shortest
		}

	}
	/**wordAdder method 
	 * 
	 * This functional method allows the user to add a new word to the list of arrays, if there is a open spot
	 * in the column the user wishes to add a word, the word is added to the open spot, otherwise
	 * a new row is created
	 * 
	 *	-Let Ass3 represent an object of the Assignment3 class (type Assignment3)  
	 *	-Let newWords represent the array of words to add the users word to(type String)
	 *	-Let type represent the type of word the user is inputing (type Strig)
	 *	-Let plural represent the plural version of the word the user inputed (if available) (type String)
	 * 
	 * @para 	column=Which column the user wants to sort
	 * 			unsorted=The array of words to sort
	 * 			type=How the user wants to sort
	 * 
	 * @return (String) The updated list of words with the users word added
	 */
	public String[][][] wordAdder(String oldWords[][][], String word, int column)
			throws IOException {
		String newWords[][][];
		Assignment3 Ass3 = new Assignment3();
		String type, plural;
		while (true) {
			System.out.println("What type of word is " + word + "?");
			System.out.println("1. Name");
			System.out.println("2. Noun");
			System.out.println("3. Verb");
			System.out.println("4. Adjective");
			System.out.println("5. Article(A, the, his, this)");
			System.out.println("6. Adverb");
			type = Ass3.getInput(1);//Calls getInput method to get users input
			if (type.equals("1"))
				type = "N";
			else if (type.equals("2"))
				type = "n";
			else if (type.equals("3"))
				type = "v";
			else if (type.equals("4"))
				type = "dp";
			else if (type.equals("5"))
				type = "a";
			else if (type.equals("6"))
				type = "b";
			else
				System.out.println("That is not a valid option.");
			plural = "";
			if (Ass3.checkType(oldWords, column, type)) {
				if (type.equals("n")) {
					System.out
							.println("What is the singular version of the word?");
					word = Ass3.getInput(0);
					System.out
							.println("What is the plural version of the word?");
					plural = type;

				}
				break;
			} else {
				System.out
						.println("Sorry, that type of word cannot be in column "
								+ column);
				Ass3.lineCreater(60, "~");
			}
			if (type.equals("a")) {
				System.out.println("Is this the plural version of the word?");
				System.out.println("e.g. These is the plural version of this");
				System.out.println("Enter 1 if it is the plural version");
				System.out.println("Enter anything else if it isn't");
				if (Ass3.getInput(0).equals("1"))//Checks 
					type += "p";

				break;
			}
		}

		if (oldWords[column - 1][oldWords[0].length - 1][1].equals("#"))
			newWords = new String[4][oldWords[0].length][3];

		else
			newWords = new String[4][oldWords[0].length + 1][3];

		for (int x = 0; x < oldWords.length; x++)
			System.arraycopy(oldWords[x], 0, newWords[x], 0, oldWords[x].length);
		for (int x = 0; x < newWords.length; x++) {
			if (newWords[x][newWords[x].length - 1][0] == (null)) {
				newWords[x][newWords[x].length - 1][0] = " ";
				newWords[x][newWords[x].length - 1][1] = "#";
			}
		}
		newWords[column - 1][newWords[0].length - 1][0] = word;
		newWords[column - 1][newWords[0].length - 1][1] = type;
		newWords[column - 1][newWords[0].length - 1][2] = plural;
		System.out.println("The list of words have added the new word, " + word
				+ ", in column " + column);
		return newWords;
	}
	/**randomWord method 
	 * 
	 * This functional method selects a random word from a column that is of the right type
	 * 
	 *	-Let selected represent the selected word and the word type
	 *	-Let random represent the random row selected
	 * 
	 * @para 	column=Which column to search for the word
	 * 			wordList=list of words to search through
	 * 			type=What word type to get
	 * 
	 * @return (String) The randomly selected word and its type
	 */
	public String[] randomWord(String wordList[][][], int column, String type) {
		String[] selected = new String[2];
		int random;

		while (true) {
			random = (int) Math.round(Math.random() * 15);
			if (type.contains("p"))
				selected[0] = wordList[column][random][2];
			else
				selected[0] = wordList[column][random][0];
			selected[1] = wordList[column][random][1];

			if (selected[1].contains(Character.toString(type.charAt(0))))
				break;
		}
		if (type.contains("@"))
			selected[1] += "@";
		return selected;
	}
	/**sentenceType method 
	 * 
	 * This functional method randomly chooses 1 of the 3 type of sentences types to create
	 * 
	 *	-Let wordType represent the types of word needed for the sentence(type String)
	 *	-Let newWords represent the array of words to add the users word to(type String)
	 *	-Let type represent the which type of sentence to create(type int)
	 * 
	 * @return (String) The word types needed for the sentence
	 */
	public String[] sentenceType() {
		String[] wordType = new String[4];
		int type = (int) Math.round((Math.random() * 2) + 1);
		if (type == 1) {
			wordType[0] = "a";
			wordType[1] = "n";
			wordType[2] = "v";
			wordType[3] = "b";
		} else if (type == 2) {
			wordType[0] = "N";
			wordType[1] = "v";
			wordType[2] = "d";
			wordType[3] = "n";
		} else {
			wordType[0] = "N";
			wordType[1] = "v";
			wordType[2] = "a";
			wordType[3] = "n";
		}
		return wordType;
	}
	/**sentenceCreater method 
	 * 
	 * This functional method generates random sentences
	 * 
	 *	-Let Ass3 represent an object of the Assignment3 class (type Assignment3)  
	 *	-Let sentence represent the sentences generated(type String)
	 *	-Let chosen represent the word chosen (type String)
	 *	-Let wordType represent the word types needed to generate the sentence (type String)
	 * 
	 * @para 	lines = the number of lines the user wants to create
	 * 			word = the list of word to get the words from
	 * 
	 * @return (String) The randomly generated sentences
	 */
	public String[] sentenceCreater(String word[][][], int lines) {
		Assignment3 Ass3 = new Assignment3();
		String[] sentence = new String[lines];
		String[] chosen = new String[2];
		String[] wordType = new String[4];
		for (int x = 0; x < sentence.length; x++)
			sentence[x] = "";
		for (int x = 0; x < sentence.length; x++) {
			wordType = Ass3.sentenceType();// Randomly selects a rule to follow
			for (int y = 0; y < 4; y++) {
				chosen = Ass3.randomWord(word, y, wordType[y]);

				if (chosen[1].contains("p"))
					wordType[y + 1] += "p";
				if (chosen[0].equals("a"))
					wordType[y + 1] += "@";

				if (chosen[0].equals("a"))
					sentence[x] += " " + chosen[0];

				else if (chosen[1].contains("@")
						&& chosen[0].matches("^[aeiou].*"))
					sentence[x] += "n " + chosen[0];
				else if (chosen[1].contains("@"))
					sentence[x] += " " + chosen[0];
				else
					sentence[x] += " " + chosen[0];
				if (y == 3)
					sentence[x] += ".";
			}
			sentence[x] = sentence[x].substring(0, 2).toUpperCase()
					+ sentence[x].substring(2);
		}
		return sentence;
	}
	/**wordCount method 
	 * 
	 * This functional method outputs the occurrences of the target word in the list of words
	 * 
	 *	-Let counter represent the number of times the word appears in the list of words
	 * 
	 * @para 	word = list of words to search through
	 * 			target = which word to find the occurrences for
	 */
	public int wordCount(String word[][][], String target) {
		int counter = 0;
		for (int y = 0; y < word[0].length; y++) {
			for (int x = 0; x < word.length; x++) {
				// Prints words in tabular format

				if (target.equalsIgnoreCase(word[x][y][0]))
					counter++;
			}

		}
		return counter;
	}
	/**count method 
	 * 
	 * This procedural method displays the occurrences of each word in the list of words
	 * 
	 *	-Let Ass3 represent an object of the Assignment3 class (type Assignment3)  
	 * 
	 * @para 	word = list of words to count the occurences for
	 * 
	 */
	public void count(String word[][][]) {
		Assignment3 Ass3 = new Assignment3();
		System.out.println("The number next to the word indicates how many times that word occurs in the list");
		for (int y = 0; y < word[0].length; y++) {
			for (int x = 0; x < word.length; x++) {
				// Prints words in tabular format
				System.out.printf("|%-15s", word[x][y][0]);
				System.out.print(Ass3.wordCount(word, word[x][y][0]));
			}
			System.out.println();
		}
	}
}
