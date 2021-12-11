package net.azisaba.bungeeLog4j2Fix;

import net.azisaba.bungeeLog4j2Fix.util.Util;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeLog4j2Fix extends Plugin {
    @Override
    public void onLoad() {
        String replace = ".replaceAll(\"\\\\$\\\\{(.*?):?(.*?)}.*\", \"\").replaceAll(\"(?i)jndi:ldap.*\", \"\")";
        Util.redefineClass("net.md_5.bungee.protocol.packet.Kick", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (message != null) { message = message" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (message != null) { message = message" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Team", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (name != null) { name = name" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (name != null) { name = name" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Title", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (text != null) { text = text" + replace + "; } }");
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (text != null) { text = text" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Subtitle", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (text != null) { text = text" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (text != null) { text = text" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.TabCompleteRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (cursor != null) { cursor = cursor" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (cursor != null) { cursor = cursor" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.ScoreboardScore", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (itemName != null) { itemName = itemName" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (itemName != null) { itemName = itemName" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.ScoreboardObjective", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (name != null) { name = name" + replace + "; value = value" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (name != null) { name = name" + replace + "; value = value" + replace + "; } }");
            return cc.toBytecode();
        });
        /*
        Util.redefineClass("net.md_5.bungee.protocol.packet.ScoreboardDisplay", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (name != null) { name = name" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (name != null) { name = name" + replace + "; } }");
            return cc.toBytecode();
        });
        */
        Util.redefineClass("net.md_5.bungee.protocol.packet.PluginMessage", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (tag != null) { tag = tag" + replace + "; if (\"REGISTER\".equals(tag) || \"minecraft:register\".equals(tag)) { data = new String(data, java.nio.charset.StandardCharsets.UTF_8)" + replace + ".getBytes(java.nio.charset.StandardCharsets.UTF_8); } } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (tag != null) { tag = tag" + replace + "; if (\"REGISTER\".equals(tag) || \"minecraft:register\".equals(tag)) { data = new String(data, java.nio.charset.StandardCharsets.UTF_8)" + replace + ".getBytes(java.nio.charset.StandardCharsets.UTF_8); } } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.LoginSuccess", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (username != null) { username = username" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (username != null) { username = username" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.LoginRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (data != null) { data = data" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (data != null) { data = data" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.LoginPayloadRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (channel != null) { channel = channel" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (channel != null) { channel = channel" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Handshake", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (host != null) { host = host" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (host != null) { host = host" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.EncryptionRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (serverId != null) { serverId = serverId" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (serverId != null) { serverId = serverId" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.ClientSettings", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (locale != null) { locale = locale" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (locale != null) { locale = locale" + replace + "; } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Chat", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (message != null) { message = message" + replace + "; } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (message != null) { message = message" + replace + "; } }");
            return cc.toBytecode();
        });
    }
}
