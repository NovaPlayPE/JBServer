package net.novaplay.jbserver.network.bedrock.handler;

import com.nukkitx.protocol.bedrock.BedrockPacket;
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.novaplay.jbserver.player.bedrock.BedrockPlayer;

@RequiredArgsConstructor
public class JBBedrockPacketHandler implements BedrockPacketHandler {

    @Getter
    private final BedrockPlayer player;

    public boolean handle(BedrockPacket dataPacket) {
        if (!player.isConnected()) {
            return true;
        }

        switch (dataPacket.getPacketType()) {
            //TODO: Subsequent Work with Packages
            default:
                break;
        }

        return true;
    }
}