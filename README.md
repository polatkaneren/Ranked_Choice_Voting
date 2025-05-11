# Ranked Choice Voting System (Instant-Runoff Voting)

This Java application simulates a ranked-choice (instant-runoff) election system. It reads a file of voters’ ranked preferences and processes votes in multiple rounds until a winner is determined or a tie is reached.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Class Breakdown](#class-breakdown)
- [Input Format](#input-format)
- [How to Run](#how-to-run)
- [Example Files](#example-files)
- [License](#license)

## Overview

In each round of voting:
- The program tallies all first-choice votes.
- If a candidate has a majority, they win.
- If no candidate has a majority, the one with the fewest votes is eliminated.
- Ballots that ranked the eliminated candidate shift their vote to the next preferred candidate.

This process continues until a candidate has a majority or a tie is unavoidable.

## Features

- Supports unlimited candidates and voters.
- Handles incomplete or partially empty ballots.
- Eliminates the lowest-ranked candidate in each round.
- Identifies winners or ties with proper messaging.
- Handles long candidate names and edge cases.

## Class Breakdown

- `TallyVotes2.java`: Main class that manages reading input, counting votes, and elimination logic.
- `Ballot.java`: Represents a single voter’s preferences and allows dynamic elimination of candidates.

## Input Format

- Each line in the input file represents a ballot.
- Candidate names are **tab-separated** in order of preference.
- Empty lines are ignored.

Example:

```
Alice    Bob    Carol
Bob      Carol  Alice
Carol    Alice  Bob
```

## How to Run

1. Compile all Java files:
   ```bash
   javac TallyVotes2.java Ballot.java
   ```

2. Run the program:
   ```bash
   java TallyVotes2
   ```

3. When prompted, enter the name of a vote file (e.g., `vote.txt`), or type `quit` to exit.

## Example Files

Included in the repository:
- `vote.txt` – Standard case
- `vote_no_winner_tie.txt` – Tie scenario
- `vote_no_winner_no_majority.txt` – No majority, no clear winner
- `vote_multiple_cases.txt` – Mixed ballots
- `vote_incomplete_prefs.txt` – Incomplete preference rankings
- `vote_longer_names.txt` – Ballots with long candidate names

## License

This project is licensed under the MIT License.
