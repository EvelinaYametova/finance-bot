public class DialogLogic {
    private BotReply replyGenerator = new BotReply();

    public String executeCommand(String command) {
        return this.replyGenerator.generateAnswerByLine(command);
    }
}