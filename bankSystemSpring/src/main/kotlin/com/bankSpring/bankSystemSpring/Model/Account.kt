package com.bankSpring.bankSystemSpring.Model

import javax.persistence.*

@Entity
@Table(name= "Account")
data class Account( val name: String, val pin: String)
{
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(columnDefinition = "serial")
        var accountNumber: String? = null
        var beneficiaryName: String = name
        val pinNumber: String = pin
        var funds :Double = 0.0
}


