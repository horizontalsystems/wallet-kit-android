package io.horizontalsystems.bitcoinkit.network.PeerTask

import io.horizontalsystems.bitcoinkit.models.InventoryItem
import io.horizontalsystems.bitcoinkit.models.Transaction

class SendTransactionTask(val transaction: Transaction) : PeerTask() {
    override fun start() {
        requester?.sendTransactionInventory(transaction.hash)
    }

    override fun handleGetDataInventoryItem(item: InventoryItem): Boolean {
        if (item.type == InventoryItem.MSG_TX && item.hash.contentEquals(transaction.hash)) {
            requester?.send(transaction)
            delegate?.onTaskCompleted(this)

            return true
        }

        return false
    }
}