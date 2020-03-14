package com.bankSpring.bankSystemSpring.Backup

import com.bankSpring.bankSystemSpring.Model.Account
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

class accounts_Json()
{

    fun toJson(acc: Account) : String
    {
        var mapper = jacksonObjectMapper()
        return mapper.writeValueAsString(acc)
    }
    fun convert(acc: Account) :String
    {
        var output = ""

        output += entry("accountNumber", acc.accountNumber.toString(), true)
        output += entry("beneficiaryName", acc.beneficiaryName.toString(), true)
        output += entry("pinNumber", acc.pinNumber, true)
        output += numberEntry("funds", acc.funds.toString(), false )

        return betweenCurly(output)
    }


    private fun numberEntry(header: String, value: String, withComma: Boolean) : String
    {
        var output = parenthesis(header) + ": " + value

        if(withComma)
        {
            output += ","
        }

        return output
    }
    private fun entry(header: String, value: String, withComma: Boolean) : String
    {
        var output = parenthesis(header) + ": " + parenthesis(value)

        if(withComma)
        {
            output += ","
        }
        return output
    }

    private fun betweenCurly(value: String) :String
    {
        return "{" + value + "}"
    }

    private fun parenthesis(value: String) :String
    {
        return "\"" + value +"\""
    }
}