package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0DPacketCollectItem extends Packet
{
    private int field_149357_a;
    private int field_149356_b;
    private static final String __OBFID = "CL_00001339";

    public S0DPacketCollectItem() {}

    public S0DPacketCollectItem(int p_i45232_1_, int p_i45232_2_)
    {
        this.field_149357_a = p_i45232_1_;
        this.field_149356_b = p_i45232_2_;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer p_148837_1_) throws IOException
    {
        this.field_149357_a = p_148837_1_.readInt();
        this.field_149356_b = p_148837_1_.readInt();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer p_148840_1_) throws IOException
    {
        p_148840_1_.writeInt(this.field_149357_a);
        p_148840_1_.writeInt(this.field_149356_b);
    }

    public void func_148833_a(INetHandlerPlayClient p_148833_1_)
    {
        p_148833_1_.handleCollectItem(this);
    }

    public void func_148833_a(INetHandler p_148833_1_)
    {
        this.func_148833_a((INetHandlerPlayClient)p_148833_1_);
    }
}
