package io.horizontalsystems.groestlcoinkit

import io.horizontalsystems.bitcoincore.io.BitcoinInput
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(MainNet::class)

class MainNetTest {

    private lateinit var network: MainNet

    @Before
    fun setup() {
        network = MainNet()
    }

    @Test
    fun packetMagic() {
        val stream = BitcoinInput(byteArrayOf(
                0xf9.toByte(),
                0xbe.toByte(),
                0xb4.toByte(),
                0xd4.toByte()
        ))

        val magic = stream.readUnsignedInt()
        assertEquals(magic, network.magic)
    }

}
