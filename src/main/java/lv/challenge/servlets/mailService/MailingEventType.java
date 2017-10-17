package lv.challenge.servlets.mailService;

public enum MailingEventType {
    REGISTRATION, ROBOT_ACCEPTED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
