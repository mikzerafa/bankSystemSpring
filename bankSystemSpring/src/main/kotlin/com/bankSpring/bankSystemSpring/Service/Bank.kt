package com.bankSpring.bankSystemSpring.Service

import com.bankSpring.bankSystemSpring.Model.Account
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface Bank  : CrudRepository<Account, String>{
    companion object bank {


        fun createAccount(beneficiaryName: String, pinNumber: String) : Account
        {
            return Account(beneficiaryName, pinNumber)
        }


        fun deposit(amount: Double, account: Account) : Account
        {
            account.funds+= amount
            return account
        }

        fun withdraw(amount: Double,  account: Account, pin: String) : Account
        {
            if(pin == account.pinNumber) {
                account.funds -= amount
            }

            return account
        }

        fun data(account: Account) : String
        {
            var output = ""

            output += account.accountNumber + ", "
            output += account.beneficiaryName +", "
            output += account.funds

            return output
        }



        /*
        fun findAccountNumber(beneficiaryName: String, pinNumber: String) : String
        {
            var output = ""

            for(acc in accounts)
            {
                if(acc.beneficiaryName == beneficiaryName && acc.pinNumber == pinNumber)
                {
                    output = acc.accountNumber
                }
            }

            return output;
        }
        */


    }
}