import java.util.LinkedList;

public class ElectionSystem {
    public static void main(String[] args) {
        // Create an instance of the Election class
        Election election = new Election();

        // Initialize the election with a list of candidates
        LinkedList<String> candidates = new LinkedList<>();
        candidates.add("Marcus Fenix");
        candidates.add("Dominic Santiago");
        candidates.add("Damon Baird");
        candidates.add("Cole Train");
        candidates.add("Anya Stroud");
        election.initializeCandidates(candidates);

        // Perform various operations on the Election instance
        // 1. Simulate casting votes
        election.castVote("Cole Train");
        election.castVote("Cole Train");
        election.castVote("Marcus Fenix");
        election.castVote("Anya Stroud");
        election.castVote("Anya Stroud");

        // 2. Get top 3 candidates after initial votes
        String[] topCandidates = election.getTopKCandidates(3);
        System.out.println("Top 3 candidates after 5 votes:");
        for (String candidate : topCandidates) {
            System.out.println(candidate);
        }

        // 3. Rig the election for a specific candidate
        String candidateToRig = "Marcus Fenix"; // Example: Marcus Fenix
        election.rigElection(candidateToRig);

        // 4. Get top 3 candidates after rigging the election
        topCandidates = election.getTopKCandidates(3);
        System.out.println("Top 3 candidates after rigging the election:");
        for (String candidate : topCandidates) {
            System.out.println(candidate);
        }

        // 5. Audit the election
        System.out.println("Audit of the Election:");
        election.auditElection();
    }
}
