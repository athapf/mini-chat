package de.thaso.mc.fe.chat;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 * ChatController
 *
 * @author thaler
 * @since 23.09.16
 */
@Named
@ApplicationScoped
public class ChatController {

    public String getMessage() {
        return "Hello, im the controller!";
    }
}
