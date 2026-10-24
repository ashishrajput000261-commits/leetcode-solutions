class Solution {
    private static final int[] DAYS_IN_MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // The overlap starts on the later arrival date
        String startOverlap = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        // The overlap ends on the earlier departure date
        String endOverlap = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;

        // If the start of the overlap is after the end, they don't overlap at all
        if (startOverlap.compareTo(endOverlap) > 0) {
            return 0;
        }

        // Calculate the difference in days (inclusive)
        return toDayOfYear(endOverlap) - toDayOfYear(startOverlap) + 1;
    }

    // Helper method to convert "MM-DD" to the day of the year (1 to 365)
    private int toDayOfYear(String date) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += DAYS_IN_MONTH[i];
        }
        return totalDays;
    }
}