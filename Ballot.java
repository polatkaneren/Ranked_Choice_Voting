// Polatkan Eren Polat
// class for storing one voter's preferences

import java.util.*;

public class Ballot implements Comparable<Ballot> {
  private ArrayList<String> preferences;

  // constructs a ballot with the given candidate names in order
  public Ballot(String[] names) {
    preferences = new ArrayList<>();
    for (String next : names) {
      if (next.trim().length() > 0) {  // Basic string check for empty names
        preferences.add(next);
      }
    }
  }

  // returns true if this ballot has no candidates left
  public boolean isEmpty() {
    return preferences.size() == 0;
  }

  // returns the current first choice for this ballot or "none" if there are
  // no longer any choices for this ballot
  public String getCandidate() {
    if (preferences.size() == 0) {
      return "none";
    }
    return preferences.get(0);
  }

  // eliminates the given candidate from consideration
  public void eliminate(String name) {
    int index = preferences.indexOf(name);
    if (index >= 0) {
      preferences.remove(index);
    }
  }

  // compares this ballot to another, putting them in order
  // alphabetically by their first choice candidate
  public int compareTo(Ballot other) {
    return getCandidate().compareTo(other.getCandidate());
  }
}