package de.thaso.mc.fe.chat;

import de.thaso.mc.fe.chat.model.ChatOverviewModel;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
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

    @Inject
    private ChatOverviewModel chatOverviewModel;
}
