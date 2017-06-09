package stream.flarebot.flarebot.commands.automod;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import stream.flarebot.flarebot.commands.Command;
import stream.flarebot.flarebot.commands.CommandType;
import stream.flarebot.flarebot.commands.FlareBotManager;
import stream.flarebot.flarebot.mod.AutoModConfig;
import stream.flarebot.flarebot.util.MessageUtils;

import java.awt.*;

public class ModlogCommand implements Command {

    @Override
    public void onCommand(User sender, TextChannel channel, Message message, String[] args, Member member) {
        if (!getPermissions(channel).hasPermission(member, "flarebot.modlog"))
            return;
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("setchannel")) {
                FlareBotManager.getInstance().getAutoModConfig(channel.getGuild().getId())
                        .setModLogChannel(channel.getId());
                channel.sendMessage(new EmbedBuilder().setColor(Color.green)
                        .setDescription("The modlog channel has been changed to " + channel
                                .getAsMention()).build()).queue();
            } else if (args[0].equalsIgnoreCase("config")) {
                AutoModConfig config = FlareBotManager.getInstance().getAutoModConfig(channel.getGuild().getId());
            } else if (args[0].equalsIgnoreCase("set")) {

            } else {
                MessageUtils.sendErrorMessage("Invalid argument!", channel);
            }
        } else if (args.length == 2) {

        } else if (args.length == 3) {

        } else {
            MessageUtils.getUsage(this, channel, sender).queue();
        }
    }

    @Override
    public String getCommand() {
        return "modlog";
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getUsage() {
        return "`{%}modlog setchannel` - Set the modlog to be displayed in this channel.\n"
                + "`{%}modlog config` - View the config of the modlog.\n"
                + "`{%}modlog set <configOption> <value>` - Set config options";
    }

    @Override
    public CommandType getType() {
        return CommandType.MODERATION;
    }
}