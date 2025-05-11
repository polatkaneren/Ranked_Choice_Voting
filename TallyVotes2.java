// Polatkan Eren Polat
// Program to perform ranked choice voting algorithm
// using a data file of voting preferences.
// This file should be *almost* identical to the code explained in the book
// Lines have been added to main to ask the user for the file to read

import java.util.*;
import java.io.*;

public class TallyVotes2 {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner keyboard = new Scanner(System.in);
    while (true) {
      System.out.println("What file contains the ballot information? (type quit to end the program)");
      String fileName = keyboard.nextLine();
      
      if (fileName.equalsIgnoreCase("quit")) {
        System.out.println("Goodbye!");
        break;
      }
      
      try {
        Scanner input = new Scanner(new File(fileName));
        ArrayList<Ballot> ballots = readFile(input);
        if (ballots.size() == 0) {
          System.out.println("Round #1");
          System.out.println("Election has no winner");
          System.out.println("------------------------------");
          continue;
        }
        
        int originalBallotCount = ballots.size();
        int round = 1;
        boolean done = false;
        while (!done) {
          System.out.println("Round #" + round);
          Collections.sort(ballots);
          done = oneRound(ballots, originalBallotCount);
          System.out.println("------------------------------");
          round++;
        }
      } catch (FileNotFoundException e) {
        System.out.println("File not found: " + fileName);
      }
    }
  }

  // Reads a data file of voter preferences, returning a list
  // of the resulting ballots. Candidate names are listed in
  // order of preference with tabs separating choices.
  public static ArrayList<Ballot> readFile(Scanner input) {
    ArrayList<Ballot> result = new ArrayList<>();
    while (input.hasNextLine()) {
      String text = input.nextLine().trim();
      if (text.length() > 0) {  // Skip empty lines using basic string check
        result.add(new Ballot(text.split("\t")));
      }
    }
    return result;
  }

  // Performs one round of ranked choice voting. The candidate
  // with the lowest vote total is eliminated until some
  // candidate gets a majority or until we reach a tie between
  // only two candidates. Assumes the list is in order by
  // candidate name.
  public static boolean oneRound(ArrayList<Ballot> ballots, int originalBallotCount) {
    // Remove empty ballots first
    removeEmptyBallots(ballots);
    
    if (ballots.size() == 0) {
      System.out.println("Election has no winner");
      return true;
    }
    
    String top = null;
    String bottom = null;
    int topCount = 0;
    int bottomCount = ballots.size() + 1;
    int index = 0;
    
    while (index < ballots.size()) {
      String next = ballots.get(index).getCandidate();
      if (next.equals("none")) {
        index++;
        continue;
      }
      int count = processVotes(next, index, ballots);
      if (count > topCount) {
        topCount = count;
        top = next;
      }
      if (count < bottomCount) {
        bottomCount = count;
        bottom = next;
      }
      index += count;
    }
    
    if (topCount == bottomCount) {
      System.out.println("Election has no winner");
      return true;
    } else if (topCount > originalBallotCount / 2.0) {  // Use original ballot count for majority
      System.out.println("Winner is " + top);
      return true;
    } else {
      System.out.println("no winner, eliminating " + bottom);
      eliminate(bottom, ballots);
      return false;
    }
  }

  // Counts and reports the votes for the next candidate
  // starting at the given index in the ballots list.
  public static int processVotes(String name, int index, ArrayList<Ballot> ballots) {
    int count = 0;
    while (index < ballots.size() && ballots.get(index).getCandidate().equals(name)) {
      index++;
      count++;
    }
    double percent = 100.0 * count / ballots.size();
    System.out.printf("%d votes for %s (%4.1f%%)\n", count, name, percent);
    return count;
  }

  // Eliminates the given candidate from all ballots
  public static void eliminate(String candidate, ArrayList<Ballot> ballots) {
    // First eliminate the candidate from all ballots
    for (int i = 0; i < ballots.size(); i++) {
      ballots.get(i).eliminate(candidate);
    }
    // Then remove empty ballots
    removeEmptyBallots(ballots);
  }
  
  // Helper method to remove empty ballots from the list using basic list operations
  private static void removeEmptyBallots(ArrayList<Ballot> ballots) {
    // Remove from end to avoid shifting elements unnecessarily
    for (int i = ballots.size() - 1; i >= 0; i--) {
      if (ballots.get(i).isEmpty()) {
        ballots.remove(i);
      }
    }
  }
}