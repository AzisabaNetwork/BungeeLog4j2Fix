package net.azisaba.bungeeLog4j2Fix;

import net.azisaba.bungeeLog4j2Fix.util.Util;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeLog4j2Fix extends Plugin {
    @Override
    public void onLoad() {
        Util.redefineClass("net.md_5.bungee.protocol.packet.Kick", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (message != null) { message = message.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (message != null) { message = message.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Team", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (name != null) { name = name.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (name != null) { name = name.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Title", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (text != null) { text = text.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (text != null) { text = text.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Subtitle", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (text != null) { text = text.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (text != null) { text = text.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.TabCompleteRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (cursor != null) { cursor = cursor.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (cursor != null) { cursor = cursor.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.ScoreboardScore", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (itemName != null) { itemName = itemName.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (itemName != null) { itemName = itemName.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.ScoreboardObjective", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (name != null) { name = name.replaceAll(\"(?i)jndi:ldap\", \"\"); value = value.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (name != null) { name = name.replaceAll(\"(?i)jndi:ldap\", \"\"); value = value.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        /*
        Util.redefineClass("net.md_5.bungee.protocol.packet.ScoreboardDisplay", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (name != null) { name = name.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (name != null) { name = name.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        */
        Util.redefineClass("net.md_5.bungee.protocol.packet.PluginMessage", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (tag != null) { tag = tag.replaceAll(\"(?i)jndi:ldap\", \"\"); if (\"REGISTER\".equals(tag) || \"minecraft:register\".equals(tag)) { data = new String(data, java.nio.charset.StandardCharsets.UTF_8).replaceAll(\"(?i)jndi:ldap\", \"\").getBytes(java.nio.charset.StandardCharsets.UTF_8); } } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (tag != null) { tag = tag.replaceAll(\"(?i)jndi:ldap\", \"\"); if (\"REGISTER\".equals(tag) || \"minecraft:register\".equals(tag)) { data = new String(data, java.nio.charset.StandardCharsets.UTF_8).replaceAll(\"(?i)jndi:ldap\", \"\").getBytes(java.nio.charset.StandardCharsets.UTF_8); } } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.LoginSuccess", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (username != null) { username = username.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (username != null) { username = username.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.LoginRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (data != null) { data = data.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (data != null) { data = data.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.LoginPayloadRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (channel != null) { channel = channel.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (channel != null) { channel = channel.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Handshake", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertAfter("{ if (host != null) { host = host.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;)V")
                    .insertBefore("{ if (host != null) { host = host.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.EncryptionRequest", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (serverId != null) { serverId = serverId.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (serverId != null) { serverId = serverId.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.ClientSettings", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (locale != null) { locale = locale.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (locale != null) { locale = locale.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
        Util.redefineClass("net.md_5.bungee.protocol.packet.Chat", cc -> {
            cc.getMethod("read", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertAfter("{ if (message != null) { message = message.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            cc.getMethod("write", "(Lio/netty/buffer/ByteBuf;Lnet/md_5/bungee/protocol/ProtocolConstants$Direction;I)V")
                    .insertBefore("{ if (message != null) { message = message.replaceAll(\"(?i)jndi:ldap\", \"\"); } }");
            return cc.toBytecode();
        });
    }
}
