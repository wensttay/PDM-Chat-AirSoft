package br.edu.ifpb.ads.pdm.chatapp.socket;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 30/03/2017, 08:03:42
 */
public class SocketProtocol {

    private static final String CODE_TOKEN = "---123---";
    private static final String ERROR_TOKEN = "---ERROR---";
    private static final String START_TOKEN = "--|START|--";
    private static final String END_TOKEN = "--|END|--";

    public static String encodeMessage(String content) {
        if (content != null) {
            return CODE_TOKEN + START_TOKEN + content + END_TOKEN + CODE_TOKEN;
        }else{
            return ERROR_TOKEN;
        }
    }

    public static String decodeMessage(String msg) {

        String m = msg.replaceAll(CODE_TOKEN, "");
        m = m.replace(START_TOKEN, "");
        m = m.replace(END_TOKEN, "");
        return m;
    }
}
