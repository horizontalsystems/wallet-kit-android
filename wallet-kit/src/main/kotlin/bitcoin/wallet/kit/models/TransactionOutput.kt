package bitcoin.wallet.kit.models

import bitcoin.wallet.kit.hdwallet.PublicKey
import bitcoin.walllet.kit.io.BitcoinInput
import bitcoin.walllet.kit.io.BitcoinOutput
import io.realm.RealmObject
import java.io.IOException

/**
 * Transaction output
 *
 *  Size        Field                Description
 *  ====        =====                ===========
 *  8 bytes     OutputValue          Value expressed in Satoshis (0.00000001 BTC)
 *  VarInt      OutputScriptLength   Script length
 *  Variable    OutputScript         Script
 */
open class TransactionOutput : RealmObject {

    // Output value
    var value: Long = 0

    // Output script used for authenticating that the redeemer is allowed to spend this output.
    var lockingScript: ByteArray = byteArrayOf()

    var publicKey: PublicKey? = null

    constructor()

    @Throws(IOException::class)
    constructor(input: BitcoinInput) {
        value = input.readLong()
        val scriptLength = input.readVarInt() // do not store
        lockingScript = input.readBytes(scriptLength.toInt())
    }

    fun toByteArray(): ByteArray {
        return BitcoinOutput()
                .writeLong(value)
                .writeVarInt(lockingScript.size.toLong())
                .write(lockingScript)
                .toByteArray()
    }
}