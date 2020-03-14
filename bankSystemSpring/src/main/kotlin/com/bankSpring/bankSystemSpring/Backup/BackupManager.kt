package com.bankSpring.bankSystemSpring.Backup

import com.bankSpring.bankSystemSpring.Model.Account
import com.fasterxml.jackson.module.kotlin.*

import java.io.File

class BackupManager()
{
    //Pass an object into backup manager with the desired json file location
    var accountsBackupLocation: String = "src/main/kotlin/com/bankSpring/bankSystemSpring/Backup/accounts_backup.txt"
    var acc_json = accounts_Json()

    fun loadAccounts() : ArrayList<Account>
    {
        var text: String = File(accountsBackupLocation).bufferedReader().readText()
        var accStrObjects = text.split(";")
        var accounts = ArrayList<Account>()

        for ( accStr in accStrObjects)
        {
            if(!accStr.isEmpty())
            {
                accounts.add(toAccount(accStr))
            }
        }

        return accounts
    }

    private fun toAccount(accJson: String): Account
    {
        val mapper = jacksonObjectMapper()
        var acc: Account = mapper.readValue(accJson)

        return acc
    }

    private fun backup(text: String)
    {
        File(accountsBackupLocation).appendText(text)
    }

    fun backupAccount(acc: Account)
    {
        backup(acc_json.toJson(acc) + ";")
    }
}