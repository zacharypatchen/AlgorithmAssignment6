import java.util.*;

public class Election {
    // PriorityQueue to store candidates based on the number of votes
    PriorityQueue<Candidate> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

    /*
    InitializeCandidates(LinkedList<String> candidates): Initialize the election system with the list
    of candidates given.
     */
    public void initializeCandidates(LinkedList<String> candidates) {
        for (String candidate : candidates) {
            priorityQueue.offer(new Candidate(candidate, 0));
        }
    }

    /*
    castVote(String candidate): Simulate a vote for the specified candidate and update the priority
    queue.
     */
    public void castVote(String candidateName) {
        // Find the candidate in the priority queue and update its vote count
        for (Candidate c : priorityQueue) {
            if (c.name.equals(candidateName)) {
                // Remove the candidate from the priority queue
                priorityQueue.remove(c);
                // Update the vote count
                c.votes++;
                // Re-add the candidate to the priority queue
                priorityQueue.offer(c);
                break;
            }
        }
    }


    /*
    castRandomVote(): Simulate a vote for a random candidate and update the priority queue.
     */
    public void castRandomVote() {
        // Generate a random index to select a candidate
        Random rand = new Random();
        int index = rand.nextInt(priorityQueue.size());

        // Retrieve the array representation of the priority queue
        Candidate[] candidates = priorityQueue.toArray(new Candidate[0]);

        // Update the vote count for the randomly selected candidate
        candidates[index].votes++;

        // Reconstruct the priority queue based on the updated vote counts
        PriorityQueue<Candidate> newPriorityQueue = new PriorityQueue<>(priorityQueue.size(), Comparator.reverseOrder());
        newPriorityQueue.addAll(Arrays.asList(candidates));
        priorityQueue = newPriorityQueue;
    }


    /*
    rigElection(String candidate): Simulate enough votes for the given candidate to win the
    election and update the priority queue. (Note: The total number of votes should be p)
     */
    public void rigElection(String candidateName) {
        // Find the candidate in the priority queue
        Candidate candidateToRig = null;
        for (Candidate c : priorityQueue) {
            if (c.name.equals(candidateName)) {
                candidateToRig = c;
                break;
            }
        }

        // If candidate is not found, return
        if (candidateToRig == null) {
            return;
        }

        // Calculate the total number of votes needed to rig the election
        int totalVotesNeeded = priorityQueue.peek().votes - candidateToRig.votes + 1;

        // Redistribute votes to rig the election
        for (Candidate c : priorityQueue) {
            if (c != candidateToRig) {
                // Calculate the number of votes to add to this candidate
                int votesToAdd = totalVotesNeeded - (priorityQueue.peek().votes - c.votes);
                c.votes += votesToAdd;
                totalVotesNeeded -= votesToAdd;
            }
        }

        // Set the rigged candidate's votes to the total needed
        candidateToRig.votes = priorityQueue.peek().votes + 1;

        // Reconstruct the priority queue based on the updated vote counts
        PriorityQueue<Candidate> newPriorityQueue = new PriorityQueue<>(priorityQueue.size(), Comparator.reverseOrder());
        newPriorityQueue.addAll(priorityQueue);
        priorityQueue = newPriorityQueue;
    }


    /*
    getTopKCandidates(int k): Return the top k candidates with the most votes.
     */
    public String[] getTopKCandidates(int k) {
        List<String> topCandidates = new ArrayList<>();
        PriorityQueue<Candidate> tempQueue = new PriorityQueue<>(priorityQueue);

        for (int i = 0; i < k && !tempQueue.isEmpty(); i++) {
            topCandidates.add(tempQueue.poll().name);
        }
        return topCandidates.toArray(new String[0]);
    }

    /*
    auditElection(): Print to console all the candidates and how many votes they got in order from
    the candidate with the most votes to the candidate with the least amount of votes.
     */
    public void auditElection() {
        while (!priorityQueue.isEmpty()) {
            Candidate c = priorityQueue.poll();
            System.out.println(c.name + ": " + c.votes + " votes");
        }
    }

    // Internal class to represent a Candidate
    private class Candidate implements Comparable<Candidate> {
        String name;
        int votes;

        public Candidate(String name, int votes) {
            this.name = name;
            this.votes = votes;
        }

        @Override
        public int compareTo(Candidate other) {
            return Integer.compare(this.votes, other.votes);
        }
    }
}
