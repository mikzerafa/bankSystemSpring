package com.bankSpring.bankSystemSpring.Controller

import com.bankSpring.bankSystemSpring.Backup.BackupManager
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.RequestParam


import com.bankSpring.bankSystemSpring.Service.Bank
import com.bankSpring.bankSystemSpring.Model.Account



@RestController
class AccountController
{
    @Autowired
    lateinit var bank: Bank
    var backupManager = BackupManager()

    @PostMapping("/api/account/load")
    fun loadAccounts() : String
    {

        for(acc in backupManager.loadAccounts())
        {
            bank.save(acc)
        }

        return "accounts have been loaded!"
    }

    @PostMapping("/api/account/create")
    fun createAccount(@RequestParam(name="beneficiaryName") beneficiaryName: String, @RequestParam(name="pinNumber") pinNumber:String) : String
    {
        val newAcc = Bank.createAccount(beneficiaryName,pinNumber);
        bank.save(newAcc)
        //backupManager.backupAccounts(accountService)
        backupManager.backupAccount(newAcc)
        return "account Added"
    }

    @GetMapping("/api/account/getAccountNumber")
    fun getAccountNumber(@RequestParam(name="beneficiaryName") beneficiaryName: String, @RequestParam(name="pinNumber")pinNumber: String) :String
    {
        var output = "account not found"
        for( acc in bank.findAll())
        {
            if(acc.beneficiaryName == beneficiaryName && acc.pinNumber == pinNumber)
            {
                output = acc.accountNumber.toString()
            }
        }

        return output
    }

    @GetMapping("/api/account/exists")
    fun accountExists(@RequestParam(name="AccountNumber") accountNumber: String) : Boolean
    {
        return bank.existsById(accountNumber)
    }

    @GetMapping("/api/account/account")
    fun findAccount(@RequestParam(name="AccountNumber") accountNumber: String): Account
    {
        return bank.findByIdOrNull(accountNumber)!!
    }



    @PostMapping("/api/account/deposit")
    fun deposit(@RequestParam (name="accountNumber")accountNumber: String, @RequestParam(name="amount") amount: Double) : Boolean
    {
        var success = false
        if(accountExists(accountNumber))
        {

            var depositFloatAccount = Bank.deposit(amount, findAccount(accountNumber))

            bank.deleteById(accountNumber)
            bank.save(depositFloatAccount)

            success = true // no validation on deposits
        }

        return success
    }

    @PostMapping("/api/account/withdraw")
    fun withdraw(@RequestParam(name = "accountNumber") accountNumber: String, @RequestParam(name="amount") amount: Double, @RequestParam(name= "pinNumber") pinNumber: String) : Boolean
    {
        var success = false

        if(accountExists(accountNumber))
        {
            if(findAccount(accountNumber).pinNumber == pinNumber)
            {
                var withdrawnFloatAcc = Bank.withdraw(amount, findAccount(accountNumber), pinNumber)

                bank.deleteById(accountNumber)
                bank.save(withdrawnFloatAcc)
                success = true
            }
        }
        return success
    }

    @PostMapping("api/account/transfer")
    fun transfer(@RequestParam(name = "fromAccountNo") fromAccountNumber: String, @RequestParam(name="toAccountNumber") toAccountNumber: String,@RequestParam(name="amount") amount: Double, @RequestParam(name= "pinNumber") pinNumber: String) : Boolean
    {
        var success = false

        if(accountExists(fromAccountNumber) && accountExists(toAccountNumber))
        {
            withdraw(fromAccountNumber, amount, pinNumber)
            deposit(toAccountNumber, amount)
            success = true
        }

        return success
    }

    @GetMapping("/api/account/data")
    fun data(): String
    {
        var output = ""
        for( acc in bank.findAll())
        {
            output += Bank.data(acc) + "\n"
        }
        return output
    }

    @GetMapping("/api/account/test")
    fun test(): String
    {
        return "connection okay"
    }

}
