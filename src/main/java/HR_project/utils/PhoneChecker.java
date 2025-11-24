package HR_project.utils;

public class PhoneChecker {

    private static final String FULL_FORMAT = "^\\+998\\d{9}$";  // +998#########
    private static final String SHORT_FORMAT = "^\\d{9}$";        // #########

    /**
     * Normalizes any Uzbekistan phone number to +998######### format.
     *
     * @param phone raw phone number
     * @return normalized phone number in +998######### format
     */
    public static String normalizePhone(String phone) {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }

        // Remove all non-digit characters except leading '+'
        phone = phone.replaceAll("[^\\d+]", "");

        // If already +998#########
        if (phone.matches(FULL_FORMAT)) {
            return phone;
        }

        // If 9-digit short number #########
        if (phone.matches(SHORT_FORMAT)) {
            return "+998" + phone;
        }

        // If starts with 998######### without '+'
        if (phone.matches("^998\\d{9}$")) {
            return "+" + phone;
        }

        throw new IllegalArgumentException("Invalid Uzbekistan phone number format: " + phone);
    }
}
