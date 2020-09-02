package bot_package;

import org.javacord.api.entity.message.MessageSet;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.event.message.MessageDeleteEvent;
import org.javacord.api.event.message.MessageEditEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.listener.message.MessageDeleteListener;
import org.javacord.api.listener.message.MessageEditListener;

import java.awt.*;

public class Listener implements MessageCreateListener, MessageEditListener , MessageDeleteListener {

    private Listener_types type = Listener_types.admin;

    public Listener(Listener_types type)
    {
        this.type = type;
    }
    public Listener(){}

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        if (messageCreateEvent.getMessageAuthor().isBotUser())return;

        switch (this.type){
            case all:{
                if (messageCreateEvent.getMessageAuthor().isServerAdmin() && messageCreateEvent.getMessageContent().equalsIgnoreCase(".messaging"))
                {
                    if (Main.messaging)
                    {
                        Main.messaging = false;
                        messageCreateEvent.getChannel().sendMessage(new EmbedBuilder().setAuthor("Bot").setTitle("Wyłączono generowanie wiadomości").setDescription("Wpisz '.messaging' aby zmienić status").setTimestampToNow().setColor(Color.red));
                        messageCreateEvent.deleteMessage();
                    }else{
                        Main.messaging = true;
                        messageCreateEvent.getChannel().sendMessage(new EmbedBuilder().setAuthor("Bot").setTitle("Włączono generowanie wiadomości").setDescription("Wpisz '.messaging' aby zmienić status").setTimestampToNow().setColor(Color.green));
                        messageCreateEvent.deleteMessage();
                    }
                    return;
                }
                if (Main.messaging) {
                    EmbedBuilder embed = new EmbedBuilder()
                            .setAuthor(messageCreateEvent.getMessageAuthor())
                            .setTimestampToNow()
                            .setColor(Color.blue)
                            .setTitle(messageCreateEvent.getMessageContent())
                            .setDescription("Wyłącz tą funkcję wpisując '.messaging'");
                    messageCreateEvent.deleteMessage();
                    messageCreateEvent.getChannel().sendMessage(embed);
                }


                break;
            }
            case user:{
                break;
            }
            case admin:{
                break;
            }
            case owner:{
                break;
            }
        }
    }

    @Override
    public void onMessageEdit(MessageEditEvent messageEditEvent) {
        messageEditEvent.addReactionToMessage(":flag_black:");
    }

    @Override
    public void onMessageDelete(MessageDeleteEvent messageDeleteEvent) {

    }

    public enum Listener_types{
        admin,
        user,
        owner,
        all;


    }
}

