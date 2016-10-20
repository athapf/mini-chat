package de.thaso.mc.fe.chat;

import de.thaso.mc.fe.chat.model.ChatOverviewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);

    @Inject
    private ChatOverviewModel chatOverviewModel;
}
