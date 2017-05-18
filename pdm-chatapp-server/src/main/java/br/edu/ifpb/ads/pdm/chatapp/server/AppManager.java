package br.edu.ifpb.ads.pdm.chatapp.server;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 30/03/2017, 14:05:33
 */
public class AppManager {
    private final ChatManager chatManager;
    private final UserManager userManager;
    private final MessageManager messageManager;

    public AppManager(ChatManager chatManager, UserManager userManager, MessageManager messageManager) {
        this.chatManager = chatManager;
        this.userManager = userManager;
        this.messageManager = messageManager;
    }

    public ChatManager getChatManager() {
        return chatManager;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }
    
    public void exec(){
        chatManager.exec();
        userManager.exec();
        messageManager.exec();
    }
}
